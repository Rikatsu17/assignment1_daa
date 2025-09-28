package org.example;

import java.util.Random;

class QuickSort {
    private static final Random rand = new Random();

    static void swap(int[] arr, int i, int j, Metrics metrics) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.incrementSwaps();
    }

    static int partition(int[] arr, int low, int high, Metrics metrics) {

        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIndex, high, metrics);
        int pivot = arr[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j, metrics);
            }
        }
        swap(arr, i + 1, high, metrics);
        return i + 1;
    }

    static void quickSort(int[] arr, int low, int high, int depth, Metrics metrics) {
        while (low < high) {
            metrics.incrementRecursiveCalls();
            metrics.updateDepth(depth);

            int pi = partition(arr, low, high, metrics);

            if (pi - low < high - pi) {
                quickSort(arr, low, pi - 1, depth + 1, metrics);
                low = pi + 1;
            } else {
                quickSort(arr, pi + 1, high, depth + 1, metrics);
                high = pi - 1;
            }
        }
    }

    static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, 1, metrics);
    }
}
