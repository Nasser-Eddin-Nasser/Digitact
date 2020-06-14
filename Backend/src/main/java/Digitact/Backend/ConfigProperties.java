package Digitact.Backend;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
    public static final String absoluteFileSystemPath = "/Users/vishwasanavatti/AMOS/FileSystem/";
    public static final String BLOCKFORMAT = ".txt";
    public static final long BLOCKSIZE = 1024 * 1024; // 1 MiB
    public static final int Max_Repetition_Try = 5;
}
