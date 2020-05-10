package Digitact.Backend.Controller;

import Digitact.Backend.Model.Applicant;
import Digitact.Backend.Model.User;
import Digitact.Backend.Model.UserUI;
import Digitact.Backend.Storage.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/controller")
@RestController
public class Controller {

    @Autowired
    DataRepository repository;

    @GetMapping("/getusers")
    public List<UserUI> getAll(){

        List<User> users = repository.findAll();
        List<UserUI> usersUI = new ArrayList<>();
        for (User user: users) {
            usersUI.add(new UserUI(user.getFirstName(),user.getLastName()));
        }
        return usersUI;
    }

    @PostMapping("/createapplicant")
    public String create(@RequestBody UserUI userUI){
        Applicant appli = new Applicant(userUI.getFirstName(), userUI.getLastName());
        repository.save(appli);
        return "Applicant is created in the database";
    }
/*
    @PostMapping("/createadmin")
    public String create(@RequestBody UserUI userUI){
        Applicant appli = new Applicant(userUI.getFirstName(), userUI.getLastName());
        repository.save(appli);
        return "Applicant is created in the database";
    }

    @PostMapping
    public void createAdmin(@RequestBody @Valid Admin admin) {
        Storage.addUserToDB(admin);
    }

    @DeleteMapping
    public void deleteAdmin(@RequestBody @Valid Admin admin) {
        Storage.removeUserFromDB(admin);
    }

 */
}
