package org.acme.crypto.controller;


import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.acme.crypto.dto.CryptoDtoRequest;
import org.acme.crypto.dto.CryptoDtoResponse;
import org.acme.crypto.dto.EncryptDtoResponse;
import org.acme.crypto.service.CryptoService;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/crypto")
@AllArgsConstructor
public class CryptoController {
    private final CryptoService cryptoService;

    @POST
    @Path("/encryptText")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<EncryptDtoResponse> encryptText(final CryptoDtoRequest cryptoDtoRequest) {
        return cryptoService.encryptText(cryptoDtoRequest.getData());
    }

    @GET
    @Path("/decryptFile/{fileName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<CryptoDtoResponse> decryptFile(@PathParam("fileName") String fileName) {
        return cryptoService.decryptFile(fileName);
    }



}
