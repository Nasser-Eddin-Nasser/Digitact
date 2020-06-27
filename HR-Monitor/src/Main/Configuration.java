package Main;

import java.nio.file.Paths;

public class Configuration {
    public static final String BES_URI = "http://localhost:9090/api/HRController/";
    public static final String absoluteFileSystemPath =
            Paths.get(System.getProperty("user.dir")).getParent().toAbsolutePath()
                    + "\\FileSystem\\";
}
