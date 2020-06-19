package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.Industries;
import Digitact.Backend.Model.KeyCompetence;
import Digitact.Backend.Model.Positions;
import java.util.List;

public class ApplicantUI implements IUser {

    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private String linkedIn;
    private String xing;

    private Title title;
    private List<ImageString> imageList;
    private List<Education> educations;

    private List<Industries> industries;
    private List<Positions> positions;
    private List<KeyCompetence> keyCompetencies;

    private String additionalInfo;

    protected ApplicantUI() {}

    public ApplicantUI(
            String firstName,
            String lastName,
            String email,
            String phone,
            String linkedIn,
            String xing,
            Title title,
            List<ImageString> imageList,
            List<Education> educations,
            List<Industries> industries,
            List<Positions> positions,
            List<KeyCompetence> keyCompetencies,
            String additionalInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.xing = xing;
        this.linkedIn = linkedIn;
        this.title = title;
        this.imageList = imageList;
        this.educations = educations;
        this.industries = industries;
        this.positions = positions;
        this.keyCompetencies = keyCompetencies;
        this.additionalInfo = additionalInfo;
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

    public List<Industries> getIndustries() {
        return industries;
    }

    public List<ImageString> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageString> imageList) {
        this.imageList = imageList;
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

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getXing() {
        return xing;
    }

    public void setXing(String xing) {
        this.xing = xing;
    }

    public Title getTitle() {
        return title;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String impression) {
        this.additionalInfo = impression;
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
