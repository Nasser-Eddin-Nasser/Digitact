package Digitact.Backend.Model.User;

/** To hold the Json requests' user objects */
public class UserUI implements IUser {

    private String firstName;

    private String lastName;

    protected UserUI() {}

    public UserUI(String firstName, String lastName) {
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
}
