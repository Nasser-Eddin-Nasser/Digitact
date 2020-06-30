package Digitact.Backend.Util;

import static org.junit.Assert.*;

import Digitact.Backend.ConfigProperties;
import Digitact.Backend.Exception.ImageException;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Image.Block;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.Image.ImageType;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageToolsTest {

    ImageString is;
    String invalidContent;
    String defaultFileSystem;
    AppImage ai;
    ImageTools imageTools;

    @BeforeEach
    void setUp() {
        ConfigProperties.testEnvironment = true;
        defaultFileSystem = System.getProperty("user.dir") + "\\tmpFS";
        imageTools = new ImageTools();
        invalidContent = "data:image/jpeg;base64,aaa";
        is = new ImageString(invalidContent, ImageType.profilePic);
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
    void createAppImageWithoutFileSystemLeadsToException() {
        ConfigProperties.testAbsoluteFileSystemPath = "\\xxxx\\xxx";
        boolean thrown = false;
        try {
            imageTools.createAppImage(invalidContent, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void createAppImageWitFileSystemLeads() {
        ConfigProperties.testAbsoluteFileSystemPath = defaultFileSystem + "\\";
        setUp(); // it has to be call again in order to set the new FS
        boolean thrown = false;
        try {
            ai =
                    imageTools.createAppImage(
                            ImageContentString.validImgContent, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    void combineImage() {
        boolean thrown = false;
        try {
            ai =
                    ImageTools.createAppImage(
                            ImageContentString.validImgContent, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        ImageString is = ImageTools.combineImage(ai);
        assertEquals(ImageContentString.validImgContent, is.getContent());
        assertEquals(ai.getType(), is.getType());
        assertFalse(thrown);
    }

    @Test
    void combineVeryLongImage() {
        boolean thrown = false;
        StringBuilder veryLongImageContent = getLongString();
        try {
            ai = ImageTools.createAppImage(veryLongImageContent.toString(), ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        ImageString is = ImageTools.combineImage(ai);
        assertEquals(veryLongImageContent.toString(), is.getContent());
        assertEquals(ai.getType(), is.getType());
        assertFalse(thrown);
    }

    private StringBuilder getLongString() {
        int imgSize = 1024 * 1024 * 7;
        StringBuilder veryLongImageContent = new StringBuilder(imgSize);
        veryLongImageContent.append('1'); // first char in the string
        for (int i = 0; i < imgSize; i++) {
            veryLongImageContent.append('a');
        }
        veryLongImageContent.append('z'); // last char in the string
        return veryLongImageContent;
    }

    @Test
    void checkIfBlockAreAllCreatedForBigImage() {
        boolean thrown = false;
        StringBuilder veryLongImageContent = getLongString();
        try {
            ai = ImageTools.createAppImage(veryLongImageContent.toString(), ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        List<Block> blocks = ai.getBlocks();
        assertTrue(blocks.size() > 0);
        // assert all blocks are in the FS
        for (Block b : blocks) {
            assertEquals(ai.getId(), b.getAppImage().getId());
            assertTrue(
                    new File(new File(defaultFileSystem), b.getID() + ConfigProperties.BLOCKFORMAT)
                            .exists());
        }
        assertFalse(thrown);
    }

    @Test
    void checkIfBlockAreAllCreated() {
        boolean thrown = false;
        try {
            ai =
                    ImageTools.createAppImage(
                            ImageContentString.validImgContent, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        List<Block> blocks = ai.getBlocks();
        assertTrue(blocks.size() > 0);
        // assert all blocks are in the FS
        for (Block b : blocks) {
            assertEquals(ai.getId(), b.getAppImage().getId());
            assertTrue(
                    new File(new File(defaultFileSystem), b.getID() + ConfigProperties.BLOCKFORMAT)
                            .exists());
        }
        assertFalse(thrown);
    }

    @Test
    void parseImageStringToImage() {
        boolean thrown = false;
        try {
            ai =
                    ImageTools.createAppImage(
                            ImageContentString.validImgContent, ImageType.profilePic);
        } catch (ImageException e) {
            thrown = true;
        }
        List<Block> blocks = ai.getBlocks();
        assertTrue(blocks.size() > 0);
        // assert all blocks are in the FS
        for (Block b : blocks) {
            assertEquals(ai.getId(), b.getAppImage().getId());
            assertTrue(
                    new File(new File(defaultFileSystem), b.getID() + ConfigProperties.BLOCKFORMAT)
                            .exists());
        }
        assertFalse(thrown);
        imageTools.parseImageStringToImage(
                new ImageString(ImageContentString.validImgContent, ImageType.profilePic));
        assertTrue(new File(new File(defaultFileSystem), ImageType.profilePic + ".jpeg").exists());
    }
}
