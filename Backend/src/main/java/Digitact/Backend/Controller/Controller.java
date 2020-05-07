package Digitact.Backend.Controller;

import Digitact.Backend.Model.Admin;
import Digitact.Backend.Storage.Storage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/controller")
@RestController
public class Controller {

    @GetMapping
    public void createAdmin(@RequestBody @Valid Admin admin) {
        Storage.addUserToDB(admin);
    }
}
