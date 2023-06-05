package com.example.crypto.service;


import com.example.crypto.dto.CryptoDtoRequest;
import com.example.crypto.dto.CryptoDtoResponse;
import jakarta.inject.Singleton;

@Singleton
public class CryptoConverter {
    public CryptoDtoResponse byteArrayToCryptoDtoResponse(final byte [] array){
        CryptoDtoResponse cryptoDtoResponse = new CryptoDtoResponse();
        cryptoDtoResponse.setData(new String(array));
        return cryptoDtoResponse;
    }

    public String CryptoDtoRequestToString(final CryptoDtoRequest cryptoDtoRequest){
       return cryptoDtoRequest.getData();
    }
}
