package com.urbanek.demo.crypto.controller;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.service.CryptoService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@Validated
@RequestMapping("/crypto")
@AllArgsConstructor
public class CryptoController {
    private final CryptoService cryptoService;

    @PostMapping(value = "/encryptText")
    @ResponseBody
    public Mono<String> encryptText(@RequestBody final CryptoDtoRequest cryptoDtoRequest) {
        Mono<byte[]> text = cryptoService.encryptText(cryptoDtoRequest.getData());

        return text.flatMap(cryptoService::saveEncryptedText)
                .thenReturn("Text was encrypted and saved on the server");
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
