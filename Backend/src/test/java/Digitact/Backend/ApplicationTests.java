package Digitact.Backend;

import Digitact.Backend.Controller.ClientController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
  @Autowired private ClientController cc;

  @Test
  void contextLoads() {
    Assertions.assertThat(cc).isNotNull();
  }
}
