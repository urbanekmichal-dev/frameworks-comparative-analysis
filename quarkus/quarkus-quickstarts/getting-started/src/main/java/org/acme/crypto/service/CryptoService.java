package org.acme.crypto.service;


import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.acme.crypto.dto.CryptoDtoResponse;
import org.acme.crypto.dto.EncryptDtoResponse;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.nio.file.Files;
import java.nio.file.Path;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Arrays;


@ApplicationScoped
@AllArgsConstructor
public class CryptoService {

    private final CryptoConverter cryptoConverter;
    private static final String TRIPLE_DES_ALGORITHM = "DESede";
    private static final String TRIPLE_DES_ALGORITHM_TRANSFORMATION = "DESede/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "secretpassworsdfgsghserdfgdfgdggweagdsecretpassworsdfgsghserdfgdfgdggweagd";

//    public static Uni<Void> deleteFile(String filePath) {
//        return Mono.fromRunnable(() -> {
//            try {
//                Path path = Paths.get(filePath);
//                Files.delete(path);
//            } catch (Exception e) {
//                throw new RuntimeException("An error occurred while deleting the file: " + filePath, e);
//            }
//        });
//    }

    public Uni<EncryptDtoResponse> encryptText(final String text) {
        return Uni.createFrom().item(() -> {
            try {
                EncryptDtoResponse encryptDtoResponse = new EncryptDtoResponse();
                // Szyfrowanie tekstu za pomocą Triple-DES
                byte[] encryptedText = encrypt(text);

                // Zapisywanie zaszyfrowanego tekstu na serwerze
                String fileName = ("file"+ LocalDateTime.now() +".txt").replace(":","-");
                saveEncryptedText(encryptedText, fileName);

                encryptDtoResponse.setFileName(fileName);
                return encryptDtoResponse;
            } catch (Exception e) {
                throw new RuntimeException("Błąd podczas szyfrowania i zapisu tekstu.", e);
            }
        });
    }

    private void saveEncryptedText(byte[] encryptedText, String savePath) throws Exception {
        Path filePath = Path.of(savePath);
        Files.write(filePath, encryptedText);
    }

    public byte[] encrypt(String message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(SECRET_KEY
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, TRIPLE_DES_ALGORITHM);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance(TRIPLE_DES_ALGORITHM_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);

        return cipherText;
    }

    public String decrypt(byte[] message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(SECRET_KEY
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, TRIPLE_DES_ALGORITHM);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance(TRIPLE_DES_ALGORITHM_TRANSFORMATION);
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        final byte[] plainText = decipher.doFinal(message);

        return new String(plainText, "UTF-8");
    }

    public Uni<CryptoDtoResponse> decryptFile(String fileName) {
        return Uni.createFrom().item(() -> {
            try {
                CryptoDtoResponse cryptoDtoResponse = new CryptoDtoResponse();
                byte[]  encryptedText = readEncryptedFile(fileName);

                String decryptedText = decrypt(encryptedText);



                cryptoDtoResponse.setData(decryptedText);
                return cryptoDtoResponse;
            } catch (Exception e) {
                throw new RuntimeException("Błąd podczas odszyfrowywania i odczytu pliku.", e);
            }
        });
    }

    private  byte[] readEncryptedFile(String fileName) throws Exception {
        Path filePath = Path.of(fileName);
        return Files.readAllBytes(filePath);

    }

}
