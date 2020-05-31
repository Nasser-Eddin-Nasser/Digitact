package Digitact.Backend.Model.User;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Industries;
import Digitact.Backend.Model.Positions;

import java.util.List;

public class ApplicantUI implements IUser {

  private String firstName;
  private String lastName;
  private List<Education> educations;
  private Industries industries;
  private Positions positions;

  protected ApplicantUI() {}

  public ApplicantUI(
      String firstName,
      String lastName,
      List<Education> educations,
      Industries industries,
      Positions positions) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.educations = educations;
    this.industries = industries;
    this.positions = positions;
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

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }
}
