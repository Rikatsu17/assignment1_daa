package org.example;

import java.util.*;

public class DeterministicSelect {

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                    metrics.incrementSwaps();
                } else break;
            }
            arr[j + 1] = key;
            metrics.incrementSwaps();
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        if (n <= 5) {
            insertionSort(arr, left, right, metrics);
            return arr[left + n/2];
        }

        int numMedians = (n + 4) / 5;
        int[] medians = new int[numMedians];
        int mi = 0;
        for (int i = left; i <= right; i += 5) {
            int r = Math.min(i + 4, right);
            insertionSort(arr, i, r, metrics);
            medians[mi++] = arr[i + (r - i) / 2];
        }

        int kMedian = (mi + 1) / 2;
        return select(medians, 0, mi - 1, kMedian, 1, metrics);
    }

    private static int partitionAroundPivot(int[] arr, int left, int right, int pivotValue, Metrics metrics) {
        int i = left, j = left;
        while (j <= right) {
            metrics.incrementComparisons();
            if (arr[j] < pivotValue) {
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                metrics.incrementSwaps();
                i++; j++;
            } else if (arr[j] > pivotValue) {
                j++;
            } else {
                j++;
            }
        }
        return i;
    }

    public static int select(int[] arr, int left, int right, int k, int depth, Metrics metrics) {
        metrics.incrementRecursiveCalls();
        metrics.updateDepth(depth);

        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right, metrics);

        int lt = left, i = left, gt = right;
        while (i <= gt) {
            metrics.incrementComparisons();
            if (arr[i] < pivot) {
                int tmp = arr[lt]; arr[lt] = arr[i]; arr[i] = tmp;
                metrics.incrementSwaps();
                lt++; i++;
            } else if (arr[i] > pivot) {
                int tmp = arr[i]; arr[i] = arr[gt]; arr[gt] = tmp;
                metrics.incrementSwaps();
                gt--;
            } else {
                i++;
            }
        }
        int countLess = lt - left;
        int countEqual = gt - lt + 1;

        if (k <= countLess) {
            return select(arr, left, lt - 1, k, depth + 1, metrics);
        } else if (k <= countLess + countEqual) {
            return pivot;
        } else {
            return select(arr, gt + 1, right, k - countLess - countEqual, depth + 1, metrics);
        }
    }

    public static int select(int[] arr, int k, Metrics metrics) {
        if (k < 1 || k > arr.length) throw new IllegalArgumentException("k out of range");
        return select(arr, 0, arr.length - 1, k, 1, metrics);
    }
}
