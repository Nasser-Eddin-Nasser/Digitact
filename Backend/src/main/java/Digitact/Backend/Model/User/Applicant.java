package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Industries;
import Digitact.Backend.Model.KeyCompetence;
import Digitact.Backend.Model.Positions;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class Applicant extends User {
  private static final long serialVersionUID = -2343243243242432341L;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<Education> educations;

  @ElementCollection(targetClass = Industries.class)
  @Column(name = "Industries")
  private List<Industries> industries;

  @ElementCollection(targetClass = Industries.class)
  @Column(name = "Positions")
  private List<Positions> positions;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<KeyCompetence> keyCompetencies;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Fetch(FetchMode.JOIN)
  private Set<AppImage> images;

  protected Applicant() {
    super();
  }

  /**
   * @param firstName
   * @param lastName
   */
  public Applicant(String firstName, String lastName) {
    super(firstName, lastName, UserRight.Applicant);
    educations = new HashSet<Education>();
    keyCompetencies = new HashSet<KeyCompetence>();
    images = new HashSet<AppImage>();
    industries = new LinkedList<Industries>();
    positions = new LinkedList<Positions>();
  }

  public UserRight getUserRight() {
    return UserRight.Applicant;
  }

  public void setEducation(Education education) {
    educations.add(education);
  }

  public List<Education> getEducations() {
    return new ArrayList<Education>(educations);
  }

  public void setEducations(List<Education> education) {
    education.forEach(edd -> educations.add(edd));
  }

  public List<Positions> getPositions() {
    return positions;
  }

  public void setPositions(List<Positions> positions) {
    this.positions = positions;
  }

  public void addPosition(Positions position) {
    this.positions.add(position);
  }

  public void addKeyCompetence(KeyCompetence keyCompetence) {
    keyCompetencies.add(keyCompetence);
  }

  public List<KeyCompetence> getKeyCompetencies() {
    return new ArrayList<KeyCompetence>(keyCompetencies);
  }

  public void setKeyCompetencies(List<KeyCompetence> keyCompetencies) {
    keyCompetencies.forEach(comp -> this.keyCompetencies.add(comp));
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Set<AppImage> getImages() {
    return images;
  }

  public void setImages(Set<AppImage> images) {
    this.images = images;
  }

  public void addImage(AppImage image) {
    this.images.add(image);
  }

  public List<Industries> getIndustries() {
    return industries;
  }

  public void setIndustries(List<Industries> selectedIndustries) {
    this.industries = selectedIndustries;
  }

  public void addIndustries(Industries industriesType) {
    this.industries.add(industriesType);
  }
}
