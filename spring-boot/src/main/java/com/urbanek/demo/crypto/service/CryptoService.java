package com.urbanek.demo.crypto.service;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.dto.CryptoDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@AllArgsConstructor
public class CryptoService {
    private final CryptoConverter cryptoConverter;

    private static final String SECRET_KEY = "0123456789abcdef0123456789abcdef";
    private static final String INIT_VECTOR = "abcdefghijklmnop";

    public Mono<CryptoDtoResponse> encrypt(final CryptoDtoRequest input) {

        return Mono.fromCallable(() -> {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] encrypted = cipher.doFinal(cryptoConverter.CryptoDtoRequestToString(input).getBytes());
            return cryptoConverter.byteArrayToCryptoDtoResponse(encrypted);
        });
    }

    public  Mono<CryptoDtoResponse> decrypt(final CryptoDtoRequest encrypted) {
        return Mono.fromCallable(() -> {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] original = cipher.doFinal(cryptoConverter.CryptoDtoRequestToString(encrypted).getBytes());
            return cryptoConverter.byteArrayToCryptoDtoResponse(original);
        });
    }

    public Mono<byte[]> encrypt(byte[] input) {
        return Mono.fromCallable(() -> {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            return cipher.doFinal(input);
        });
    }
}
