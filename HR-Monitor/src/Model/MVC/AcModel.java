package Model.MVC;

import Database.Connector;
import Database.Method;
import Model.User.Admin;
import Storage.DBStorage;
import Util.PasswordTools;

public class AcModel {
    public static boolean isPasswordValid(String text) {
        return text.length() > 3;
    }

    public boolean checkAuthentication(String userName, String password) {
        boolean isValid = false;
        if (DBStorage.isUserNameInUse(userName)) {
            getAdmin(userName);
            Admin admin = DBStorage.getCurrentAdmin();
            isValid =
                    PasswordTools.removeSpecialCharacters(admin.getPassword())
                            .equals(
                                    PasswordTools.removeSpecialCharacters(
                                            PasswordTools.encryptString((password))));
            if (isValid) {
                DBStorage.getToken().setLoggedinAdmin(admin);
                Connector.sendPutType(Method.putToken, DBStorage.getToken());
            }
        }
        return isValid;
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
