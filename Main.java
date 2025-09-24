import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Меню алгоритмов ===");
            System.out.println("1. MergeSort");
            System.out.println("2. QuickSort");
            System.out.println("3. Median of Medians (k-й элемент)");
            System.out.println("4. Closest Pair of Points");
            System.out.println("0. Выход");
            System.out.print("Выберите алгоритм: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Попробуйте ещё раз.");
                sc.nextLine();
                continue;
            }

            if (choice == 0) {
                System.out.println("Выход...");
                break;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Введите количество элементов: ");
                    int n1 = sc.nextInt();
                    int[] arr1 = new int[n1];
                    System.out.println("Введите " + n1 + " чисел:");
                    for (int i = 0; i < n1; i++) arr1[i] = sc.nextInt();

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    MergeSort.mergeSort(arr1, 0, n1 - 1, 0,1, metrics);

                    metrics.endTimer();
                    System.out.println("Отсортированный массив:");
                    System.out.println(Arrays.toString(arr1));
                    metrics.printMetrics();
                    break;
                }

                case 2: {
                    System.out.print("Введите количество элементов: ");
                    int n2 = sc.nextInt();
                    int[] arr2 = new int[n2];
                    System.out.println("Введите " + n2 + " чисел:");
                    for (int i = 0; i < n2; i++) arr2[i] = sc.nextInt();

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    QuickSort.quickSort(arr2, 0, n2 - 1, 1, metrics);

                    metrics.endTimer();
                    System.out.println("Отсортированный массив:");
                    System.out.println(Arrays.toString(arr2));
                    metrics.printMetrics();
                    break;
                }

                case 3: {
                    System.out.print("Введите количество элементов: ");
                    int n3 = sc.nextInt();
                    int[] arr3 = new int[n3];
                    System.out.println("Введите " + n3 + " чисел:");
                    for (int i = 0; i < n3; i++) arr3[i] = sc.nextInt();

                    System.out.print("Введите k (какой по порядку элемент найти, 1-based): ");
                    int k = sc.nextInt();

                    Metrics metrics = new Metrics();
                    metrics.reset();
                    metrics.startTimer();

                    int kth = DeterministicSelect.select(arr3, k, 1, metrics);

                    metrics.endTimer();
                    System.out.println(k + "-й наименьший элемент: " + kth);
                    metrics.printMetrics();
                    break;
                }

                case 4: {
                    System.out.print("Введите количество точек: ");
                    int n4 = sc.nextInt();
                    Point[] points = new Point[n4];
                    System.out.println("Введите " + n4 + " точек (x y):");
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
                    System.out.println("Минимальное расстояние между парой точек: " + result);
                    metrics.printMetrics();
                    break;
                }

                default:
                    System.out.println("Неверный выбор!");
            }
        }
        sc.close();
    }
}
