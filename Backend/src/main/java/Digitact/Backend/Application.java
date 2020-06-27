package Digitact.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static Digitact.Backend.ConfigProperties.absoluteFileSystemPath;

/** Main application */
@SpringBootApplication
public class Application {
    /** @param args */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
