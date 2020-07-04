package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("Open")
    Open,
    @JsonProperty("Send2HR")
    Send2HR,
    @JsonProperty("Denied")
    Denied,
}
