package Digitact.Backend;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
    public static final int serverPort = 9090;
    public static final String absoluteFileSystemPath = "D:\\Masters\\2nd Semester\\AMOS Project\\FileSystem\\BE\\";
    public static final String BLOCKFORMAT = ".txt";
    public static final long BLOCKSIZE = 1024 * 1024; // 1 MiB
    public static final int Max_Repetition_Try = 5;
    public static final String absoluteClientURL_ng = "";
    public static final String absoluteClientURL_ionic = "";
    // Security
    public static final String SECRET = "DigitactAPIKey";
    public static final long EXPIRATION_TIME = 1296_000_000; // 15 days
    public static final String USER_HEADER_STRING = "userAuthorization";
    public static final String SIGN_UP_URL = "/register";
    public static final String DEVICE_HEADER_STRING = "deviceAuthorization";
    public static final String SECRET_DEVICE = "DeviceOfDigitact";
    // --test configurations--
    public static boolean testEnvironment = false; // it will be dynamically set by the test classes
    public static String testAbsoluteFileSystemPath =
            "xx\\xx\\"; // it will be dynamically set by the test classes

    public static void AssertionConfig() {
        if (absoluteFileSystemPath.length() < 1
                || !Files.exists(Paths.get(absoluteFileSystemPath))) {
            System.err.println("FileSystem doesn't exist");
            System.exit(0);
        }
    }
}
