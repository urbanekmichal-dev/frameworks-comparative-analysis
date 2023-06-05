package org.acme.sort.controller;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.acme.sort.dto.ListDtoRequest;
import org.acme.sort.dto.ListDtoResponse;
import org.acme.sort.service.SortService;
import org.jboss.resteasy.reactive.RestResponse;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@RequiredArgsConstructor
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/sort")
public class SortController {
    private final SortService sortService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("quick-sort")
    public Uni<RestResponse<ListDtoResponse>> quickSort(final ListDtoRequest listDtoRequest) {
        return sortService.quickSort(listDtoRequest).map(RestResponse::ok);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("bubble-sort")
    public Uni<RestResponse<ListDtoResponse>> bubbleSort(final ListDtoRequest listDtoRequest) {
        return sortService.bubbleSort(listDtoRequest).map(RestResponse::ok);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("insertion-sort")
    public Uni<RestResponse<ListDtoResponse>> insertionSort(final ListDtoRequest listDtoRequest) {
        return sortService.selectionSort(listDtoRequest).map(RestResponse::ok);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("merge-sort")
    public Uni<RestResponse<ListDtoResponse>> mergeSort(final ListDtoRequest listDtoRequest) {
        return sortService.mergeSort(listDtoRequest).map(RestResponse::ok);
    }
}
