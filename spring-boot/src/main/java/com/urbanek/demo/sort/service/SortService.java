package com.urbanek.demo.sort.service;

import com.urbanek.demo.sort.dto.ListDtoRequest;
import com.urbanek.demo.sort.dto.ListDtoResponse;
import com.urbanek.demo.sort.service.sortingAlgorithms.BubbleSortService;
import com.urbanek.demo.sort.service.sortingAlgorithms.QuickSortService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SortService {

    private final QuickSortService quickSortService;
    private final BubbleSortService bubbleSortService;
    public Mono<ListDtoResponse> quickSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = listDtoRequest.getIntegerList().stream().mapToInt(Integer::intValue).toArray();
        int n = numbersArray.length;
        quickSortService.sort(numbersArray,0,n-1);
        List<Integer> sortedList = Arrays.stream(numbersArray).boxed().toList();
        ListDtoResponse listDtoResponse = new ListDtoResponse();
        listDtoResponse.setIntegerList(sortedList);
        return Mono.just(listDtoResponse);
    }

    public Mono<ListDtoResponse> bubbleSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = listDtoRequest.getIntegerList().stream().mapToInt(Integer::intValue).toArray();
        bubbleSortService.sort(numbersArray);
        List<Integer> sortedList = Arrays.stream(numbersArray).boxed().toList();
        ListDtoResponse listDtoResponse = new ListDtoResponse();
        listDtoResponse.setIntegerList(sortedList);
        return Mono.just(listDtoResponse);
    }
}
