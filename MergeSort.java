import java.util.Scanner;

class MergeSort {

    private static final int CUTOFF = 16; // small-n cutoff для insertion sort

    // Вставки
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

    // Merge с использованием буфера
    static void merge(int[] arr, int[] buffer, int l, int m, int r, Metrics metrics) {
        // Копируем в буфер
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

    // Основной MergeSort
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

    // Входной метод
    static void sort(int[] arr, Metrics metrics) {
        int[] buffer = new int[arr.length]; // единый reusable буфер
        mergeSort(arr, buffer, 0, arr.length - 1, 1, metrics);
    }
}
