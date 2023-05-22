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

    @GetMapping(value = "/decryptFile/{fileName}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public Mono<String> decryptFile(@PathVariable String fileName) {
        return cryptoService.decryptFile(fileName);
    }

}
