package Util;

import Model.Image.AppImage;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

import static Main.Configuration.absoluteFileSystemPath;

public class ImageTools {

    public static void parseImageStringToImage(AppImage img) {
        String[] imageStrings = img.getContent().split(",");
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
        String path =
                absoluteFileSystemPath
                        + img.getType()
                        + img.getId()
                        + imageFormat;
        File file = new File(path);
        img.setPath(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
