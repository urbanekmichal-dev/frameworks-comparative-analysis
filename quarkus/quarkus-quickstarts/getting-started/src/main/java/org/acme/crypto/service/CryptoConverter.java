package org.acme.crypto.service;

import org.acme.crypto.dto.CryptoDtoRequest;
import org.acme.crypto.dto.CryptoDtoResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
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
