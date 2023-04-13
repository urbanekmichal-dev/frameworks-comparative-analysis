package com.urbanek.demo.sort.controller;

import com.urbanek.demo.sort.dto.ListDtoRequest;
import com.urbanek.demo.sort.dto.ListDtoResponse;
import com.urbanek.demo.sort.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sort")
public class SortController {
    private final SortService sortService;

    @GetMapping("quick-sort")
    public Mono<ResponseEntity<ListDtoResponse>> quickSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.quickSort(listDtoRequest).map(ResponseEntity::ok);
    }

    @GetMapping("bubble-sort")
    public Mono<ResponseEntity<ListDtoResponse>> bubbleSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.bubbleSort(listDtoRequest).map(ResponseEntity::ok);
    }

    @GetMapping("insertion-sort")
    public Mono<ResponseEntity<ListDtoResponse>> insertionSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.selectionSort(listDtoRequest).map(ResponseEntity::ok);
    }

    @GetMapping("merge-sort")
    public Mono<ResponseEntity<ListDtoResponse>> mergeSort(@RequestBody final ListDtoRequest listDtoRequest) {
        return sortService.mergeSort(listDtoRequest).map(ResponseEntity::ok);
    }
}
