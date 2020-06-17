package Model.MVC;

public class AcModel {
    public static boolean isPasswordValid(String text) {
        return true;
    }

    public boolean checkAuthentication(String userName, String password) {
        return userName.equals("admin") && password.equals("123");
    }

    public void createNewAccount(String userName, String password, String firstName, String lastName, String adminID, String passHint, String email) {
        System.out.println(userName + " - " + password + " - " + adminID + " - " + firstName + " - " + lastName + " - " + passHint + " - " + email);
    }

    public boolean isUserNameValid(String newUserName) { //no duplication
        return true;
    }
}
