package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Positions {
    @JsonProperty("consultant_Business_Consultant")
    consultant_Business_Consultant,
    @JsonProperty("iT_Consultant_Informationsmanagement")
    iT_Consultant_Informationsmanagement,
    @JsonProperty("iT_Consultant_Java_JEE")
    iT_Consultant_Java_JEE,
    @JsonProperty("iT_Consultant_Data_Science")
    iT_Consultant_Data_Science,
    @JsonProperty("iT_Consultant_Artificial_Intelligence")
    iT_Consultant_Artificial_Intelligence,
    @JsonProperty("consultant_SAP")
    consultant_SAP,
    @JsonProperty("internship_Working_Student")
    internship_Working_Student,
    Others
}
