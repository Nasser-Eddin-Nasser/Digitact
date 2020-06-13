package Model.Image;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ImageType {
    @JsonProperty("profilePic")
    profilePic,
    @JsonProperty("CV")
    CV;
}
