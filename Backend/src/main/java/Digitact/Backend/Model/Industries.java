package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Industries {
    @JsonProperty("automotive")
    automotive,
    @JsonProperty("finance")
    finance,
    @JsonProperty("commerce")
    commerce,
    @JsonProperty("pharma_Helthcare")
    pharma_Helthcare,
    @JsonProperty("public_Sector")
    public_Sector,
    Others
}
