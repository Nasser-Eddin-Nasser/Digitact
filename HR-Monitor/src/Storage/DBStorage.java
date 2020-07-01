package Storage;

import Database.Connector;
import Database.Method;
import Model.Education;
import Model.User.Admin;
import Model.User.ApplicantUI;
import Model.WorkExperience;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBStorage {
    private static List<ApplicantUI> users;
    private static List<Education> eduInfo;
    private static List<Education> selected;
    private static List<WorkExperience> workInfo;

    private static List<String> adminUserNames;
    // True if receiver should wait
    private static boolean transfer = false;

    private static Admin currentAdmin;

    public static List<ApplicantUI> getStorage() {
        updateStorage();
        while (!transfer) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        transfer = false;
        return users;
    }

    public static ApplicantUI getApplicantByID(long id) {
        return DBStorage.users
                .stream()
                .filter(x -> x.getID() == id)
                .collect(Collectors.toList())
                .get(0);
    }

    private static void updateStorage() {
        try {
            getApplicants();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getApplicants() throws IOException {
        Connector.sendGetHttp(Method.getApplicants);
    }

    private static void getAllEducationInfo() throws IOException {
        Connector.sendGetHttp(Method.getAllEducationInfo);
    }

    private static void getAllWorkExperienceInfo() throws IOException {
        Connector.sendGetHttp(Method.getAllWorkExperienceInfo);
    }

    public static void setUsers(List<ApplicantUI> users) {
        DBStorage.users = new ArrayList<>(users);
        transfer = true;
    }

    public static void setEduInfo(List<Education> eduInfo) {
        DBStorage.eduInfo = new ArrayList<>(eduInfo);
        transfer = true;
    }

    public static void setWorkInfo(List<WorkExperience> workInfo) {
        DBStorage.workInfo = new ArrayList<>(workInfo);
        transfer = true;
    }

    public static void createAdmin(Admin admin) {
        Connector.sendPutType(Method.createAdminAccount, admin);
    }

    public static void getAdminByUserName(String userName) {
        Connector.sendGetHttp(Method.getAdminByUserName, userName);
    }

    public static void setCurrentAdmin(Admin admin) {
        currentAdmin = admin;
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public static void setAdminUserNames(List<String> readValue) {
        adminUserNames = new ArrayList<>(readValue);
    }

    public static List<String> getAdminUserNames() {
        getAdminsFromBES();
        return DBStorage.adminUserNames;
    }

    private static void getAdminsFromBES() {
        Connector.sendGetHttp(Method.getAdminUserNames);
    }

    public static boolean isUserNameInUse(String newUserName) {
        getAdminsFromBES();
        return adminUserNames.contains(newUserName);
    }
}
