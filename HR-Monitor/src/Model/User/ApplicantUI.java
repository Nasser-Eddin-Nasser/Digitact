package Model.User;

import Model.Education;
import Model.Image.AppImage;
import Model.Industries;
import Model.KeyCompetence;
import Model.Positions;

import java.util.List;
import java.util.Set;

public class ApplicantUI {
    private long id;
    private String firstName;
    private String lastName;
    private UserRight userRight;
    private String email;
    private String phone;
    private String linkedIn;
    private String xing;
    private Title title;


    private List<Education> educations;
    private List<Industries> industries;
    private List<Positions> positions;
    private List<KeyCompetence> keyCompetencies;
    private List<AppImage> images;

    protected ApplicantUI() {}

    public ApplicantUI(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ApplicantUI(
            long id,
            String firstName,
            String lastName,
            String email,
            String phone,
            String linkedIn,
            String xing,
            Title title,
            UserRight userRight,
            List<Education> educations,
            List<Industries> industries,
            List<Positions> positions,
            List<KeyCompetence> keyCompetencies,
            List<AppImage> images) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.linkedIn = linkedIn;
        this.xing = xing;
        this.title = title;
        this.userRight = userRight;
        this.educations = educations;
        this.industries = industries;
        this.positions = positions;
        this.keyCompetencies = keyCompetencies;
        this.images = images;
    }

    public List<Industries> getIndustries() {
        return industries;
    }

    public List<Positions> getPositions() {
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

    public Long getID() {
        return id;
    }

    public List<AppImage> getAppImage() {
        return images;
    }

}
