package com.urbanek.demo.sort.service;

import com.urbanek.demo.sort.dto.ListDtoRequest;
import com.urbanek.demo.sort.dto.ListDtoResponse;
import com.urbanek.demo.sort.enums.SortStrategy;
import com.urbanek.demo.sort.enums.SortType;
import com.urbanek.demo.sort.service.sortingAlgorithms.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SortService {

    private final QuickSortService quickSortService;
    private final BubbleSortService bubbleSortService;
    private final SelectionSortService selectionSortService;
    private final MergeSortService mergeSortService;
    private final SortConverter sortConverter;

    public Mono<ListDtoResponse> quickSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.quickSortService.sort(numbersArray);
        return Mono.just(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }

    public Mono<ListDtoResponse> bubbleSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.bubbleSortService.sort(numbersArray);
        return Mono.just(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }

    public Mono<ListDtoResponse> selectionSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.selectionSortService.sort(numbersArray);
        return Mono.just(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }

    public Mono<ListDtoResponse> mergeSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.mergeSortService.sort(numbersArray);
        return Mono.just(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }
}
