package Digitact.Backend.Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EducationTest {

    @Test
    void createEducation() {
        Education education = new Education();
        Assertions.assertThat(education).isNotNull();
    }
}
