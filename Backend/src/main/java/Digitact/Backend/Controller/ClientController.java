package Digitact.Backend.Controller;

import static Digitact.Backend.ConfigProperties.DEVICE_HEADER_STRING;
import static Digitact.Backend.ConfigProperties.USER_HEADER_STRING;

import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Storage.IDataRepository;
import Digitact.Backend.Storage.Repository;
import Digitact.Backend.Util.PasswordTools;
import java.util.List;
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
        boolean isAuthorized = true;
        try {
            Admin admin =
                    repository.getAdminByUserClientToken(headers.get(USER_HEADER_STRING).get(0));
            if (admin != null) {
                if (myRepos.checkJwtTokenValidation(admin.getClientToken())) {
                    isAuthorized =
                            repository.getDeviceIdentfierByDeviceHeader(
                                    headers.get(DEVICE_HEADER_STRING).get(0), admin.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isAuthorized) {
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

    /**
     * This mapping is used when device starts for the first time in a new machine to register the
     * device
     *
     * @param userName
     * @param password
     * @return token
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("deviceauthorization") String deviceToken) {
        Repository myRepos = new Repository(repository);
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            Admin admin = repository.getAdminByUserName(userName);
            String encryptPassword = PasswordTools.encryptString(password);
            encryptPassword = PasswordTools.removeSpecialCharacters(encryptPassword);
            String pass = PasswordTools.removeSpecialCharacters(admin.getPassword());
            if (admin != null && (pass).equals(encryptPassword)) {
                responseHeader = myRepos.createTokenForDeviceRegistry(admin, deviceToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (responseHeader.get(DEVICE_HEADER_STRING) != null
                && responseHeader.get(USER_HEADER_STRING) != null) {
            return new ResponseEntity<String>(
                    "Token is created", responseHeader, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>(
                    "User name or password is wrong", HttpStatus.UNAUTHORIZED);
        }
    }
}
