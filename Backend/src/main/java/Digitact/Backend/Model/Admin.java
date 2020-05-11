package Digitact.Backend.Model;

/**
 * Class for the controllers
 */
public class Admin extends User {

    private String firstName;
    private String lastName;

    /**
     * @param firstName
     * @param lastName
     */
    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
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
