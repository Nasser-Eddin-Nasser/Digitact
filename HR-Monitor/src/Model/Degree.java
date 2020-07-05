package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Degree {
    @JsonProperty("Master")
    Master,
    @JsonProperty("Bachelor")
    Bachelor,
    @JsonProperty("PhD")
    PhD,
    @JsonProperty("School")
    School,
    @JsonProperty("Others")
    Others
}
