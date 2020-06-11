package Digitact.Backend.Controller;

import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Model.User.UserUI;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** This is the controller class of the Clients */
@RequestMapping("api/controller")
@RestController
public class ClientController {
    @Autowired IDataRepository repository;

    //  @PostMapping("/setImage")
    //  public String setImage(@RequestBody String image) {
    //    ImageTool it = new ImageTool(repository);
    //    it.createAppImage(image, ImageType.CV);
    //    return "image is created in the database";
    //  }

    /** @return JSON object of the user */
    @GetMapping("/getAll")
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * @param applicant - JSON request's user object
     *     <p>save the user "Applicant" in the DB using repository
     * @return "Applicant is created in the database"
     */
    @PostMapping("/createApplicant")
    public String createApplicant(@RequestBody ApplicantUI applicant) {
        Repository myRepos = new Repository(repository); // todo singleton pattern
        myRepos.storeApplicantOnDB(applicant);
        return "Applicant is created in the database";
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody UserUI userUI) {
        repository.save(new Admin(userUI.getFirstName(), userUI.getLastName()));
        return "Admin is created in the database";
    }
}
