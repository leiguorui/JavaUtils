package com.renrennet.utils.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by leiguorui on 1/5/15.
 *
 * 文件的加密和解密
 */
public class CryptoFile {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    // 加密
    public static void encrypt(String key, File inputFile, File outputFile){
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    // 解密
    public static void decrypt(String key, File inputFile, File outputFile){
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile,
                                 File outputFile) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            //TODO 这里需要抛出异常，以便项目捕获
            System.out.println("Error encrypting/decrypting file: "+ex);
        }
    }

    public static void main(String[] args) {
<<<<<<< HEAD:src/main/java/com/renrennet/utils/file/EncryptionAndDecryption.java
<<<<<<< HEAD
=======
        //这里的key为15位
>>>>>>> 文件的加密与解密
=======
        //这里的key为15位
>>>>>>> 文件的加密与解密:src/main/java/com/renrennet/utils/security/CryptoFile.java
        String key = "0123456789abcdef";
        File inputFile = new File("/home/leiguorui/repos.txt");
        File encryptedFile = new File("document.encrypted");
        File decryptedFile = new File("document.decrypted");

        CryptoFile.encrypt(key, inputFile, encryptedFile);
        CryptoFile.decrypt(key, encryptedFile, decryptedFile);
    }
}
