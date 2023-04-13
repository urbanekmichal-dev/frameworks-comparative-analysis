package com.urbanek.demo.crypto.controller;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.dto.CryptoDtoResponse;
import com.urbanek.demo.crypto.service.CryptoService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.Logger;

@RestController
@Validated
@RequestMapping("/crypto")
@AllArgsConstructor
public class CryptoController {
    private final CryptoService cryptoService;

    @PostMapping(value="/encrypt")
    public  Mono<ResponseEntity<CryptoDtoResponse>> encrypt (@RequestBody final CryptoDtoRequest cryptoDtoRequest) {
        return cryptoService.encrypt(cryptoDtoRequest).map(ResponseEntity::ok);
    }

    @PostMapping(value="/decrypt")
    public  Mono<ResponseEntity<CryptoDtoResponse>> decrypt (@RequestBody final CryptoDtoRequest cryptoDtoRequest) {
        return cryptoService.decrypt(cryptoDtoRequest).map(ResponseEntity::ok);
    }

    @PostMapping(value = "/encryptfile")
    public Mono<String> encryptFile(@RequestParam("file") MultipartFile file) throws IOException {
        return cryptoService.encrypt(file.getBytes())
                .doOnNext(encryptedBytes -> {
                    try {
                        Path encryptedFilePath = Paths.get("encrypted_" + file.getOriginalFilename());
                        Files.write(encryptedFilePath, encryptedBytes);
                    } catch (IOException e) {
                        throw new RuntimeException("Could not write encrypted file", e);
                    }
                })
                .thenReturn("File encrypted and saved as encrypted_" + file.getOriginalFilename());
    }

}
