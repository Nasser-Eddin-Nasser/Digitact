package Util;

import Model.Education;
import Model.User.ApplicantUI;
import Storage.DBStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JSONTools {
    public static void convertJSONToApplicant(String jsonInput) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            DBStorage.setUsers(
                    mapper.readValue(jsonInput, new TypeReference<List<ApplicantUI>>() {}));
        } catch (JsonProcessingException e) {
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
}
