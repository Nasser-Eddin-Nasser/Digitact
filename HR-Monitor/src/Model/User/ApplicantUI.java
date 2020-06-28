package Model.User;

import Model.Education;
import Model.WorkExperience;
import Model.Image.AppImage;
import Model.Industries;
import Model.KeyCompetence;
import Model.Positions;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ApplicantUI {
    private long id;
    private String firstName;
    private String lastName;

    @JsonProperty("userRight")
    private UserRight userRight;

    private String email;
    private String phone;
    private String linkedIn;
    private String xing;

    private String additionalInfo;

    @JsonProperty("title")
    private Title title;

    @JsonProperty("educations")
    private List<Education> educations;
    
    @JsonProperty("workExperiences")
    private List<WorkExperience> workExperiences;

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
            UserRight userRight,
            String email,
            String phone,
            String linkedIn,
            String xing,
            Title title,
            String additionalInfo,
            List<Education> educations,
            List<WorkExperience> workExperiences,
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
        this.additionalInfo = additionalInfo;
        this.workExperiences = workExperiences;
    }

    public List<Industries> getIndustries() {
        return industries;
    }

    public List<Positions> getPositions() {
        return positions;
    }

    public List<Education> getEducation() {
        return educations;
    }

    public void setEducation(List<Education> education) {

        this.educations = education;
    }
    
    public List<WorkExperience> getWorkExperience() {
        return workExperiences;
    }

    public void setWorkExperience(List<WorkExperience> workExperience) {

        this.workExperiences = workExperience;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getXing() {
        return xing;
    }

    public List<AppImage> getImages() {
        return images;
    }

    public List<AppImage> getAppImage() {
        return images;
    }

    public long getID() {
        return id;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
