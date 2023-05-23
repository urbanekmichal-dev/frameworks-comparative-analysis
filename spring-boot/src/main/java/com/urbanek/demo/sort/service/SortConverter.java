package com.urbanek.demo.sort.service;

import com.urbanek.demo.sort.dto.ListDtoRequest;
import com.urbanek.demo.sort.dto.ListDtoResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SortConverter {

    public int [] listDtoRequestToIntArray(final ListDtoRequest listDtoRequest){
        return listDtoRequest.getIntegerList().stream().mapToInt(Integer::intValue).toArray();
    }

    public ListDtoResponse intArrayToListDtoResponse(final int [] array){
        List<Integer> sortedList = Arrays.stream(array).boxed().toList();
        ListDtoResponse listDtoResponse = new ListDtoResponse();
        listDtoResponse.setIntegerListSorted(sortedList);
        return listDtoResponse;
    }
}
