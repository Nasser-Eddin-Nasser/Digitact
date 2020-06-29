package Digitact.Backend.Util;

import Digitact.Backend.ConfigProperties;
import Digitact.Backend.Exception.ImageException;
import Digitact.Backend.Model.Image.AppImage;
import Digitact.Backend.Model.Image.Block;
import Digitact.Backend.Model.Image.ImageString;
import Digitact.Backend.Model.Image.ImageType;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static Digitact.Backend.ConfigProperties.BLOCKFORMAT;
import static Digitact.Backend.ConfigProperties.Max_Repetition_Try;

public class ImageTools {
    private static String myAbsoluteFileSystemPath = ConfigProperties.absoluteFileSystemPath;

    public ImageTools() {
        if (ConfigProperties.testEnvironment) {
            myAbsoluteFileSystemPath = ConfigProperties.testAbsoluteFileSystemPath;
        }
    }

    private static int size = (int) ConfigProperties.BLOCKSIZE;
    private static int i = 0;
    private static int tries = 0;

    public static AppImage createAppImage(String imageString, ImageType it) throws ImageException {
        AppImage appImage = new AppImage();
        appImage.createId();
        appImage.setType(it);
        List<Block> blocks = new LinkedList<Block>();

        List<String> contents = new ArrayList<String>((imageString.length() + size - 1) / size);

        for (int start = 0; start < imageString.length(); start += size) {
            contents.add(
                    imageString.substring(start, Math.min(imageString.length(), start + size)));
        }
        int series = 1;
        for (String content : contents) {
            Block block = new Block();
            block.createId();
            if (storeBlockInFS(block, content)) {
                block.setSeries(series);
                block.setAppImage(appImage);
                blocks.add(block);
                ++series;
            }
        }
        if (blocks.size() == contents.size()) {
            appImage.setBlocks(blocks);
        } else { // repeat the process if a block cant be saved correctly
            if (i < Max_Repetition_Try) {
                try {
                    ++i;
                    createAppImage(imageString, it);
                } catch (Exception e) { // ignore
                }
            } else {
                i = 0;
                appImage = null;
            }
        }
        return appImage;
    }

    private static boolean storeBlockInFS(Block block, String content) throws ImageException {
        File file = new File(myAbsoluteFileSystemPath + block.getID() + BLOCKFORMAT);
        try {
            if (file.createNewFile()) {
                boolean successful = writeBlock(file, content);
                System.out.println("Block Created!");
                return successful;
            } else System.out.println("Block already exists!");
        } catch (IOException e) {
            if (tries < Max_Repetition_Try) {
                ++tries;
                storeBlockInFS(block, content);
            } else {
                tries = 0;
                System.err.println("Error while storing Block In FS ");
                throw new ImageException("Error while storing Block In FS");
            }

        }
        return false;
    }

    private static boolean writeBlock(File block, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(block));
            writer.write(content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static ImageString combineImage(AppImage image) {
        String content = "";

        List<Block> blocks =
                image.getBlocks()
                        .stream()
                        .sorted(Comparator.comparingInt(Block::getSeries))
                        .collect(Collectors.toList());
        for (Block block : blocks) {
            content += readBlockContent(block);
        }
        return new ImageString(content, image.getType());
    }

    private static String readBlockContent(Block block) {
        BufferedReader reader = null;
        String content = "";
        try {
            reader =
                    new BufferedReader(
                            new FileReader(myAbsoluteFileSystemPath + block.getID() + BLOCKFORMAT));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            content = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException while reading a Block In FS");
        } catch (IOException e) {
            System.err.println("IOException while reading a Block In FS");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("IOException while closing a reader");
            }
        }
        return content;
    }

    public static void parseImageStringToImage(ImageString imageString) {
        String[] imageStrings = imageString.getContent().split(",");
        String imageFormat;
        switch (imageStrings[0]) { // check image format
            case "data:image/jpeg;base64":
                imageFormat = ".jpeg";
                break;
            case "data:image/png;base64":
                imageFormat = ".png";
                break;
            default:
                imageFormat = ".jpg";
                break;
        }
        // convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(imageStrings[1]);
        String path = myAbsoluteFileSystemPath + imageString.getType().toString() + imageFormat;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            System.err.println("IOException while parsing a string to image!");
        }
    }
}
