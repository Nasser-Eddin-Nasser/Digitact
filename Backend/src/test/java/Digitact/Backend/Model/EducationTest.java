package Digitact.Backend.Model;

import static org.junit.Assert.assertFalse;

import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EducationTest {

    @Test
    void createEducation() {
        Education education = new Education();
        Assertions.assertThat(education).isNotNull();
        User user = new Applicant("fn", "ln");
        education.setUser(user);
        Assertions.assertThat(education.getUser().getClass().equals(Applicant.class));
        assertFalse(education.getUser().getClass().equals(User.class));
    }
}
