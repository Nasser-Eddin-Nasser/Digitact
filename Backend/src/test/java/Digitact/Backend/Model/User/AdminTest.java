package Digitact.Backend.Model.User;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AdminTest {
    @Test
    void createAdmin() {
        Admin admin = new Admin();
        Assertions.assertThat(admin).isNotNull();
    }
}
