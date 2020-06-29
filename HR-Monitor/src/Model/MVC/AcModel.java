package Model.MVC;

import Model.User.Admin;
import Storage.DBStorage;

public class AcModel {
    public static boolean isPasswordValid(String text) {
        return text.length() > 3;
    }

    public boolean checkAuthentication(String userName, String password) {
        if (DBStorage.isUserNameInUse(userName)) {
            getAdmin(userName);
            Admin admin = DBStorage.getCurrentAdmin();
            return admin.getPassword().equals(Util.PasswordTools.encryptString(password));
        }
        return false;
    }

    public void getAdmin(String userName) {
        DBStorage.getAdminByUserName(userName);
    }

    public void createNewAccount(
            String userName,
            String password,
            String firstName,
            String lastName,
            String passHint,
            String email) {
        DBStorage.createAdmin(
                new Admin(
                        firstName,
                        lastName,
                        userName,
                        email,
                        passHint,
                        Util.PasswordTools.encryptString(password)));
    }

    public boolean isUserNameValid(String newUserName) { // no duplication
        return !DBStorage.isUserNameInUse(newUserName);
    }
}
