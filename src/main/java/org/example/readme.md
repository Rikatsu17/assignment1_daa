# Assignment 1 – Divide and Conquer Algorithms

## Overview
In this assignment i created Metrics file, Merge Sort, Quick Sort, ClosestPair, DeterministicSelect, also tests and Main file.
For doing this all i used a knowledges of master theorem, acro-bazzi.

Implemented algorithms:
- **MergeSort** – O(n log n), stable sorting with reusable buffer
- **QuickSort** – randomized pivot, recursion depth analysis
- **Deterministic Selection** – k-th smallest element via Median of Medians
- **Closest Pair of Points** – geometric divide-and-conquer algorithm (O(n log n))
  
MergeSort: <br>
T(n) = 2T(n/2) + Θ(n)<br>
  Master theorem case 2 → T(n) = Θ(n log n)

QuickSort:<br>
Average: T(n) = T(k) + T(n-k-1) + Θ(n), E[k] ≈ n/2<br>
Θ(n log n) expected, Θ(n²) worst-case (rare with random pivot).

Deterministic Select (Median of Medians):<br>
T(n) = T(n/5) + T(7n/10) + Θ(n)
By Akra–Bazzi intuition → Θ(n)

Closest Pair of Points: <br>
T(n) = 2T(n/2) + Θ(n)<br>
Master theorem case 2 → Θ(n log n)
---

## Learning Goals
- Implement classic **divide-and-conquer algorithms** with safe recursion patterns.
- Analyze **running-time recurrences** using:
  - Master Theorem (3 cases)
  - Akra–Bazzi intuition
  - Experimental validation with measurements
- Collect and compare metrics:
  - Execution time
  - Recursion depth
  - Number of comparisons and swaps
- Validate correctness via **unit tests**.
- Present results in a structured report.

---

## Project Structure
src/<br>
├── main/java/org/example/<br>
│ ├── MergeSort.java<br>
│ ├── QuickSort.java<br>
│ ├── DeterministicSelect.java<br>
│ ├── ClosestPair.java<br>
│ └── Metrics.java<br>
└── test/java/org/example/<br>
├── MergeSortTest.java<br>
├── QuickSortTest.java<br>
├── DeterministicSelectTest.java<br>
└── ClosestPairTest.java<br>


Time comparison
![time.png](../../../resources/time.png)

Depth comparison
![depth.png](../../../resources/depth.png)

## Conclusion
In summary this assignment shows the theoretical expectations
of the divide and concquer algorithms. MergeSort and Closest Pair both follow Θ(n log n) complexity with stable recursion depth growth.
QuickSort performs very efficiently on average (Θ(n log n)), though with slightly higher variance due to randomized pivot selection.
Deterministic Select (Median of Medians) shows linear performance Θ(n), with deeper recursion in practice but predictable bounds.
