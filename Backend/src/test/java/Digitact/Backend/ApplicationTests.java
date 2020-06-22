package Digitact.Backend;

import Digitact.Backend.Controller.ClientController;
import java.io.IOException;
import java.net.Socket;
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

    @Test
    void portAvailability() {
        System.out.println("--------------Testing port " + ConfigProperties.serverPort);
        boolean portavaialble;
        Socket s = null;
        try {
            s = new Socket("localhost", ConfigProperties.serverPort);

            System.out.println(
                    "--------------Port " + ConfigProperties.serverPort + " is not available");
            portavaialble = false;
        } catch (IOException e) {
            System.out.println(
                    "--------------Port " + ConfigProperties.serverPort + " is available");
            portavaialble = true;
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException("Runtime error", e);
                }
            }
        }
        assert (portavaialble);
    }
}
