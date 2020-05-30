package Digitact.Backend.Controller;

import Digitact.Backend.Model.User;
import Digitact.Backend.Storage.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the controller class of the HR
 */
@RequestMapping("api/HRController")
@RestController
public class HRController {
    @Autowired
    IDataRepository repository;

    /**
     * @return JSON object of the user
     */
    @GetMapping("/getApplicants")
    public List<User> getAll() {
        return new ArrayList(repository.getApplicants());
    }

}
