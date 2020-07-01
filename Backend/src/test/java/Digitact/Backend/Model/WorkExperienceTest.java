package Digitact.Backend.Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WorkExperienceTest {

    @Test
    void createWorkExperience() {
        WorkExperience workExperience = new WorkExperience();
        Assertions.assertThat(workExperience).isNotNull();
    }
}
