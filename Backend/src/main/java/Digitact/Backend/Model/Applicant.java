package Digitact.Backend.Model;

import javax.persistence.*;

/**
 * Class for the applicants
 */
@Entity
@Table(name = "users")
public class Applicant extends User{

    private static final long serialVersionUID = -2343243243242432341L;

    protected Applicant() {
        super();
    }

    /**
     * @param firstName
     * @param lastName
     */
    public Applicant(String firstName, String lastName) {
        super(firstName, lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        System.out.println("This prints");
        return String.format("Applicant[id=%d, firstname=%s, lastname=%s]",
                id,firstName,lastName );
    }

}
