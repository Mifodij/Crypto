package Crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 *Класс написанный для шифрования данных
 * алгоритмом AES в режиме ECB
 * @see Hash_data
 * @see Calculator
 */
public class AES {


    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * Метод для расчета ключа шифрования обьекта
     * @param myKey - содержит в себе пароль пользователя из
     *                которого вырабатывается секретный ключ
     * @see AES
     */
    private void setKey(String myKey) {

        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    /**
     * Метод используется для шифрования данных
     * @param data - переменная содержащая в себя данные для шифрования
     * @param key - пароль пользователя для шифрования
     * @return возвращает массив байт уже зашифрованных данных
     */
    public byte []  Encrypt(byte [] data, String key) {
        try
        {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * Метод
     * @param data - переменная содержащая в себя данные для расшифрования
     * @param key - пароль пользователя для шифрования
     * @return возвращает массив байт уже расашифрованных данных
     */
    public byte [] Decrypt(byte[] data, String key) {
        try
        {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


}
