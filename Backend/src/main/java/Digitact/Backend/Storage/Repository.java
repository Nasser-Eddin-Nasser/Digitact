package Digitact.Backend.Storage;

import Digitact.Backend.Exception.ImageException;
import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.KeyCompetence;
import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Model.WorkExperience;
import Digitact.Backend.Util.ImageTools;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.beans.factory.annotation.Autowired;

public class Repository {
    IDataRepository repo;

    @Autowired
    public Repository(IDataRepository repo) {
        if (this.repo == null) this.repo = repo;
    }

    public boolean storeApplicantOnDB(ApplicantUI applicant) {
        boolean isImageSuccessfullyStored = true;
        Applicant app = new Applicant(applicant.getFirstName(), applicant.getLastName());
        app.setEmail(applicant.getEmail());
        app.setPhone(applicant.getPhone());
        addEducationInfoToApplicant(applicant.getEducations(), app);
        addWorkExperienceToApplicant(applicant.getWorkExperiences(), app);
        addKeyCompetencesToApplicant(applicant.getKeyCompetencies(), app);
        app.setIndustries(applicant.getIndustries());
        app.setPositions(applicant.getPositions());
        app.setLinkedIn(applicant.getLinkedIn());
        app.setXing(applicant.getXing());
        app.setTitle(applicant.getTitle());
        app.setAdditionalInfo(applicant.getAdditionalInfo());
        boolean hasImages = applicant.getImageList() != null;
        if (hasImages)
            isImageSuccessfullyStored = addImagesToApplicant(applicant.getImageList(), app);
        if (isImageSuccessfullyStored) {
            try {
                repo.save(app);
            } catch (Exception e) {
                return false;
            }
        }
        return (hasImages && isImageSuccessfullyStored)
                || (!hasImages && isImageSuccessfullyStored);
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
        keyCompetences.forEach(x -> x.setUser(app));
        app.setKeyCompetencies(keyCompetences);
    }

    private void addEducationInfoToApplicant(List<Education> educationList, Applicant app) {
        educationList.forEach(x -> x.setUser(app));
        app.setEducations(educationList);
    }
    
    private void addWorkExperienceToApplicant(List<WorkExperience> workExperienceList, Applicant app) {
    	workExperienceList.forEach(x -> x.setUser(app));
        app.setWorkExperiences(workExperienceList);
    }
}
