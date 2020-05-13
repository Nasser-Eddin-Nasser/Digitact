package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract class user which will be used to define applicants and controllers
 */
@Entity
@Table(name = "users")
public abstract class User implements IUser, Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "firstname")
    protected String firstName;

    @Column(name = "lastname")
    protected String lastName;

    @Column(name = "userRole")
    protected UserRight userRight;

    protected User() {
    }

    protected User(String firstName, String lastName, UserRight userRight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRight = userRight;
    }

    /**
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    /* Returns the string representation of the User.*/
    @Override
    public String toString() {
        return String.format("User[id=%d, firstname=%s, lastname=%s]",
                id, firstName, lastName);
    }
}
