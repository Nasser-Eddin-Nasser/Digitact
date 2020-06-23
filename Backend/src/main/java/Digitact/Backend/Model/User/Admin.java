package Digitact.Backend.Model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Admin extends User {
    private static final long serialVersionUID = -2343243243242432341L;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "passHint")
    private String passHint;

    @Column(name = "password")
    private String password; // todo

    /**
     * @param firstName
     * @param lastName
     */
    public Admin(String firstName, String lastName) {
        super(firstName, lastName, UserRight.Admin);
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

    protected Admin() {
        super();
    }

    public UserRight getUserRight() {
        return UserRight.Admin;
    }
}
