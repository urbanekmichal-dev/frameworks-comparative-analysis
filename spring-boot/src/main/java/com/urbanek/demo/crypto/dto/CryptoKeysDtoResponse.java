package com.urbanek.demo.crypto.dto;

import lombok.Data;

@Data
public class CryptoKeysDtoResponse {
    private String publicKey;
    private String privateKey;
}
