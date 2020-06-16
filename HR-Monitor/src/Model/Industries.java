package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Industries {
    @JsonProperty("Automotive")
    automotive,
    @JsonProperty("Finance")
    finance,
    @JsonProperty("Commerce")
    commerce,
    @JsonProperty("PharmaHealthcare")
    pharma_Helthcare,
    @JsonProperty("PublicSector")
    public_Sector,
    Others
}
