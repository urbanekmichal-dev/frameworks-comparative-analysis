package com.example.sort.controller;


import com.example.sort.dto.ListDtoRequest;
import com.example.sort.dto.ListDtoResponse;
import com.example.sort.service.SortService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import reactor.core.publisher.Mono;


@Controller("/sort")
public class SortController {
    private final SortService sortService;

    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @Post("quick-sort")
    public Mono<HttpResponse<ListDtoResponse>> quickSort(final ListDtoRequest listDtoRequest) {
        return sortService.quickSort(listDtoRequest).map(HttpResponse::ok);
    }

    @Post("bubble-sort")
    public Mono<HttpResponse<ListDtoResponse>> bubbleSort( final ListDtoRequest listDtoRequest) {
        return sortService.bubbleSort(listDtoRequest).map(HttpResponse::ok);
    }

    @Post("insertion-sort")
    public Mono<HttpResponse<ListDtoResponse>> insertionSort( final ListDtoRequest listDtoRequest) {
        return sortService.selectionSort(listDtoRequest).map(HttpResponse::ok);
    }

    @Post("merge-sort")
    public Mono<HttpResponse<ListDtoResponse>> mergeSort( final ListDtoRequest listDtoRequest) {
        return sortService.mergeSort(listDtoRequest).map(HttpResponse::ok);
    }
}
