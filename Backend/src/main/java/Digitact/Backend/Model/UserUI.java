package Digitact.Backend.Model;

/**
 * To hold the Json requests' user objects
 */
public class UserUI {


    private String firstName;

    private String lastName;

    /**
     * @param firstName
     * @param lastName
     */
    public UserUI(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected UserUI() {
    }

    /**
     * get the first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get the last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /* Returns the string representation of the User.*/
    @Override
    public String toString() {
        return String.format("User[firstname=%s, lastname=%s]",
                firstName,lastName );
    }
}
