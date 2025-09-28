package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSelectSingleElement() {
        Metrics metrics = new Metrics();
        int[] arr = {42};
        int result = DeterministicSelect.select(arr, 1, metrics);
        assertEquals(42, result, "Selecting from single-element array should return the element itself");
    }

    @Test
    void testSelectSmallArray() {
        Metrics metrics = new Metrics();
        int[] arr = {3, 1, 2};
        int result = DeterministicSelect.select(arr, 2, metrics);
        assertEquals(2, result, "The 2nd smallest element should be 2");
    }

    @Test
    void testSelectSortedArray() {
        Metrics metrics = new Metrics();
        int[] arr = {1, 2, 3, 4, 5};
        int result = DeterministicSelect.select(arr, 4, metrics);
        assertEquals(4, result, "The 4th smallest element should be 4");
    }

    @Test
    void testSelectUnsortedArray() {
        Metrics metrics = new Metrics();
        int[] arr = {10, 3, 5, 2, 8, 6, 1};
        int result = DeterministicSelect.select(arr, 3, metrics);
        assertEquals(3, result, "The 3rd smallest element should be 3");
    }

    @Test
    void testSelectFirstElement() {
        Metrics metrics = new Metrics();
        int[] arr = {9, 7, 5, 3, 1};
        int result = DeterministicSelect.select(arr, 1, metrics);
        assertEquals(1, result, "The 1st smallest element should be 1");
    }

    @Test
    void testSelectLastElement() {
        Metrics metrics = new Metrics();
        int[] arr = {9, 7, 5, 3, 1};
        int result = DeterministicSelect.select(arr, arr.length, metrics);
        assertEquals(9, result, "The largest element should be 9");
    }

    @Test
    void testSelectThrowsWhenKOutOfRange() {
        Metrics metrics = new Metrics();
        int[] arr = {4, 2, 7};
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 0, metrics));
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 4, metrics));
    }

    @Test
    void testMetricsCount() {
        Metrics metrics = new Metrics();
        int[] arr = {4, 2, 7, 1, 5, 3};
        DeterministicSelect.select(arr, 3, metrics);

        assertTrue(metrics.getComparisons() > 0, "Comparisons should be counted");
        assertTrue(metrics.getSwaps() >= 0, "Swaps should be non-negative");
        assertTrue(metrics.getRecursiveCalls() > 0, "There should be recursive calls");
        assertTrue(metrics.getMaxDepth() > 0, "Recursion depth should be tracked");
    }
}
