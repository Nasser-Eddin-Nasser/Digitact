package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Positions {
    @JsonProperty("Consultant")
    consultant_Business_Consultant,
    @JsonProperty("ITConsultantInformationsmanagement")
    iT_Consultant_Informationsmanagement,
    @JsonProperty("ITConsultantJavaJEE")
    iT_Consultant_Java_JEE,
    @JsonProperty("ITConsultantDataScienceArtificialIntelligence")
    iT_Consultant_Data_Science,
    @JsonProperty("ConsultantSAP")
    consultant_SAP,
    @JsonProperty("InternshipWorkingStudent")
    internship_Working_Student,
    Others
}
