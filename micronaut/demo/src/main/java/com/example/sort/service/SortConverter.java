package com.example.sort.service;

import com.example.sort.dto.ListDtoRequest;
import com.example.sort.dto.ListDtoResponse;
import jakarta.inject.Singleton;


import java.util.Arrays;
import java.util.List;

@Singleton
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
