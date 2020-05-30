package Digitact.Backend.Controller;

import Digitact.Backend.Model.User.*;
import Digitact.Backend.Storage.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/getAllUsers")
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * @param userUI - JSON request's user object
     *               <p>
     *               save the user in the DB using repository
     * @return "Applicant is created in the database"
     */
    @PostMapping("/createApplicant")
    public String createApplicant(@RequestBody UserUI userUI) {
        repository.save(new Applicant(userUI.getFirstName(), userUI.getLastName()));
        return "Applicant is created in the database";
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody UserUI userUI) {
        repository.save(new Admin(userUI.getFirstName(), userUI.getLastName()));
        return "Admin is created in the database";
    }

    @PostMapping("/createApplicantWithEducation")
    public String createAdmin(@RequestBody ApplicantUI userUI) {
        Applicant app = new Applicant(userUI.getFirstName(), userUI.getLastName());
        userUI.getEducations().forEach(x -> x.setUser(app));
        app.setEducations(userUI.getEducations());
        repository.save(app);
        return "Applicant is created in the database";
    }


}
