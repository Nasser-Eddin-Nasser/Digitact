package Digitact.Backend.Model.User;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

class ApplicantTest {

    @Test
    void applicantCreation() {
        Applicant applicant = new Applicant();
        Assertions.assertThat(applicant).isNotNull();
    }
}
