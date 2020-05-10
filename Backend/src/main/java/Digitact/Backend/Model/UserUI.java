package Digitact.Backend.Model;


public class UserUI {

    private String firstName;

    private String lastName;

    public UserUI(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected UserUI() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /* Returns the string representation of the User.*/
    @Override
    public String toString() {
        return String.format("User[firstname=%s, lastname=%s]",
                firstName,lastName );
    }
}
