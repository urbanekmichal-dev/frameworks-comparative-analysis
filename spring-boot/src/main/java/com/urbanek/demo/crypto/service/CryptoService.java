package com.urbanek.demo.crypto.service;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.dto.CryptoDtoResponse;
import com.urbanek.demo.crypto.dto.CryptoKeysDtoResponse;
import com.urbanek.demo.crypto.exception.StorageException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;


@Service
public class CryptoService {

    private final Path rootLocation;

    @Autowired
    public CryptoService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

//    public Mono<CryptoDtoResponse> encrypt(final CryptoDtoRequest cryptoDtoRequest) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
//        SecretKey key = RSAUtil.generateKey(128);
//        String algorithm = "AES/CBC/PKCS5Padding";
//        IvParameterSpec ivParameterSpec = RSAUtil.generateIv();
//        Resource resource = new ClassPathResource("inputFile/test.txt");
//        File inputFile = resource.getFile();
//        File encryptedFile = new File("classpath:baeldung.encrypted");
//        File decryptedFile = new File("document.decrypted");
//        RSAUtil.encryptFile(algorithm, key, ivParameterSpec, inputFile, encryptedFile);
//    }
//
//    public Mono<CryptoDtoResponse> decode(final CryptoDtoRequest cryptoDtoRequest){
//        return null;
//    }
//
//    public Mono<CryptoKeysDtoResponse> generateKeys() throws IOException, NoSuchAlgorithmException {
//        SecretKey key = RSAUtil.generateKey(128);
//
//        CryptoKeysDtoResponse cryptoKeysDtoResponse = new CryptoKeysDtoResponse();
//        cryptoKeysDtoResponse.setPrivateKey(key.toString());
//        cryptoKeysDtoResponse.setPublicKey(key.toString());
//       return Mono.just(cryptoKeysDtoResponse);
//    }

    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }
}
