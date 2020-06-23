package Digitact.Backend.Controller;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.AdminUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.IEducationRepository;
import Digitact.Backend.Storage.IImageRepository;
import Digitact.Backend.Util.ImageTools;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** This is the controller class of the HR */
@RequestMapping("api/HRController")
@RestController
public class HRController {
    @Autowired IDataRepository dataRepository;
    @Autowired IEducationRepository educationRepository;
    @Autowired IImageRepository imageRepository;

    /** @return JSON object of the applicants */
    @GetMapping("/getApplicants")
    public List<User> getApplicants() {
        return new ArrayList<User>(dataRepository.getApplicants());
    }

    @GetMapping("/getAllEducationInfo")
    public List<Education> getFullEducationsInfo() {
        return new ArrayList<Education>(educationRepository.getAllEducationsInfo());
    }

    public static int counter = 0;

    @GetMapping(path = "/getImageById={imageId}")
    public String getImageById(@PathVariable String imageId) {
        return ImageTools.combineImage(imageRepository.getImageByID(imageId)).getContent();
    }

    /** @return JSON object of the admins */
    @GetMapping("/getAdmins")
    public List<User> getAdmins() {
        return new ArrayList<User>(dataRepository.getAdmins());
    }

    @GetMapping("/getAdminUserNames")
    public List<String> getAdminUserNames() {
        return new ArrayList<String>(
                dataRepository
                        .getAdmins()
                        .stream()
                        .map(x -> x.getUserName())
                        .collect(Collectors.toList()));
    }

    @GetMapping("/getAdminByUserName={userName}")
    public Admin getAdminByID(@PathVariable String userName) {
        Admin a = dataRepository.getAdminByUserName(userName);
        System.out.println(a.getEmail());
        return a;
    }

    @PostMapping(path = "/createAdminAccount")
    public String createAdminAccount(@RequestBody AdminUI admin) {

        Admin a = new Admin(admin.getFirstName(), admin.getLastName());
        a.setEmail(admin.getEmail());
        a.setPassHint(admin.getPassHint());
        a.setUserName(admin.getUserName());
        a.setPassword(admin.getPassword());
        dataRepository.save(a);
        return "Admin created!";
    }
}
