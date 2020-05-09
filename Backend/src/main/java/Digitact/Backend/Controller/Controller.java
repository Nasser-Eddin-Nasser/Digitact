package Digitact.Backend.Controller;

import Digitact.Backend.Model.Admin;
import Digitact.Backend.Model.User;
import Digitact.Backend.Storage.Storage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/controller")
@RestController
public class Controller {

    @GetMapping
    public List<User> getUserByLastName(@RequestBody @Valid String lastName) {
        return Storage.getAllUsersByLastName(lastName);
    }

    @PostMapping
    public void createAdmin(@RequestBody @Valid Admin admin) {
        Storage.addUserToDB(admin);
    }

    @DeleteMapping
    public void deleteAdmin(@RequestBody @Valid Admin admin) {
        Storage.removeUserFromDB(admin);
    }
}
