package org.example;

class MergeSort {

    private static final int CUTOFF = 16;

    static void insertionSort(int[] arr, int l, int r, Metrics metrics) {
        for (int i = l + 1; i <= r; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= l) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.incrementSwaps();
                    j--;
                } else break;
            }
            arr[j + 1] = key;
            metrics.incrementSwaps();
        }
    }

    static void merge(int[] arr, int[] buffer, int l, int m, int r, Metrics metrics) {
        for (int i = l; i <= r; i++) {
            buffer[i] = arr[i];
            metrics.incrementSwaps();
        }

        int i = l, j = m + 1, k = l;

        while (i <= m && j <= r) {
            metrics.incrementComparisons();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
            metrics.incrementSwaps();
        }

        while (i <= m) {
            arr[k++] = buffer[i++];
            metrics.incrementSwaps();
        }

        while (j <= r) {
            arr[k++] = buffer[j++];
            metrics.incrementSwaps();
        }
    }

    static void mergeSort(int[] arr, int[] buffer, int l, int r, int depth, Metrics metrics) {
        metrics.incrementRecursiveCalls();
        metrics.updateDepth(depth);

        if (r - l + 1 <= CUTOFF) {
            insertionSort(arr, l, r, metrics);
            return;
        }

        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, buffer, l, m, depth + 1, metrics);
            mergeSort(arr, buffer, m + 1, r, depth + 1, metrics);

            merge(arr, buffer, l, m, r, metrics);
        }
    }

    static void sort(int[] arr, Metrics metrics) {
        if (arr == null || arr.length <= 1) return;
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1, 1, metrics);
    }
}
