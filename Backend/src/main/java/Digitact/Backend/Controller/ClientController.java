package Digitact.Backend.Controller;

import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Model.User.UserUI;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.Repository;
import Digitact.Backend.Util.PasswordTools;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @param applicant - JSON request's user object
     *     <p>save the user "Applicant" in the DB using repository
     * @return "Applicant is created in the database"
     */
    @PostMapping("/createApplicant")
    public ResponseEntity<String> createApplicant(
            @RequestHeader HttpHeaders headers, @RequestBody ApplicantUI applicant) {
        Repository myRepos = new Repository(repository);
        boolean isValid = false;
        try {
            Long userId =
                    repository.getDeviceIdentfierByDeviceHeader(
                            headers.get("deviceauthorization").get(0));
            if (userId != null) {
                System.out.println(userId);
                Admin admin = repository.getAdminByUserId(userId);
                if (admin != null) {
                    if (admin.getClientToken().equals(headers.get("userauthorization").get(0))) {
                        isValid = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isValid) {
            boolean isSuccessful = myRepos.storeApplicantOnDB(applicant);
            return (isSuccessful)
                    ? new ResponseEntity<String>(
                            "Application is successfully saved", HttpStatus.CREATED)
                    : new ResponseEntity<String>(
                            "images save path not found", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<String>("User is not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/createAdmin")
    public String createAdmin(@RequestBody UserUI userUI) {
        repository.save(new Admin(userUI.getFirstName(), userUI.getLastName()));
        return "Admin is created in the database";
    }
    /**
     * This mapping is used when device starts for the first time in a new machine to register the
     * device
     *
     * @param userName
     * @param password
     * @return token
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> authInput) {
        Repository myRepos = new Repository(repository);
        HttpHeaders header = new HttpHeaders();
        try {
            Admin admin = repository.getAdminByUserName(authInput.get("userName"));
            String password = PasswordTools.encryptString(authInput.get("password"));
            if (admin != null && admin.getPassword().equals(password)) {
                header = myRepos.createTokenForDeviceRegistry(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!header.isEmpty()) {
            return new ResponseEntity<String>("Token is created", header, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>(
                    "User name or password is wrong", HttpStatus.UNAUTHORIZED);
        }
    }
}
