package org.acme.sort.service;

import org.acme.sort.dto.ListDtoRequest;
import org.acme.sort.dto.ListDtoResponse;


import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
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
