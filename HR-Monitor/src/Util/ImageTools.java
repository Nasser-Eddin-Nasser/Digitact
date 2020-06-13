package Util;

import Model.Image.AppImage;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



public class ImageTools {

    public static void parseImageStringToImage(AppImage img) {
        String absoluteFileSystemPath = "D:\\Current\\2nd Semester\\AMOS Project\\Digitact\\HR-Monitor\\src\\ApplicantImages\\";
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
                imageFormat = ".jpg"; // todo
                break;
        }

        // convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(imageStrings[1]);
        String path =
                absoluteFileSystemPath // todo where to export
                        + img.getType()
                        + img.getId()
                        + imageFormat; // todo better name like User id
        File file = new File(path);
        img.setPath(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
