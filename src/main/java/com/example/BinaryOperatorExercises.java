package com.example;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
BinaryOperator<T>

- Abstract method: `T apply(T t1, T t2)`
- Purpose: Special case of BiFunction where input and output are the same type.
- Exercise ideas:
    - Sum two integers
    - Concatenate two strings
    - Combine two objects of the same type

Key points:
- BinaryOperator<T> = BiFunction<T, T, T>
- Useful for reductions, combining elements, or custom aggregation in streams
- Works for primitive types, strings, and custom objects
- Integrates naturally with Stream.reduce
 */
public class BinaryOperatorExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Sum two integers ---
        System.out.println("=== Exercise 1: Sum two integers ===");
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("5 + 10 = " + sum.apply(5, 10)); // Output: 15

        // --- Exercise 2: Multiply two integers ---
        System.out.println("\n=== Exercise 2: Multiply two integers ===");
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println("3 * 4 = " + multiply.apply(3, 4)); // Output: 12

        // --- Exercise 3: Concatenate two strings ---
        System.out.println("\n=== Exercise 3: Concatenate two strings ===");
        BinaryOperator<String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println(concat.apply("Hello", "World")); // Output: Hello World

        // --- Exercise 4: Find max in two numbers ---
        System.out.println("\n=== Exercise 4: Find max of two numbers ===");
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("Max of 7 and 10: " + max.apply(7, 10)); // Output: 10

        // --- Exercise 5: Reduce a list using BinaryOperator ---
        System.out.println("\n=== Exercise 5: Reduce a list ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Sum using reduce
        int totalSum = numbers.stream()
                              .reduce(0, sum); // identity 0
        System.out.println("Total sum: " + totalSum); // Output: 15

        // Multiply using reduce
        int totalProduct = numbers.stream()
                                  .reduce(1, multiply); // identity 1
        System.out.println("Total product: " + totalProduct); // Output: 120

        // --- Exercise 6: BinaryOperator chaining using andThen? ---
        System.out.println("\n=== Exercise 6: Custom object combining ===");
        class Point {
            int x, y;
            Point(int x, int y) { this.x = x; this.y = y; }
            @Override
            public String toString() { return "(" + x + ", " + y + ")"; }
        }

        BinaryOperator<Point> combinePoints = (p1, p2) -> new Point(p1.x + p2.x, p1.y + p2.y);
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 4);
        Point p3 = combinePoints.apply(p1, p2);
        System.out.println("Combined point: " + p3); // Output: (4, 6)

        // --- Exercise 7: Max string by length ---
        System.out.println("\n=== Exercise 7: Max string by length ===");
        List<String> words = Arrays.asList("apple", "banana", "kiwi", "strawberry");
        BinaryOperator<String> maxByLength = (s1, s2) -> s1.length() >= s2.length() ? s1 : s2;

        String longest = words.stream()
                              .reduce(maxByLength)
                              .orElse(""); // returns empty string if list is empty
        System.out.println("Longest word: " + longest); // Output: strawberry
    }
}
