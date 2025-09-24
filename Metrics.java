public class Metrics {
    private long comparisons = 0;
    private long swaps = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private long startTime = 0;
    private long endTime = 0;
    private int recursiveCalls = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void enterRecursion() {
        recursiveCalls++;
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
        recursiveCalls = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void endTimer() {
        endTime = System.nanoTime();
    }

    public long getExecutionTime() {
        return (endTime - startTime) / 1_000_000;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getRecursiveCalls() {
        return recursiveCalls;
    }

    public void printMetrics() {
        System.out.println("Сравнений: " + comparisons);
        System.out.println("Обменов: " + swaps);
        System.out.println("Рекурсивных вызовов: " + recursiveCalls);
        System.out.println("Макс. глубина рекурсии: " + maxDepth);
        System.out.println("Время выполнения: " + getExecutionTime() + " мс");
    }

    public void incrementRecursiveCalls() {
        recursiveCalls++;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }
}
