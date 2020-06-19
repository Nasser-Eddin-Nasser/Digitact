package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordTools {
    // from https://stackoverflow.com/questions/1132567/encrypt-password-in-configuration-files
    //    private static String base64Encode(byte[] bytes) {
    //        return Base64.getEncoder().encodeToString(bytes);
    //    }
    //
    //    private static byte[] base64Decode(String property) throws IOException {
    //        return Base64.getDecoder().decode(property);
    //    }
    //
    //    private static String decrypt(String password, SecretKeySpec key) throws
    // GeneralSecurityException, IOException, NoSuchPaddingException, NoSuchAlgorithmException {
    //        String iv = password.split(":")[0];
    //        String property = password.split(":")[1];
    //        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    //        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
    //        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    //    }
    //
    //    private static String encrypt(String property, SecretKeySpec key) throws
    // GeneralSecurityException, UnsupportedEncodingException {
    //        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    //        pbeCipher.init(Cipher.ENCRYPT_MODE, key);
    //        AlgorithmParameters parameters = pbeCipher.getParameters();
    //        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
    //        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
    //        byte[] iv = ivParameterSpec.getIV();
    //        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    //    }
    //
    //    private static SecretKeySpec createSecretKey(char[] password, byte[] salt, int
    // iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
    //
    //
    //        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
    //        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
    //        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
    //        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    //    }

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
