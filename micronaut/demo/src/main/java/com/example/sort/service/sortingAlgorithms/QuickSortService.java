package com.example.sort.service.sortingAlgorithms;

import com.example.sort.enums.SortStrategy;
import jakarta.inject.Singleton;

@Singleton
public class QuickSortService implements Sort{

    @Override
    public SortStrategy getStrategy() {
        return SortStrategy.QUICK_SORT;
    }

    @Override
    public int[] sort(int[] inputArray) {
        int n = inputArray.length;
        this.sort(inputArray,0,n-1);
        return inputArray;
    }

    /* The main function that implements QuickSort()
    arr[] --> Array to be sorted,
    low  --> Starting index,
    high  --> Ending index */
    private void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
    private int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }
}
