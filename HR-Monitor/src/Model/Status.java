package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("Open")
    Open("Open"),
    @JsonProperty("Send2HR")
    Send2HR("Sent to HR"),
    @JsonProperty("Denied")
    Denied("Denied"),
    ;

    public String getStatus() {
        return status;
    }

    private String status;

    Status(String status) {
        this.status = status;
    }
}
