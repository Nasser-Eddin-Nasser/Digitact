package Digitact.Backend.Controller;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.Token;
import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.AdminUI;
import Digitact.Backend.Model.User.User;
import Digitact.Backend.Model.WorkExperience;
import Digitact.Backend.Storage.*;
import Digitact.Backend.Util.ImageTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** This is the controller class of the HR */
@RequestMapping("api/HRController")
@RestController
public class HRController {
  @Autowired IDataRepository dataRepository;
  @Autowired IEducationRepository educationRepository;
  @Autowired IImageRepository imageRepository;
  @Autowired IWorkExperienceRepository workExperieinceRepository;

  /** @return JSON object of the applicants */
  @GetMapping("/getApplicants")
  public List<User> getApplicants(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
    List<String> tokens = headers.get("authtoken");
    String uri = getClientURI(request);
    uri = uri.substring(0, uri.length() - "getApplicants".length());
    if (tokens.size() == 1) {
      String token = tokens.get(0);
      String[] parts = token.split(" -//- ");
      String tokenNumber = parts[0];
      String userName = parts[1];

      Token newToken =
          Repository.createNewTokenFromTokenString(
              tokenNumber, dataRepository.getAdminByUserName(userName), uri);
      Token t = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri); // token in my repo
      if (t != null && t.equals(newToken)) {
        return new ArrayList<User>(dataRepository.getApplicants());
      }
    }
    System.err.println("Access denied! with URI " + uri);
    return new ArrayList<User>();
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
      HttpServletRequest request) { // todo
    List<String> tokens = headers.get("authtoken");
    String uri = getClientURI(request);
    uri = uri.substring(0, uri.length() - ("getImageById=".length() + imageId.length()));
    if (tokens.size() == 1) {
      String token = tokens.get(0);
      String[] parts = token.split(" -//- ");
      String tokenNumber = parts[0];
      String userName = parts[1];

      Token newToken =
          Repository.createNewTokenFromTokenString(
              tokenNumber, dataRepository.getAdminByUserName(userName), uri);
      Token t = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri); // token in my repo
      if (t != null && t.equals(newToken)) {
        return ImageTools.combineImage(imageRepository.getImageByID(imageId)).getContent();
      }
    }
    System.err.println("Access denied! with URI " + uri);
    return null;
  }

  /** @return JSON object of the admins */
  @GetMapping("/getAdmins")
  public List<User> getAdmins() { // todo
    return new ArrayList<User>(dataRepository.getAdmins());
  }

  @GetMapping("/getAdminUserNames")
  public List<String> getAdminUserNames() {
    return new ArrayList<String>(
        dataRepository.getAdmins().stream().map(x -> x.getUserName()).collect(Collectors.toList()));
  }

  @GetMapping("/getAdminByUserName={userName}")
  public Admin getAdminByID(@PathVariable String userName) {
    return dataRepository.getAdminByUserName(userName);
  }

  @PostMapping(path = "/createAdminAccount")
  public String createAdminAccount( // todo readable code!
      @RequestBody AdminUI admin, @RequestHeader HttpHeaders headers, HttpServletRequest request) {
    Admin a = new Admin(admin.getFirstName(), admin.getLastName());
    List<String> tokens = headers.get("authtoken");
    if (tokens.size() == 1) {
      String token = tokens.get(0);
      String[] parts = token.split(" -//- ");
      String tokenNumber = parts[0];
      String userName = parts[1];
      String uri = getClientURI(request);
      uri = uri.substring(0, uri.length() - "createAdminAccount".length());
      Token newToken =
          Repository.createNewTokenFromTokenString(
              tokenNumber, dataRepository.getAdminByUserName(userName), uri);
      Token t = Repository.getTokenByTokenNumberAndURL(tokenNumber, uri); // token in my repo
      if (t.equals(newToken)) {
        a.setEmail(admin.getEmail());
        a.setPassHint(admin.getPassHint());
        a.setUserName(admin.getUserName());
        a.setPassword(admin.getPassword());
        dataRepository.save(a);
        return "Admin created!";
      }
    }
    return "access denied!";
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
}
