package org.acme.sort.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListDtoResponse {
    private List<Integer> integerListSorted;
}
