package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Positions {
    @JsonProperty("Consultant")
    consultant_Business_Consultant("Business Consultant"),
    @JsonProperty("ITConsultantInformationsmanagement")
    iT_Consultant_Informationsmanagement("IT Consultant - Informationsmanagement"),
    @JsonProperty("ITConsultantJavaJEE")
    iT_Consultant_Java_JEE("IT Consultant - Java JEE"),
    @JsonProperty("ITConsultantDataScienceArtificialIntelligence")
    iT_Consultant_Data_Science("IT Consultant - Data Science"),
    @JsonProperty("ConsultantSAP")
    consultant_SAP("Consultant - SAP"),
    @JsonProperty("InternshipWorkingStudent")
    internship_Working_Student("Internship/Working Student"),
    Others("Others");

    private final String position;

    private Positions(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
