package Digitact.Backend.Model.User;

public class AdminUI implements IUser {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String passHint;
    private String password; // todo

    protected AdminUI() {}

    public AdminUI(
            String firstName,
            String lastName,
            String userName,
            String email,
            String passHint,
            String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.passHint = passHint;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassHint() {
        return passHint;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
