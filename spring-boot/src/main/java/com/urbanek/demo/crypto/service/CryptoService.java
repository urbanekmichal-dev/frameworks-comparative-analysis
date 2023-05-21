package com.urbanek.demo.crypto.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@Service
public class CryptoService {
    private final CryptoConverter cryptoConverter;
    private static final int RSA_KEY_SIZE = 2048;
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;
    private static final String RSA_ALGORITHM = "RSA";

    public CryptoService(final CryptoConverter cryptoConverter) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        this.cryptoConverter=cryptoConverter;
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    public Mono<byte[]> encryptText(String text) {
        return Mono.fromCallable(() -> {
            byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
            return encryptWithRSA(textBytes, publicKey);
        });
    }

    public Mono<Void> saveEncryptedText(byte[] encryptedText) {
        String savePath = "encrypted.txt";
        return Mono.fromCallable(() -> {
            Path filePath = Path.of(savePath);
            Files.write(filePath, encryptedText);
            return null;
        });
    }

    private byte[] encryptWithRSA(byte[] data, RSAPublicKey publicKey) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            return cipher.doFinal(input);
        });
    }
}
