package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Industries {
    @JsonProperty("Automotive")
    automotive("Automotive"),
    @JsonProperty("Finance")
    finance("Finance"),
    @JsonProperty("Commerce")
    commerce("Commerce"),
    @JsonProperty("PharmaHealthcare")
    pharma_Helthcare("Pharma Healthcare"),
    @JsonProperty("PublicSector")
    public_Sector("Public Sector"),
    Others("Others");

    private final String industry;

    private Industries(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }
}
