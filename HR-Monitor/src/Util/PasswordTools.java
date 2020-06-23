package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordTools {
    

    public static String encryptString(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
