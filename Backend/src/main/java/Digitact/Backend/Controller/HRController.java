package Digitact.Backend.Controller;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.IEducationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** This is the controller class of the HR */
@RequestMapping("api/HRController")
@RestController
public class HRController {
    @Autowired IDataRepository dataRepository;

    @Autowired IEducationRepository educationRepository;

    /** @return JSON object of the applicants */
    @GetMapping("/getApplicants")
    public List<User> getApplicants() {
        return new ArrayList<User>(dataRepository.getApplicants());
    }

    @GetMapping("/getAllEducationInfo")
    public List<Education> getFullEducationsInfo() {
        return new ArrayList<Education>(educationRepository.getAllEducationsInfo());
    }
}
