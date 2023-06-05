package com.example.sort.service.sortingAlgorithms;

import com.example.sort.enums.SortStrategy;
import jakarta.inject.Singleton;


@Singleton
public class BubbleSortService implements Sort{

    @Override
    public SortStrategy getStrategy() {
        return SortStrategy.BUBBLE_SORT;
    }

    public int [] sort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
