package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class User implements IUser {

    private final String FirstName;
    private final String LastName;

    /**
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    /* Returns the string representation of the User.*/
    @Override
    public String toString() {
        return String.format(FirstName + "  " + LastName );
    }
}
