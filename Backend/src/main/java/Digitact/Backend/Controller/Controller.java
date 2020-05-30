package Digitact.Backend.Controller;

import Digitact.Backend.Model.*;
import Digitact.Backend.Storage.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the controller class of the entire app
 */
@RequestMapping("api/controller")
@RestController
public class Controller {

    @Autowired
    IDataRepository repository;

    /**
     * @return JSON object of the user
     */
    @GetMapping("/getusers")
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
        return "Applicant is created in the database";
    }

}
