package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;

import java.util.List;

public class ApplicantUI implements IUser {

    private String firstName;

    private String lastName;


    private List<Education> educations;

    protected ApplicantUI() {
    }

    public ApplicantUI(String firstName, String lastName, List<Education> educations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.educations = educations;
    }

    public List<Education> getEducations() {
        return educations;
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
