package Digitact.Backend.Controller;

import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Model.User.UserUI;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller class of the Clients
 */
@RequestMapping("api/controller")
@RestController
public class ClientController {
    @Autowired
    IDataRepository repository;

    /**
     * @return JSON object of the user
     */
    @GetMapping("/getAll")
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * @param applicant - JSON request's user object
     *                  <p>save the user "Applicant" in the DB using repository
     * @return "Applicant is created in the database"
     */
    @PostMapping("/createApplicant")
    public ResponseEntity<String> createApplicant(@RequestBody ApplicantUI applicant) {
        Repository myRepos = new Repository(repository);
        boolean isSuccessful = myRepos.storeApplicantOnDB(applicant);
        return (isSuccessful)
                ? new ResponseEntity<String>(
                "Application is successfully saved", HttpStatus.CREATED)
                : new ResponseEntity<String>(
                "images save path not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody UserUI userUI) {
        repository.save(new Admin(userUI.getFirstName(), userUI.getLastName()));
        return "Admin is created in the database";
    }
}
