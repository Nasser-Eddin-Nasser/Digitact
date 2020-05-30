package Digitact.Backend.Controller;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Storage.IApplicantRepository;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.IEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the controller class of the HR
 */
@RequestMapping("api/HRController")
@RestController
public class HRController {
    @Autowired
    IDataRepository dataRepository;

    @Autowired
    IEducationRepository educationRepository;
    @Autowired
    IApplicantRepository applicantRepository;

    /**
     * @return JSON object of the applicants
     */
    @GetMapping("/getApplicants")
    public List<User> getApplicants() {
        return new ArrayList(dataRepository.getApplicants());
    }

    @GetMapping("/getFullApplicantsInfo")
    public List<Applicant> getFullApplicantsInfo() {

        return new ArrayList(dataRepository.getFullApplicantsInfo());
    }

    @GetMapping("/getAllEducationsInfo")
    public List<Education> getFullEducationsInfo() {
        return new ArrayList(educationRepository.getAllEducationsInfo());
    }


}
