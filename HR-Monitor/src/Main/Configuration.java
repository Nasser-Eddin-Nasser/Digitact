package Main;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {
    public static final String BES_URI = "https://localhost:9090/api/HRController/";

    public static final String absoluteFileSystemPath = "";

    public static void AssertionConfig() {
        if (absoluteFileSystemPath.length() < 1
                || !Files.exists(Paths.get(absoluteFileSystemPath))) {
            System.err.println("FileSystem doesn't exist");
            System.exit(0);
        }
    }
}
