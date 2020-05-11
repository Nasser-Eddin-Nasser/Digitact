package Digitact.Backend.Controller;

import Digitact.Backend.Model.Applicant;
import Digitact.Backend.Model.User;
import Digitact.Backend.Model.UserUI;
import Digitact.Backend.Storage.DataRepository;
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
    DataRepository repository;

    /**
     * @return JSON object of the user
     */
    @GetMapping("/getusers")
    public List<UserUI> getAll(){

        List<User> users = repository.findAll();
        List<UserUI> usersUI = new ArrayList<>();
        for (User user: users) {
            usersUI.add(new UserUI(user.getFirstName(),user.getLastName()));
        }
        return usersUI;
    }

    /**
     * @param userUI - JSON request's user object
     *
     * save the user in the DB using repository
     *
     * @return "Applicant is created in the database"
     */
    @PostMapping("/createapplicant")
    public String create(@RequestBody UserUI userUI){
        Applicant appli = new Applicant(userUI.getFirstName(), userUI.getLastName());
        repository.save(appli);
        return "Applicant is created in the database";
    }

}
