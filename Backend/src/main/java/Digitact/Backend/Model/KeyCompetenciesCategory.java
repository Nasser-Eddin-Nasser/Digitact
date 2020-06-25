package Digitact.Backend.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum KeyCompetenciesCategory {
    @JsonProperty("languages")
    Languages,
    @JsonProperty("businessSkills")
    BusinessSkills,
    @JsonProperty("professionalSoftware")
    ProfessionalSoftware,
    @JsonProperty("databases")
    Databases,
    @JsonProperty("programmingLanguagesAndFrameworks")
    ProgrammingLanguagesAndFrameworks,
    Others
}
