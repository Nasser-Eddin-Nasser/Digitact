package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum KeyCompetenciesCategory {
    @JsonProperty("languages")
    Languages,
    @JsonProperty("professionalSoftware")
    ProfessionalSoftware,
    @JsonProperty("databases")
    Databases,
    @JsonProperty("programmingLanguagesAndFrameworks")
    ProgrammingLanguagesAndFrameworks,
    @JsonProperty("Others")
    Others
}
