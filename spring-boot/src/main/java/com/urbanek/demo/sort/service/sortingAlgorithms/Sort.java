package com.urbanek.demo.sort.service.sortingAlgorithms;

import com.urbanek.demo.sort.enums.SortStrategy;

public interface Sort {
    public SortStrategy getStrategy();

    int [] sort (final int [] inputArray);
}
