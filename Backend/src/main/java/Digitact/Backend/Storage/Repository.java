package Digitact.Backend.Storage;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.KeyCompetence;
import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Util.ImageTools;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class Repository {
    IDataRepository repo;

    @Autowired
    public Repository(IDataRepository repo) {
        if (this.repo == null) this.repo = repo;
    }

    public boolean storeApplicantOnDB(ApplicantUI applicant) {
        Applicant app = new Applicant(applicant.getFirstName(), applicant.getLastName());
        app.setEmail(applicant.getEmail());
        app.setPhone(applicant.getPhone());
        addEducationInfoToApplicant(applicant.getEducations(), app);
        addKeyCompetencesToApplicant(applicant.getKeyCompetencies(), app);
        app.setIndustries(applicant.getIndustries());
        app.setPositions(applicant.getPositions());
        if (applicant.getImageList() != null) addImagesToApplicant(applicant.getImageList(), app);
        repo.save(app);

        return true;
    }

    private void addImagesToApplicant(List<ImageString> imageList, Applicant app) {
        imageList.forEach(
                x -> {
                    AppImage img = ImageTools.createAppImage(x.getContent(), x.getType()); // todo
                    app.addImage(img);
                    img.setUser(app);
                });
    }

    private void addKeyCompetencesToApplicant(List<KeyCompetence> keyCompetences, Applicant app) {
        keyCompetences.forEach(x -> x.setUser(app));
        app.setKeyCompetencies(keyCompetences);
    }

    private void addEducationInfoToApplicant(List<Education> educationList, Applicant app) {
        educationList.forEach(x -> x.setUser(app));
        app.setEducations(educationList);
    }
}
