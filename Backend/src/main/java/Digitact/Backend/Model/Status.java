package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("Open")
    open(0),
    @JsonProperty("Send2HR")
    send2HR(1),
    @JsonProperty("Denied")
    denied(2),
    ;


    private int num;
    Status(int i) {
        num = i;
    }

    public int getNum() {
        return num;
    }
}
