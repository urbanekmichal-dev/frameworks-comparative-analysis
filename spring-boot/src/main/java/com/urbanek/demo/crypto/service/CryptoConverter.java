package com.urbanek.demo.crypto.service;

import com.urbanek.demo.crypto.dto.CryptoDtoRequest;
import com.urbanek.demo.crypto.dto.CryptoDtoResponse;
import org.springframework.stereotype.Component;

@Component
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
