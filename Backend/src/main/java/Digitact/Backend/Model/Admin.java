package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin extends User {

    private final String FirstName;
    private final String LastName;

    /**
     * @param firstName
     * @param lastName
     */
    public Admin(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName) {
        super(firstName, lastName);
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    @Override
    public String getFirstName() {
        return FirstName;
    }

    @Override
    public String getLastName() {
        return LastName;
    }

    @Override
    public UserRight getRight() {
        return UserRight.Admin;
    }
}
