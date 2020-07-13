package Model.User;

import Model.*;
import Model.Image.AppImage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

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

    @JsonProperty("hrRating")
    private HrRating hrRating;

    @JsonProperty("status")
    private Status status;

    private List<Industries> industries;
    private List<Positions> positions;


    private List<KeyCompetence> keyCompetencies;
    private List<AppImage> images;

    private String hrComment;

    protected ApplicantUI() {
    }

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
            List<AppImage> images,
            HrRating hrRating,
            Status status,
            String hrComment) {
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
        this.hrRating = hrRating;
        this.status = status;
        this.hrComment = hrComment;
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

    public List<KeyCompetence> getKeyCompetencies(KeyCompetenciesCategory competanceCatogory) {
        List<KeyCompetence> selectedKeyCompetences =
                keyCompetencies
                        .stream()
                        .filter(x -> x.getCategory() == competanceCatogory)
                        .collect(Collectors.toList());
        return selectedKeyCompetences;
    }

    public void setKeyCompetencies(List<KeyCompetence> keyCompetencies) {
        this.keyCompetencies = keyCompetencies;
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

    public HrRating getHrRating() {
        return hrRating;
    }

    public void setHrRating(HrRating hrRating) {
        this.hrRating = hrRating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getHrComment() {
        return hrComment;
    }

    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }
}
