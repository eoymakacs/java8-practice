package com.example;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
UnaryOperator<T>

- Abstract method: `T apply(T t)`
- Purpose: Special case of Function where input and output are the same type.
- Exercise ideas:
    - Increment numbers
    - Convert strings to uppercase
    - Apply transformations to objects

Key points:
- UnaryOperator<T> = Function<T, T>
- Very convenient for transforming data in streams when input and output types are the same.
- Supports chaining using andThen().
 */
public class UnaryOperatorExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Increment integers ---
        System.out.println("=== Exercise 1: Increment integers ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        UnaryOperator<Integer> increment = n -> n + 1;
        List<Integer> incrementedNumbers = numbers.stream()
                                                  .map(increment)
                                                  .collect(Collectors.toList());
        System.out.println(incrementedNumbers); // Output: [2, 3, 4, 5, 6]

        // --- Exercise 2: Uppercase strings ---
        System.out.println("\n=== Exercise 2: Uppercase strings ===");
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        UnaryOperator<String> toUpperCase = str -> str.toUpperCase();
        List<String> upperNames = names.stream()
                                       .map(toUpperCase)
                                       .collect(Collectors.toList());
        System.out.println(upperNames); // Output: [ALICE, BOB, CHARLIE]

        // --- Exercise 3: Add prefix to strings ---
        System.out.println("\n=== Exercise 3: Add prefix to strings ===");
        UnaryOperator<String> addPrefix = str -> "Mr/Ms. " + str;
        List<String> prefixedNames = names.stream()
                                          .map(addPrefix)
                                          .collect(Collectors.toList());
        System.out.println(prefixedNames); // Output: [Mr/Ms. alice, Mr/Ms. bob, Mr/Ms. charlie]

        // --- Exercise 4: Square numbers ---
        System.out.println("\n=== Exercise 4: Square numbers ===");
        UnaryOperator<Integer> square = n -> n * n;
        List<Integer> squaredNumbers = numbers.stream()
                                              .map(square)
                                              .collect(Collectors.toList());
        System.out.println(squaredNumbers); // Output: [1, 4, 9, 16, 25]

        // --- Exercise 5: UnaryOperator chaining ---
        System.out.println("\n=== Exercise 5: UnaryOperator chaining ===");
        UnaryOperator<Integer> multiplyByTwo = n -> n * 2;
        UnaryOperator<Integer> incrementThenDouble = (UnaryOperator<Integer>) increment.andThen(multiplyByTwo);

        List<Integer> result = numbers.stream()
                                      .map(incrementThenDouble)
                                      .collect(Collectors.toList());
        System.out.println(result); // Output: [4, 6, 8, 10, 12]
    }
}
