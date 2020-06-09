package Model.User;

import Model.Education;
import Model.Industries;
import Model.KeyCompetence;
import Model.Positions;
import java.util.List;

public class ApplicantUI {
    private long  id;
    private String firstName;
    private String lastName;
    private List<Education> educations;
    private Industries industries;
    private Positions positions;
    private List<KeyCompetence> keyCompetencies;

    protected ApplicantUI() {}

    public ApplicantUI(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ApplicantUI(
            long id,
            String firstName,
            String lastName,
            List<Education> educations,
            Industries industries,
            Positions positions,
            List<KeyCompetence> keyCompetencies) {
        this.id = id;
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

    public List<KeyCompetence> getKeyCompetencies() {
        return keyCompetencies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getID() { return id; }
}
