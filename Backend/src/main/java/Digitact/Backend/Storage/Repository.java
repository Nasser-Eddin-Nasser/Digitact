package Digitact.Backend.Storage;

import static Digitact.Backend.ConfigProperties.SecurityConstants.DEVICE_HEADER_STRING;
import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import Digitact.Backend.ConfigProperties.SecurityConstants;
import Digitact.Backend.Exception.ImageException;
import Digitact.Backend.Model.*;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.User.Admin;
import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.ApplicantUI;
import Digitact.Backend.Util.ImageTools;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

public class Repository {
    IDataRepository repo;
    private static List<Token> tokenList;

    @Autowired
    public Repository(IDataRepository repo) {
        if (this.repo == null) this.repo = repo;
    }

    public static List<Token> getTokenList() {
        if (tokenList == null) tokenList = new ArrayList<Token>();
        return tokenList;
    }

    public static void insertTokenToTokenList(Token token) {
        if (tokenList == null) tokenList = new ArrayList<Token>();
        tokenList.add(token);
    }

    public static Token getTokenByTokenNumberAndURL(String tokenNumber, String uri) {
        if (tokenList == null) return null;
        List<Token> ls =
                tokenList
                        .stream()
                        .filter(
                                x ->
                                        x.getClientURL().equals(uri)
                                                && x.getUniqueRandom()
                                                        .toString()
                                                        .equals(tokenNumber.toString()))
                        .collect(Collectors.toList());
        return ls.size() > 0 ? ls.get(0) : null;
    }

    public static Token createNewTokenFromTokenString(String tokenNumber, Admin admin, String uri) {
        Token t = new Token(Long.parseLong(tokenNumber), uri);
        t.setLoggedinAdmin(admin);
        return t;
    }

    /**
     * This method is used to check whether user exist and return token after generation
     *
     * @param Admin, headers
     * @return header containing device and header token
     */
    public HttpHeaders createTokenForDeviceRegistry(Admin admin, HttpHeaders headers) {
        HttpHeaders responseHeader = new HttpHeaders();
        String deviceToken = "";
        Boolean isExist = false;

        if (headers.get(DEVICE_HEADER_STRING) == null) {
            deviceToken =
                    JWT.create()
                            .withSubject(
                                    String.valueOf(admin.getId())
                                            + UUID.randomUUID().getMostSignificantBits())
                            .sign(HMAC256(SecurityConstants.SECRET_DEVICE.getBytes()));
        } else {
            deviceToken = headers.get(DEVICE_HEADER_STRING).get(0);
            isExist =
                    repo.getDeviceIdentfierByDeviceHeader(
                            headers.get(DEVICE_HEADER_STRING).get(0), admin.getId());
        }
        String userToken =
                JWT.create()
                        .withSubject(
                                String.valueOf(admin.getId())
                                        + UUID.randomUUID().getMostSignificantBits())
                        .withExpiresAt(
                                new Date(
                                        System.currentTimeMillis()
                                                + SecurityConstants.EXPIRATION_TIME))
                        .sign(HMAC512(SecurityConstants.SECRET.getBytes()));

        admin.setClientToken(userToken);

        if (!isExist) {
            DeviceIdentifier device = new DeviceIdentifier(deviceToken);
            device.setUser(admin);
            admin.setDeviceIdentifier(device);
        }
        repo.save(admin);

        responseHeader.add(SecurityConstants.USER_HEADER_STRING, userToken);
        responseHeader.add(SecurityConstants.DEVICE_HEADER_STRING, deviceToken);

        return responseHeader;
    }

    /**
     * This method is used to check whether JWT token is valid
     *
     * @param token
     * @return boolean
     */
    public boolean checkJwtTokenValidation(String token) {

        boolean tokenValid = false;

        JWTVerifier verifier =
                JWT.require(HMAC512(SecurityConstants.SECRET.getBytes()))
                        .acceptExpiresAt(SecurityConstants.EXPIRATION_TIME)
                        .build();

        if (verifier.verify(token).getExpiresAt().compareTo(new Date(System.currentTimeMillis()))
                > 0) {
            tokenValid = true;
        }

        return tokenValid;
    }

    public boolean storeApplicantOnDB(ApplicantUI applicant) {
        boolean isImageSuccessfullyStored = true;
        Applicant app = new Applicant(applicant.getFirstName(), applicant.getLastName());
        app.setEmail(applicant.getEmail());
        app.setPhone(applicant.getPhone());
        addEducationInfoToApplicant(applicant.getEducations(), app);
        addWorkExperienceToApplicant(applicant.getWorkExperiences(), app);
        addKeyCompetencesToApplicant(applicant.getKeyCompetencies(), app);
        addHrRatingToApplicant(applicant.getHrRating(), app);
        app.setIndustries(applicant.getIndustries());
        app.setPositions(applicant.getPositions());
        app.setLinkedIn(applicant.getLinkedIn());
        app.setXing(applicant.getXing());
        app.setTitle(applicant.getTitle());
        app.setAdditionalInfo(applicant.getAdditionalInfo());
        boolean hasImages = applicant.getImageList() != null;
        if (hasImages)
            isImageSuccessfullyStored = addImagesToApplicant(applicant.getImageList(), app);
        if (isImageSuccessfullyStored) {
            try {
                repo.save(app);
            } catch (Exception e) {
                return false;
            }
        }
        return (hasImages && isImageSuccessfullyStored)
                || (!hasImages && isImageSuccessfullyStored);
    }

    private boolean addImagesToApplicant(List<ImageString> imageList, Applicant app) {
        AtomicBoolean isSuccessful = new AtomicBoolean(true);
        imageList.forEach(
                x -> {
                    AppImage img = null;
                    try {
                        img = ImageTools.createAppImage(x.getContent(), x.getType());
                    } catch (ImageException e) {
                        isSuccessful.set(false);
                        return;
                    }
                    app.addImage(img);
                    img.setUser(app);
                });
        return isSuccessful.get();
    }

    private void addKeyCompetencesToApplicant(List<KeyCompetence> keyCompetences, Applicant app) {
        if (keyCompetences != null) {
            keyCompetences.forEach(x -> x.setUser(app));
            app.setKeyCompetencies(keyCompetences);
        }
    }

    private void addEducationInfoToApplicant(List<Education> educationList, Applicant app) {
        if (educationList != null) {
            educationList.forEach(x -> x.setUser(app));
            app.setEducations(educationList);
        }
    }

    private void addWorkExperienceToApplicant(
            List<WorkExperience> workExperienceList, Applicant app) {
        if (workExperienceList != null) {
            workExperienceList.forEach(x -> x.setUser(app));
            app.setWorkExperiences(workExperienceList);
        }
    }

    private void addHrRatingToApplicant(HrRating hrRating, Applicant app) {
        hrRating.setUser(app);
        app.setHrRating(hrRating);
    }
}
