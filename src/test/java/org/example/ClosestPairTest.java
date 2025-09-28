package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testTwoPoints() {
        Point[] points = { new Point(0, 0), new Point(3, 4) };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closest(points, points.length, metrics);
        assertEquals(5.0, result, 1e-9);
    }

    @Test
    void testThreePoints() {
        Point[] points = { new Point(0, 0), new Point(1, 1), new Point(2, 2) };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closest(points, points.length, metrics);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testSquarePoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 0),
                new Point(1, 1)
        };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closest(points, points.length, metrics);
        assertEquals(1.0, result, 1e-9);
    }

    @Test
    void testNegativeCoordinates() {
        Point[] points = {
                new Point(-1, -1),
                new Point(-2, -3),
                new Point(4, 5),
                new Point(3, 4)
        };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closest(points, points.length, metrics);
        assertEquals(Math.sqrt(2), result, 1e-9);
    }

    @Test
    void testRandomCluster() {
        Point[] points = {
                new Point(10, 10),
                new Point(10.5, 10.5),
                new Point(20, 20),
                new Point(-5, -7)
        };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closest(points, points.length, metrics);
        assertEquals(Math.sqrt(0.5*0.5 + 0.5*0.5), result, 1e-9); // расстояние между (10,10) и (10.5,10.5)
    }

    @Test
    void testIdenticalPoints() {
        Point[] points = {
                new Point(2, 3),
                new Point(2, 3),
                new Point(5, 5)
        };
        Metrics metrics = new Metrics();
        double result = ClosestPair.closest(points, points.length, metrics);
        assertEquals(0.0, result, 1e-9);
    }
}
