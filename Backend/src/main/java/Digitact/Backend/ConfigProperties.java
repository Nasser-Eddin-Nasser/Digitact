package Digitact.Backend;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
    public static final int serverPort = 9090;
    /**
	 * set a path where you would like to store images permanently
	 * Example: Mac: /Users/USERNAME/Digitact/FileSystem/
	 * 			Windows: D:\\Digitact\\FileSystem\\
	 */
    public static final String absoluteFileSystemPath = "";
    public static final String BLOCKFORMAT = ".txt";
    public static final long BLOCKSIZE = 1024 * 1024; // 1 MiB
    public static final int Max_Repetition_Try = 5;
    /**
	 * set URL here for ng serve
	 * Example: http://localhost:4200
	 */
    public static final String absoluteClientURL_ng = "";
    /**
	 * set URL here for ionic serve
	 * Example: http://localhost:8100
	 */
    public static final String absoluteClientURL_ionic = "";
    // Security
    public static final String SECRET = "DigitactAPIKey";//secret key for user token
    public static final long EXPIRATION_TIME = 1296_000_000; // user token expires after 15 days
    public static final String USER_HEADER_STRING = "userAuthorization";
    public static final String DEVICE_HEADER_STRING = "deviceAuthorization";
    public static final String SECRET_DEVICE = "DeviceOfDigitact";//secret key for device token
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
