package com.example.sort.service.sortingAlgorithms;


import com.example.sort.enums.SortStrategy;

public interface Sort {
    public SortStrategy getStrategy();

    int [] sort (final int [] inputArray);
}
