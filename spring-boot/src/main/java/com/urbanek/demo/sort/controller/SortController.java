package com.urbanek.demo.sort.controller;

import com.urbanek.demo.sort.dto.ListDtoRequest;
import com.urbanek.demo.sort.dto.ListDtoResponse;
import com.urbanek.demo.sort.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sort")
public class SortController {
    private final SortService sortService;

    @PostMapping ("quick-sort")
    public Mono<ResponseEntity<ListDtoResponse>> quickSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.quickSort(listDtoRequest).map(ResponseEntity::ok);
    }

    @PostMapping("bubble-sort")
    public Mono<ResponseEntity<ListDtoResponse>> bubbleSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.bubbleSort(listDtoRequest).map(ResponseEntity::ok);
    }

    @PostMapping("insertion-sort")
    public Mono<ResponseEntity<ListDtoResponse>> insertionSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.selectionSort(listDtoRequest).map(ResponseEntity::ok);
    }

    @PostMapping("merge-sort")
    public Mono<ResponseEntity<ListDtoResponse>> mergeSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.mergeSort(listDtoRequest).map(ResponseEntity::ok);
    }
}
