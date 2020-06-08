package Digitact.Backend.Controller;

import Digitact.Backend.Model.User.*;
import Digitact.Backend.Storage.IDataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** This is the controller class of the Clients */
@RequestMapping("api/controller")
@RestController
public class ClientController {

    @Autowired IDataRepository repository;

    /** @return JSON object of the user */
    @GetMapping("/getAll")
    public List<User> getAll() {
        return repository.findAll();
    }

    /**
     * @param user - JSON request's user object
     *     <p>save the user "Applicant" in the DB using repository
     * @return "Applicant is created in the database"
     */
    @PostMapping("/createApplicant")
    public String createApplicant(@RequestBody ApplicantUI user) {
        Applicant app = new Applicant(user.getFirstName(), user.getLastName());
        user.getEducations().forEach(x -> x.setUser(app));
        user.getIndustries().setUser(app);
        user.getPositions().setUser(app);
        user.getKeyCompetencies().forEach(x -> x.setUser(app));
        app.setEducations(user.getEducations());
        app.setIndustries(user.getIndustries());
        app.setPositions(user.getPositions());
        app.setKeyCompetencies(user.getKeyCompetencies());
        repository.save(app);
        return "Applicant is created in the database";
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody UserUI userUI) {
        repository.save(new Admin(userUI.getFirstName(), userUI.getLastName()));
        return "Admin is created in the database";
    }
}
