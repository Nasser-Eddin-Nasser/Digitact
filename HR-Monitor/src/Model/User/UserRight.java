package Model.User;

import com.fasterxml.jackson.annotation.JsonProperty;

/** This contains the user permissions */
public enum UserRight {
    @JsonProperty("Admin")
    Admin,
    @JsonProperty("Applicant")
    Applicant
}
