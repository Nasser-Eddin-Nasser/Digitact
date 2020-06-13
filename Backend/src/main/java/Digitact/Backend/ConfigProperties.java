package Digitact.Backend.Model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
  public static final String absoluteFileSystemPath =
      "\\\\LENOVO-PC\\Users\\EDDIN\\Desktop\\SS20\\AMOS\\FileSystem\\";
  public static final String blockFormat = ".txt";
  public static final long BLOCKSIZE = 1024 * 1024; // 1 MiB
}
