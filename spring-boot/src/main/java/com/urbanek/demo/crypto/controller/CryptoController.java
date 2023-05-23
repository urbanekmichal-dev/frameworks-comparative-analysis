package com.urbanek.demo.crypto.controller;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.dto.CryptoDtoResponse;
import com.urbanek.demo.crypto.dto.EncryptDtoResponse;
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
    public Mono<EncryptDtoResponse> encryptText(@RequestBody final CryptoDtoRequest cryptoDtoRequest) {
        return cryptoService.encryptText(cryptoDtoRequest.getData());
    }

    @GetMapping(value = "/decryptFile/{fileName}")
    @ResponseBody
    public Mono<CryptoDtoResponse> decryptFile(@PathVariable String fileName) {
        return cryptoService.decryptFile(fileName);
    }



}
