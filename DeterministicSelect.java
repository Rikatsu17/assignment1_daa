import java.util.*;

public class DeterministicSelect {

    // Вставки для маленьких массивов (ускорение)
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

    // Находит "median of medians" pivot
    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        if (right - left < 5) {
            insertionSort(arr, left, right, metrics);
            return arr[(left + right) / 2];
        }

        int n = right - left + 1;
        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];
        int medianIndex = 0;

        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight, metrics);
            medians[medianIndex++] = arr[(i + subRight) / 2];
        }

        return select(medians, 0, medianIndex - 1, medianIndex / 2, 1, metrics);
    }

    // In-place partition
    private static int partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        int i = left, j = right;
        while (i <= j) {
            while (i <= j) {
                metrics.incrementComparisons();
                if (arr[i] < pivot) i++;
                else break;
            }
            while (i <= j) {
                metrics.incrementComparisons();
                if (arr[j] > pivot) j--;
                else break;
            }
            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                metrics.incrementSwaps();
                i++;
                j--;
            }
        }
        return i; // позиция разделения
    }

    // Основной алгоритм
    public static int select(int[] arr, int left, int right, int k, int depth, Metrics metrics) {
        metrics.incrementRecursiveCalls();
        metrics.updateDepth(depth);

        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right, metrics);
        int partitionIndex = partition(arr, left, right, pivot, metrics);

        int numLeft = partitionIndex - left;
        if (k <= numLeft) {
            // recurse в меньшую сторону
            return select(arr, left, partitionIndex - 1, k, depth + 1, metrics);
        } else if (k == numLeft + 1) {
            return pivot;
        } else {
            return select(arr, partitionIndex, right, k - numLeft, depth + 1, metrics);
        }
    }

    // Упрощённый вызов
    public static int select(int[] arr, int k, Metrics metrics) {
        return select(arr, 0, arr.length - 1, k, 1, metrics);
    }
}
