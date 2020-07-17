package Digitact.Backend.Util;

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

    public static String removeSpecialCharacters(String password) {
        String resultStr = "";
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) > 64 && password.charAt(i) <= 122) {
                resultStr = resultStr + password.charAt(i);
            }
        }
        return resultStr;
    }
}
