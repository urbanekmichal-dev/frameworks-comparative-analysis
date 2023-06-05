package org.acme.sort.service.sortingAlgorithms;

import org.acme.sort.enums.SortStrategy;

public interface Sort {
    public SortStrategy getStrategy();

    int [] sort (final int [] inputArray);
}
