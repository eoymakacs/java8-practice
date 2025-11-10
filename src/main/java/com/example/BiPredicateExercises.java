package com.example;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
BiPredicate<T, U>

- Abstract method: `boolean test(T t, U u)`
- Purpose: Tests a condition using two inputs.
- Exercise ideas:
    - Compare two strings for equality ignoring case
    - Check if two numbers satisfy a condition (e.g., sum > 10)

Key points:
- BiPredicate<T, U> always returns a boolean for two inputs.
- Very useful for comparisons, validations, and filtering data structures with two values.
- Can be chained using and, or, negate like Predicate.
 */
public class BiPredicateExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Compare two integers ---
        System.out.println("=== Exercise 1: Compare two integers ===");
        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
        System.out.println("5 > 3? " + isGreater.test(5, 3)); // Output: true
        System.out.println("2 > 4? " + isGreater.test(2, 4)); // Output: false

        // --- Exercise 2: Check string equality ignoring case ---
        System.out.println("\n=== Exercise 2: String equality ignoring case ===");
        BiPredicate<String, String> equalsIgnoreCase = (s1, s2) -> s1.equalsIgnoreCase(s2);
        System.out.println("hello vs HELLO? " + equalsIgnoreCase.test("hello", "HELLO")); // true
        System.out.println("hello vs world? " + equalsIgnoreCase.test("hello", "world")); // false

        // --- Exercise 3: Combine BiPredicates ---
        System.out.println("\n=== Exercise 3: Combine BiPredicates ===");
        BiPredicate<Integer, Integer> bothPositive = (a, b) -> a > 0 && b > 0;
        BiPredicate<Integer, Integer> sumGreaterThanTen = (a, b) -> (a + b) > 10;
        BiPredicate<Integer, Integer> combined = bothPositive.and(sumGreaterThanTen);

        System.out.println("5, 7: " + combined.test(5, 7)); // true
        System.out.println("3, 4: " + combined.test(3, 4)); // false

        // --- Exercise 4: BiPredicate with objects ---
        System.out.println("\n=== Exercise 4: BiPredicate with objects ===");
        class Person {
            String name;
            int age;
            Person(String name, int age) { this.name = name; this.age = age; }
            @Override
            public String toString() { return name + " (" + age + ")"; }
        }

        Person alice = new Person("Alice", 20);
        Person bob = new Person("Bob", 25);

        BiPredicate<Person, Person> sameAge = (p1, p2) -> p1.age == p2.age;
        System.out.println("Alice and Bob same age? " + sameAge.test(alice, bob)); // false

        Person anotherAlice = new Person("Alice2", 20);
        System.out.println("Alice and AnotherAlice same age? " + sameAge.test(alice, anotherAlice)); // true

        // --- Exercise 5: Filter a list of pairs with BiPredicate ---
        System.out.println("\n=== Exercise 5: Filter pairs with BiPredicate ===");
        List<int[]> pairs = Arrays.asList(
                new int[]{1, 2},
                new int[]{5, 10},
                new int[]{6, 7},
                new int[]{3, 8}
        );

        BiPredicate<Integer, Integer> sumGreaterThanTenPredicate = (a, b) -> (a + b) > 10;
        List<int[]> filteredPairs = pairs.stream()
                                         .filter(pair -> sumGreaterThanTenPredicate.test(pair[0], pair[1]))
                                         .collect(Collectors.toList());

        filteredPairs.forEach(pair -> System.out.println(Arrays.toString(pair))); // Output: [5, 10], [6, 7], [3, 8]
    }
}
