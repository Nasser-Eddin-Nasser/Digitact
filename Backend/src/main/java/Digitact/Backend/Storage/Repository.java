package Digitact.Backend.Storage;

import Digitact.Backend.Exception.ImageException;
import Digitact.Backend.Model.*;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Util.ImageTools;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Repository {
  IDataRepository repo;
  private static List<Token> tokenList;

  @Autowired
  public Repository(IDataRepository repo) {
    if (this.repo == null) this.repo = repo;
  }

  public static List<Token> getTokenList() {
    if (tokenList == null) tokenList = new ArrayList<Token>();
    return tokenList;
  }

  public static void insertTokenToTokenList(Token token) {
    if (tokenList == null) tokenList = new ArrayList<Token>();
    tokenList.add(token);
  }

  public static Token getTokenByTokenNumberAndURL(String tokenNumber, String uri) {
    if (tokenList == null) return null;
    List<Token> ls =
        tokenList.stream()
            .filter(
                x ->
                    x.getClientURL().equals(uri)
                        && x.getUniqueRandom().toString().equals(tokenNumber.toString()))
            .collect(Collectors.toList());
    return ls.size() > 0 ? ls.get(0) : null;
  }

  public static Token createNewTokenFromTokenString(String tokenNumber, Admin admin, String uri) {
    Token t = new Token(Long.parseLong(tokenNumber), uri);
    t.setLoggedinAdmin(admin);
    return t;
  }

  public boolean storeApplicantOnDB(ApplicantUI applicant) {
    boolean isImageSuccessfullyStored = true;
    Applicant app = new Applicant(applicant.getFirstName(), applicant.getLastName());
    app.setEmail(applicant.getEmail());
    app.setPhone(applicant.getPhone());
    addEducationInfoToApplicant(applicant.getEducations(), app);
    addWorkExperienceToApplicant(applicant.getWorkExperiences(), app);
    addKeyCompetencesToApplicant(applicant.getKeyCompetencies(), app);
    addHrRatingToApplicant(applicant.getHrRating(), app);
    app.setIndustries(applicant.getIndustries());
    app.setPositions(applicant.getPositions());
    app.setLinkedIn(applicant.getLinkedIn());
    app.setXing(applicant.getXing());
    app.setTitle(applicant.getTitle());
    app.setAdditionalInfo(applicant.getAdditionalInfo());
    boolean hasImages = applicant.getImageList() != null;
    if (hasImages) isImageSuccessfullyStored = addImagesToApplicant(applicant.getImageList(), app);
    if (isImageSuccessfullyStored) {
      try {
        repo.save(app);
      } catch (Exception e) {
        return false;
      }
    }
    return (hasImages && isImageSuccessfullyStored) || (!hasImages && isImageSuccessfullyStored);
  }

  private boolean addImagesToApplicant(List<ImageString> imageList, Applicant app) {
    AtomicBoolean isSuccessful = new AtomicBoolean(true);
    imageList.forEach(
        x -> {
          AppImage img = null;
          try {
            img = ImageTools.createAppImage(x.getContent(), x.getType());
          } catch (ImageException e) {
            isSuccessful.set(false);
            return;
          }
          app.addImage(img);
          img.setUser(app);
        });
    return isSuccessful.get();
  }

  private void addKeyCompetencesToApplicant(List<KeyCompetence> keyCompetences, Applicant app) {
    if (keyCompetences != null) {
      keyCompetences.forEach(x -> x.setUser(app));
      app.setKeyCompetencies(keyCompetences);
    }
  }

  private void addEducationInfoToApplicant(List<Education> educationList, Applicant app) {
    if (educationList != null) {
      educationList.forEach(x -> x.setUser(app));
      app.setEducations(educationList);
    }
  }

  private void addWorkExperienceToApplicant(
      List<WorkExperience> workExperienceList, Applicant app) {
    if (workExperienceList != null) {
      workExperienceList.forEach(x -> x.setUser(app));
      app.setWorkExperiences(workExperienceList);
    }
  }

  private void addHrRatingToApplicant(HrRating hrRating, Applicant app) {
    hrRating.setUser(app);
    app.setHrRating(hrRating);
  }
}
