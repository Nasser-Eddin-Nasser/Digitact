package Util;

import Model.Education;
import Model.User.Admin;
import Model.User.ApplicantUI;
import Storage.DBStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JSONTools {

    public static void convertJSONToAdmin(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            DBStorage.setCurrentAdmin(mapper.readValue(jsonInput, Admin.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertJSONToApplicant(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            DBStorage.setUsers(
                    mapper.readValue(jsonInput, new TypeReference<List<ApplicantUI>>() {}));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertJSONToEduInfo(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            DBStorage.setEduInfo(
                    mapper.readValue(jsonInput, new TypeReference<List<Education>>() {}));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static String convertAdminToJSON(Admin admin) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(admin);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void convertJSONToUserNames(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            DBStorage.setAdminUserNames(
                    mapper.readValue(jsonInput, new TypeReference<List<String>>() {}));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
