package org.acme.sort.service;


import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.acme.sort.dto.ListDtoRequest;
import org.acme.sort.dto.ListDtoResponse;
import org.acme.sort.service.sortingAlgorithms.BubbleSortService;
import org.acme.sort.service.sortingAlgorithms.MergeSortService;
import org.acme.sort.service.sortingAlgorithms.QuickSortService;
import org.acme.sort.service.sortingAlgorithms.SelectionSortService;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@AllArgsConstructor
public class SortService {

    private final QuickSortService quickSortService;
    private final BubbleSortService bubbleSortService;
    private final SelectionSortService selectionSortService;
    private final MergeSortService mergeSortService;
    private final SortConverter sortConverter;

    public Uni<ListDtoResponse> quickSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.quickSortService.sort(numbersArray);
        return Uni.createFrom().item(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }

    public Uni<ListDtoResponse> bubbleSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.bubbleSortService.sort(numbersArray);
        return Uni.createFrom().item(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }

    public Uni<ListDtoResponse> selectionSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.selectionSortService.sort(numbersArray);
        return Uni.createFrom().item(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }

    public Uni<ListDtoResponse> mergeSort(final ListDtoRequest listDtoRequest){
        int [] numbersArray = this.sortConverter.listDtoRequestToIntArray(listDtoRequest);
        this.mergeSortService.sort(numbersArray);
        return Uni.createFrom().item(this.sortConverter.intArrayToListDtoResponse(numbersArray));
    }
}
