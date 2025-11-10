package com.example;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
BiFunction<T, U, R>

- Abstract method: `R apply(T t, U u)`
- Purpose: Takes two inputs and returns a result.
- Exercise ideas:
    - Sum two integers
    - Combine two strings
    - Merge two objects into one

Key points:
- BiFunction is useful whenever you have two input parameters and want to return a value.
- Works well in object creation, calculations, and combining data.
- Can be integrated into streams, but element-wise operations require careful handling (like in exercise 5).
 */
public class BiFunctionExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Sum two integers ---
        System.out.println("=== Exercise 1: Sum two integers ===");
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println("5 + 10 = " + sum.apply(5, 10)); // Output: 15

        // --- Exercise 2: Concatenate two strings ---
        System.out.println("\n=== Exercise 2: Concatenate two strings ===");
        BiFunction<String, String, String> concat = (s1, s2) -> s1 + " " + s2;
        System.out.println(concat.apply("Hello", "World")); // Output: Hello World

        // --- Exercise 3: Create a Map entry from two values ---
        System.out.println("\n=== Exercise 3: Map entry from two values ===");
        BiFunction<String, Integer, Map.Entry<String, Integer>> toEntry = 
                (name, score) -> new AbstractMap.SimpleEntry<>(name, score);
        Map.Entry<String, Integer> entry = toEntry.apply("Alice", 95);
        System.out.println(entry.getKey() + " -> " + entry.getValue()); // Output: Alice -> 95

        // --- Exercise 4: Combine two objects into a new object ---
        System.out.println("\n=== Exercise 4: Combine objects into a new object ===");
        class Person {
            String firstName;
            String lastName;
            Person(String firstName, String lastName) { this.firstName = firstName; this.lastName = lastName; }
            @Override
            public String toString() { return firstName + " " + lastName; }
        }

        BiFunction<String, String, Person> createPerson = (first, last) -> new Person(first, last);
        Person p = createPerson.apply("John", "Doe");
        System.out.println(p); // Output: John Doe

        // --- Exercise 5: BiFunction in a stream ---
        System.out.println("\n=== Exercise 5: BiFunction with Stream ===");
        List<String> firstNames = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> lastNames = Arrays.asList("Smith", "Johnson", "Brown");

        BiFunction<String, String, String> fullName = (first, last) -> first + " " + last;

        // Combine two lists element-wise
        List<String> combinedNames = new ArrayList<>();
        for (int i = 0; i < Math.min(firstNames.size(), lastNames.size()); i++) {
            combinedNames.add(fullName.apply(firstNames.get(i), lastNames.get(i)));
        }

        System.out.println(combinedNames); // Output: [Alice Smith, Bob Johnson, Charlie Brown]

        // --- Exercise 6: BiFunction with calculations ---
        System.out.println("\n=== Exercise 6: BiFunction for calculations ===");
        BiFunction<Double, Double, Double> hypotenuse = (a, b) -> Math.sqrt(a * a + b * b);
        System.out.println("Hypotenuse of 3 and 4: " + hypotenuse.apply(3.0, 4.0)); // Output: 5.0
    }
}
