package org.acme.sort.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListDtoRequest {
    private List<Integer> integerList;
}
