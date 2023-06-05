package com.example.crypto.controller;

import com.example.crypto.dto.CryptoDtoRequest;
import com.example.crypto.dto.CryptoDtoResponse;
import com.example.crypto.dto.EncryptDtoResponse;

import com.example.crypto.service.CryptoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import reactor.core.publisher.Mono;

@Controller("/crypto")

public class CryptoController {
    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Post(value = "/encryptText")
    public Mono<EncryptDtoResponse> encryptText(final CryptoDtoRequest cryptoDtoRequest) {
        return cryptoService.encryptText(cryptoDtoRequest.getData());
    }

    @Get(value = "/decryptFile/{fileName}")
    public Mono<CryptoDtoResponse> decryptFile(@PathVariable String fileName) {
        return cryptoService.decryptFile(fileName);
    }



}
