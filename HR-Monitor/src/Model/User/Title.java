package Model.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Title {
    @JsonProperty("Mr")
    Mr,
    @JsonProperty("Mrs")
    Mrs
}
