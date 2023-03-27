package com.urbanek.demo.crypto.controller;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.dto.CryptoDtoResponse;
import com.urbanek.demo.crypto.dto.CryptoKeysDtoResponse;
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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.Logger;

@RestController
@Validated
@RequestMapping("/crypto")
@AllArgsConstructor
public class CryptoController {
    private final Logger log = LogManager.getLogger(CryptoController.class);
    private final Path basePath = Paths.get("./src/main/resources/upload");
    private final CryptoService cryptoService;

//    @GetMapping("/generate")
//    public Mono<ResponseEntity<CryptoKeysDtoResponse>> generateKeys(){
//        try {
//            return cryptoService.generateKeys().map(ResponseEntity::ok);
//        } catch (IOException | NoSuchAlgorithmException e) {
//            throw new RuntimeException("Exception");
//        }
//    }

//    @PostMapping("/encode")
//    public Mono<ResponseEntity<CryptoDtoResponse>> encode(@RequestBody final CryptoDtoRequest cryptoDtoRequest){
//        try {
//            return cryptoService.encrypt(cryptoDtoRequest).map(ResponseEntity::ok);
//        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
//                 InvalidKeyException e) {
//            throw new RuntimeException("Exception!");
//        }
//    }

//    @PostMapping("/decode")
//    public Mono<ResponseEntity<CryptoDtoResponse>> decode(final CryptoDtoRequest cryptoDtoRequest){
//        return cryptoService.decode(cryptoDtoRequest).map(ResponseEntity::ok);
//    }

    @PostMapping(value="/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public  Mono<Void> uploadFile (@RequestPart("file") Mono <FilePart> file) {


        return file
                .doOnNext(fp->log.info("File: "+fp.filename()))
                .flatMap(fp->fp.transferTo(basePath.resolve(fp.filename())))
                .then();
    }

}
