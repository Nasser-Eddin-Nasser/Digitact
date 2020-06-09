package Model.MVC;

public class AcModel {
    public boolean checkAuthentication(String userName, String password) {
        return userName.equals("admin") && password.equals("123");
    }
}
