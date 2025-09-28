package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Algorithm Menu ===");
            System.out.println("1. MergeSort");
            System.out.println("2. QuickSort");
            System.out.println("3. Median of Medians (k-th element)");
            System.out.println("4. Closest Pair of Points");
            System.out.println("0. Exit");
            System.out.print("Choose an algorithm: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
                continue;
            }

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter number of elements: ");
                    int n1 = sc.nextInt();
                    int[] arr1 = new int[n1];
                    System.out.println("Enter " + n1 + " numbers:");
                    for (int i = 0; i < n1; i++) arr1[i] = sc.nextInt();

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    MergeSort.sort(arr1, metrics);

                    metrics.endTimer();
                    System.out.println("Sorted array:");
                    System.out.println(Arrays.toString(arr1));
                    metrics.printMetrics();
                    break;
                }

                case 2: {
                    System.out.print("Enter number of elements: ");
                    int n2 = sc.nextInt();
                    int[] arr2 = new int[n2];
                    System.out.println("Enter " + n2 + " numbers:");
                    for (int i = 0; i < n2; i++) arr2[i] = sc.nextInt();

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    QuickSort.sort(arr2, metrics);

                    metrics.endTimer();
                    System.out.println("Sorted array:");
                    System.out.println(Arrays.toString(arr2));
                    metrics.printMetrics();
                    break;
                }

                case 3: {
                    System.out.print("Enter number of elements: ");
                    int n3 = sc.nextInt();
                    int[] arr3 = new int[n3];
                    System.out.println("Enter " + n3 + " numbers:");
                    for (int i = 0; i < n3; i++) arr3[i] = sc.nextInt();

                    System.out.print("Enter k (1-based, which element to find): ");
                    int k = sc.nextInt();
                    if (k < 1 || k > n3) {
                        System.out.println("Error: k is out of range!");
                        break;
                    }

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    int kth = DeterministicSelect.select(arr3, k, metrics);

                    metrics.endTimer();
                    System.out.println(k + "-th smallest element: " + kth);
                    metrics.printMetrics();
                    break;
                }

                case 4: {
                    System.out.print("Enter number of points: ");
                    int n4 = sc.nextInt();
                    Point[] points = new Point[n4];
                    System.out.println("Enter " + n4 + " points (x y):");
                    for (int i = 0; i < n4; i++) {
                        double x = sc.nextDouble();
                        double y = sc.nextDouble();
                        points[i] = new Point(x, y);
                    }

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    double result = ClosestPair.closest(points, n4, metrics);

                    metrics.endTimer();
                    System.out.println("Minimum distance between a pair of points: " + result);
                    metrics.printMetrics();
                    break;
                }

                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}
