package com.example.sort.service;


import com.example.sort.dto.ListDtoRequest;
import com.example.sort.dto.ListDtoResponse;
import com.example.sort.service.sortingAlgorithms.BubbleSortService;
import com.example.sort.service.sortingAlgorithms.MergeSortService;
import com.example.sort.service.sortingAlgorithms.QuickSortService;
import com.example.sort.service.sortingAlgorithms.SelectionSortService;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

@Singleton

public class SortService {

    private final QuickSortService quickSortService;
    private final BubbleSortService bubbleSortService;
    private final SelectionSortService selectionSortService;
    private final MergeSortService mergeSortService;
    private final SortConverter sortConverter;

    public SortService(QuickSortService quickSortService, BubbleSortService bubbleSortService, SelectionSortService selectionSortService, MergeSortService mergeSortService, SortConverter sortConverter) {
        this.quickSortService = quickSortService;
        this.bubbleSortService = bubbleSortService;
        this.selectionSortService = selectionSortService;
        this.mergeSortService = mergeSortService;
        this.sortConverter = sortConverter;
    }

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
