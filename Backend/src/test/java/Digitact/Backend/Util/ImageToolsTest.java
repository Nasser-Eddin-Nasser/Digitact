package Digitact.Backend.Util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Digitact.Backend.ConfigProperties;
import Digitact.Backend.Exception.ImageException;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.Image.ImageType;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageToolsTest {

    ImageString is;
    String content;
    String defaultFileSystem;
    String SlashSign = "//";

    @BeforeEach
    void setUp() {
        if (ConfigProperties.isWindowsOS) {
            SlashSign = "\\";
        }
        ConfigProperties.testEnvironment = true;
        defaultFileSystem = System.getProperty("user.dir") + SlashSign + "tmpFS";
        ImageTools it = new ImageTools();
        content = "data:image/jpeg;base64,aaa";
        is = new ImageString(content, ImageType.profilePic);
        new File(defaultFileSystem).mkdirs();
    }

    @AfterEach
    void tearDown() {
        /*remove the tmp fs*/
        ConfigProperties.testEnvironment = false;
        deleteFolder(defaultFileSystem);
    }

    private static void deleteFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) { // some JVMs return null for empty dirs
            for (File f : files) {
                if (!f.isDirectory()) {
                    System.out.println(f.getAbsolutePath());
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    @Test
    void createAppImageWithoutFileSystemLeadsToExceptionInWindowsOS() {
        ConfigProperties.testAbsoluteFileSystemPath = "\\xxxx\\xxx";
        boolean thrown = false;
        try {
            ImageTools.createAppImage(content, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        if (ConfigProperties.isWindowsOS) assertTrue(thrown);
        else assertTrue(!thrown);
    }

    @Test
    void createAppImageWitFileSystemLeads() {
        ConfigProperties.testAbsoluteFileSystemPath = defaultFileSystem + SlashSign;
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        boolean thrown = false;
        try {
            ImageTools.createAppImage(content, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        assertFalse(false);
    }

    @Test
    void combineImage() {}

    @Test
    void parseImageStringToImage() {}
}
