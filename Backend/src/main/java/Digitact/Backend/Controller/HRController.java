package Digitact.Backend.Controller;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.HRCommentHolder;
import Digitact.Backend.Model.StatusHolder;
import Digitact.Backend.Model.Token;
import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.AdminUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Model.WorkExperience;
import Digitact.Backend.Storage.*;
import Digitact.Backend.Util.ImageTools;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/** This is the controller class of the HR */
@RequestMapping("api/HRController")
@RestController
public class HRController {
    @Autowired IDataRepository dataRepository;
    @Autowired IEducationRepository educationRepository;
    @Autowired IImageRepository imageRepository;
    @Autowired IWorkExperienceRepository workExperieinceRepository;
    @Autowired IHRInfoRepository ihrInfoRepository;

    /** @return JSON object of the applicants */
    @GetMapping("/getApplicants")
    public List<User> getApplicants(
            @RequestHeader HttpHeaders headers, HttpServletRequest request) {
        List<User> res = new ArrayList<User>();
        List<String> tokens = headers.get("authtoken");
        String uri = getClientURI(request);
        uri = uri.substring(0, uri.length() - "getApplicants".length());
        return checkTokenValidationAndGetApplicants(res, tokens, uri);
    }

    private List<User> checkTokenValidationAndGetApplicants(
            List<User> res, List<String> tokens, String uri) {
        if (tokens!= null && tokens.size() == 1) {
            String token = tokens.get(0);
            String[] parts = token.split(" -//- ");
            String tokenNumber = parts[0];
            String userName = parts[1];

            Token newToken =
                    Repository.createNewTokenFromTokenString(
                            tokenNumber, dataRepository.getAdminByUserName(userName), uri);
            Token t = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri); // token in my repo
            if (t != null && t.equals(newToken)) {
                res = new ArrayList<User>(dataRepository.getApplicants());
            }
        } else {
            System.err.println("Access denied! with URI " + uri);
        }
        return res;
    }

    @GetMapping("/getAllEducationInfo")
    public List<Education> getFullEducationsInfo() { // todo do we need it?
        return new ArrayList<Education>(educationRepository.getAllEducationsInfo());
    }

    @GetMapping("/getAllWorkExperiencesInfo")
    public List<WorkExperience> getAllWorkExperiencesInfo() { // todo do we need it?
        return new ArrayList<WorkExperience>(workExperieinceRepository.getAllWorkExperiencesInfo());
    }

    @GetMapping(path = "/getImageById={imageId}")
    public String getImageById(
            @PathVariable String imageId,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {
        String res = null;
        String uri = getClientURI(request);
        uri = uri.substring(0, uri.length() - ("getImageById=".length() + imageId.length()));
        res = checkTokenValidationAndGetImage(imageId, headers, res, uri);
        if (res.equals("")) {
            System.err.println("Access denied! with URI " + uri);
        }
        return res;
    }

    private String checkTokenValidationAndGetImage(
            @PathVariable String imageId,
            @RequestHeader HttpHeaders headers,
            String res,
            String uri) {
        List<String> tokens = headers.get("authtoken");
        if (tokens!= null &&tokens.size() == 1) {
            String token = tokens.get(0);
            String[] parts = token.split(" -//- ");
            String tokenNumber = parts[0];
            String userName = parts[1];
            Token newToken =
                    Repository.createNewTokenFromTokenString(
                            tokenNumber, dataRepository.getAdminByUserName(userName), uri);
            Token t = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri); // token in my repo
            if (t != null && t.equals(newToken)) {
                res = ImageTools.combineImage(imageRepository.getImageByID(imageId)).getContent();
            }
        }else
            res="";
        return res;
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
        return dataRepository.getAdminByUserName(userName);
    }

    @PostMapping(path = "/createAdminAccount")
    public String createAdminAccount(
            @RequestBody AdminUI admin,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {
        String res = null;
        Admin a = new Admin(admin.getFirstName(), admin.getLastName());
        String uri = getClientURI(request);
        uri = uri.substring(0, uri.length() - "createAdminAccount".length());
        List<String> tokens = headers.get("authtoken");
        return checkTokenValidationAndCreateAdmin(admin, res, a, uri, tokens);
    }

    private String checkTokenValidationAndCreateAdmin(
            @RequestBody AdminUI admin, String res, Admin a, String uri, List<String> tokens) {
        if (tokens != null && tokens.size() == 1) {
            String token = tokens.get(0);
            String[] parts = token.split(" -//- ");
            String tokenNumber = parts[0];
            String userName = parts[1];

            Token newToken =
                    Repository.createNewTokenFromTokenString(
                            tokenNumber, dataRepository.getAdminByUserName(userName), uri);
            Token t = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri); // token in my repo
            if (t.equals(newToken)) {
                addAdminToDB(admin, a);
                res = "Admin created!";
            }
        } else if (dataRepository.getAdmins().size() == 0) {
            addAdminToDB(admin, a);
            res = "Admin created!";
        } else {
            res = "access denied!";
        }
        return res;
    }

    private void addAdminToDB(@RequestBody AdminUI admin, Admin a) {
        a.setEmail(admin.getEmail());
        a.setPassHint(admin.getPassHint());
        a.setUserName(admin.getUserName());
        a.setPassword(admin.getPassword());
        dataRepository.save(a);
    }

    @PostMapping(path = "/putToken")
    public String putToken(@RequestBody String body, HttpServletRequest request) {
        String uri = getClientURI(request);
        uri = uri.substring(0, uri.length() - "putToken".length());
        String[] parts = body.split(" -//- ");
        String tokenNumber = parts[0];
        String userName = parts[1];
        Token token = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri);
        if (token != null) {
            token.setLoggedinAdmin(dataRepository.getAdminByUserName(userName));
        }
        return "done!";
    }

    private String getClientURI(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        String uri;
        if (queryString == null) {
            uri = requestURL.toString();
        } else {
            uri = requestURL.append('?').append(queryString).toString();
        }
        return uri;
    }

    // used to set/generate token and as ping function
    @GetMapping("/gutenMorgen")
    public Long gutenMorgen(HttpServletRequest request) {
        String uri = getClientURI(request);
        uri = uri.substring(0, uri.length() - "gutenMorgen".length());
        Token token = new Token(UUID.randomUUID().getMostSignificantBits(), uri);
        Repository.insertTokenToTokenList(token);
        return token.getUniqueRandom();
    }

    @PostMapping(path = "/changeStatus")
    public String changeStatus(@RequestBody StatusHolder status) {
        ihrInfoRepository.setStatus(status.getStatus().getNum(), status.getAppID());
        return "changed";
    }

    @PostMapping(path = "/postHRComment")
    public String postHRComments(@RequestBody HRCommentHolder comment) {
        // statusRepository.setStatus( status.getStatus().getNum(),status.getAppID());
        ihrInfoRepository.setHRComment(comment.getComment(), comment.getAppID());
        return "changed";
    }
}
