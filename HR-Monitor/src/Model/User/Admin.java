package Model.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String passHint;

    private String password; // todo

    @JsonProperty("userRight")
    private UserRight userRight;

    private static final long serialVersionUID = -2343243243242432341L;

    public Admin() {}

    public Admin(
            long id,
            String firstName,
            String lastName,
            String userName,
            String email,
            String passHint,
            String password,
            UserRight userRight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.passHint = passHint;
        this.password = password;
        this.userRight = userRight;
    }

    public Admin(
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

    public UserRight getUserRight() {
        return UserRight.Admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHint() {
        return passHint;
    }

    public void setPassHint(String passHint) {
        this.passHint = passHint;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRight(UserRight userRight) {
        this.userRight = userRight;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
