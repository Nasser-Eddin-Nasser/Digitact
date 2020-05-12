package Digitact.Backend.Model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class Applicant extends User {
    private static final long serialVersionUID = -2343243243242432341L;

    protected Applicant() {
        super();
    }

    /**
     * @param firstName
     * @param lastName
     */
    public Applicant(String firstName, String lastName) {
        super(firstName, lastName, UserRight.Applicant);
    }

    public UserRight getUserRight() {
        return UserRight.Applicant;
    }
}
