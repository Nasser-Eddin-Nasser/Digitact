package Main;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {
	/**
	 * set Http or Https URL here. 
	 * Example: https://localhost:9090/api/HRController/
	 */
    public static final String Backend_Server_URL = ""; 
    /**
	 * set a path where you would like to store images temporarily 
	 * as files in the path will be cleared on logout
	 * Example: Mac: /Users/USERNAME/Digitact/FileSystem/
	 * 			Windows: D:\\Digitact\\FileSystem\\
	 */
    public static final String absoluteFileSystemPath = "";

    public static void AssertionConfig() {
        if (absoluteFileSystemPath.length() < 1
                || !Files.exists(Paths.get(absoluteFileSystemPath))) {
            System.err.println("FileSystem doesn't exist");
            System.exit(0);
        }
    }
}
