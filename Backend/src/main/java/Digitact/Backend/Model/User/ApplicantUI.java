package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Industries;
import Digitact.Backend.Model.KeyCompetence;
import Digitact.Backend.Model.Positions;
import java.util.List;

public class ApplicantUI implements IUser {

    private String firstName;
    private String lastName;
    private List<Education> educations;
    private Industries industries;
    private Positions positions;
    private List<KeyCompetence> keyCompetencies;

    protected ApplicantUI() {}

    public ApplicantUI(
            String firstName,
            String lastName,
            List<Education> educations,
            Industries industries,
            Positions positions,
            List<KeyCompetence> keyCompetencies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.educations = educations;
        this.industries = industries;
        this.positions = positions;
        this.keyCompetencies = keyCompetencies;
    }

    public Industries getIndustries() {
        return industries;
    }

    public Positions getPositions() {
        return positions;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public List<KeyCompetence> getKeyCompetencies() { return keyCompetencies; }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
