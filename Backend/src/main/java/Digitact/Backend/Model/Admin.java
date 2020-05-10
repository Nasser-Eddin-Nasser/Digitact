package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class Admin extends User {


   // @Column(name = "firstname")
    private String firstName;

   // @Column(name = "lastname")
    private String lastName;
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
