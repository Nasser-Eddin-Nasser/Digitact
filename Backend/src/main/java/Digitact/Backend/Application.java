package Digitact.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main application */
@SpringBootApplication
public class Application {
    // TODO  move configurations to Application.properties
    // absolute file system path
    public static final String absoluteFileSystemPath =
    		"\\\\LENOVO-PC\\Users\\EDDIN\\Desktop\\SS20\\AMOS\\FileSystem\\";
    public static final String blockFormat = ".txt";
    public static final long BLOCKSIZE = 1024 * 1024; // 1 MiB

    /** @param args */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
