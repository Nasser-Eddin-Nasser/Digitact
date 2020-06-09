package Storage;

import Database.Connector;
import Database.Method;
import Model.User.ApplicantUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DBStorage {
    private static List<ApplicantUI> users;
    // True if receiver should wait
    private static boolean transfer = false;

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

    public static void setUsers(List<ApplicantUI> users) {
        DBStorage.users = new ArrayList<>(users);
        transfer = true;
    }
}
