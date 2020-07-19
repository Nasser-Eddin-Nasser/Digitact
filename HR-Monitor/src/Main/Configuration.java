package Main;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {
    public static final String Backend_Server_URL = ""; //  Http or Https

    public static final String absoluteFileSystemPath = "";

    public static void AssertionConfig() {
        if (absoluteFileSystemPath.length() < 1
                || !Files.exists(Paths.get(absoluteFileSystemPath))) {
            System.err.println("FileSystem doesn't exist");
            System.exit(0);
        }
    }
}
