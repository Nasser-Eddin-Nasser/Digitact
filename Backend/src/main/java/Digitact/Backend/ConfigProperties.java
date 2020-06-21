package Digitact.Backend;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProperties {
  public static final String absoluteFileSystemPath =
      "xx\\xx\\";
  public static final String BLOCKFORMAT = ".txt";
  public static final long BLOCKSIZE = 1024 * 1024; // 1 MiB
  public static final int Max_Repetition_Try = 5;
  public static boolean testEnvironment = false; // it will be dynamically set by the test classes
  public static String testAbsoluteFileSystemPath =
      "xx\\xx\\"; // it will be dynamically set by the test classes
}
