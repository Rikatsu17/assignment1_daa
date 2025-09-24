import java.util.*;

class Point {
    double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {

    // Расстояние между двумя точками
    static double dist(Point p1, Point p2, Metrics metrics) {
        metrics.incrementComparisons();
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }

    // Brute force для маленьких массивов
    static double bruteForce(Point[] P, int n, Metrics metrics) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double d = dist(P[i], P[j], metrics);
                if (d < min) {
                    min = d;
                }
            }
        }
        return min;
    }

    // Проверка полосы (strip), но только ближайших соседей по y (до 7–8 точек)
    static double stripClosest(Point[] strip, int size, double d, Metrics metrics) {
        double min = d;

        for (int i = 0; i < size; ++i) {
            // Проверяем только ближайших соседей по y
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min && j <= i + 7; ++j) {
                double dist = dist(strip[i], strip[j], metrics);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    // Рекурсивная функция
    static double closestUtil(Point[] Px, Point[] Py, int n, int depth, Metrics metrics) {
        metrics.incrementRecursiveCalls();
        metrics.updateDepth(depth);

        if (n <= 3) return bruteForce(Px, n, metrics);

        int mid = n / 2;
        Point midPoint = Px[mid];

        // Разделяем по x
        Point[] Pyl = new Point[mid];
        Point[] Pyr = new Point[n - mid];
        int li = 0, ri = 0;
        for (int i = 0; i < n; i++) {
            if (Py[i].x <= midPoint.x && li < mid) {
                Pyl[li++] = Py[i];
            } else {
                Pyr[ri++] = Py[i];
            }
        }

        // Рекурсивный вызов
        double dl = closestUtil(Arrays.copyOfRange(Px, 0, mid), Pyl, mid, depth + 1, metrics);
        double dr = closestUtil(Arrays.copyOfRange(Px, mid, n), Pyr, n - mid, depth + 1, metrics);
        double d = Math.min(dl, dr);

        // Формируем полосу (strip)
        ArrayList<Point> strip = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Math.abs(Py[i].x - midPoint.x) < d) {
                strip.add(Py[i]);
            }
        }

        return Math.min(d, stripClosest(strip.toArray(new Point[0]), strip.size(), d, metrics));
    }

    // Входной метод
    public static double closest(Point[] P, int n, Metrics metrics) {
        Point[] Px = Arrays.copyOf(P, n);
        Arrays.sort(Px, Comparator.comparingDouble(p -> p.x));
        Point[] Py = Arrays.copyOf(P, n);
        Arrays.sort(Py, Comparator.comparingDouble(p -> p.y));

        return closestUtil(Px, Py, n, 1, metrics);
    }
}
