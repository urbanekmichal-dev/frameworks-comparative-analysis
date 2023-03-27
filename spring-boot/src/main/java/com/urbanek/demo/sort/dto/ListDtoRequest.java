package com.urbanek.demo.sort.dto;

import lombok.Data;
import java.util.List;

@Data
public class ListDtoRequest {
    private List<Integer> integerList;
}
