package com.example;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
Predicate<T>

- Abstract method: `boolean test(T t)`
- Purpose: Tests a condition on a value.
- Exercise ideas:
    - Filter list of names starting with a specific letter
    - Check if numbers are even
    - Validate objects against rules

Key points:
- Predicate<T> always returns a boolean.
- Common use case: filtering collections in streams.
- Can be combined using .and(), .or(), and .negate() to create more complex conditions.
 */
public class PredicateExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Filter even numbers ---
        System.out.println("=== Exercise 1: Filter even numbers ===");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(isEven)
                                           .collect(Collectors.toList());
        System.out.println(evenNumbers); // Output: [2, 4, 6]

        // --- Exercise 2: Filter names starting with A ---
        System.out.println("\n=== Exercise 2: Names starting with A ===");
        List<String> names = Arrays.asList("Alice", "Bob", "Andrew", "Charlie");
        Predicate<String> startsWithA = name -> name.startsWith("A");
        List<String> aNames = names.stream()
                                   .filter(startsWithA)
                                   .collect(Collectors.toList());
        System.out.println(aNames); // Output: [Alice, Andrew]

        // --- Exercise 3: Combine predicates (and / or / negate) ---
        System.out.println("\n=== Exercise 3: Combined predicates ===");
        Predicate<Integer> greaterThanThree = n -> n > 3;
        Predicate<Integer> lessThanSix = n -> n < 6;

        Predicate<Integer> combined = greaterThanThree.and(lessThanSix);
        List<Integer> filtered = numbers.stream()
                                        .filter(combined)
                                        .collect(Collectors.toList());
        System.out.println(filtered); // Output: [4, 5]

        // --- Exercise 4: Negate a predicate ---
        System.out.println("\n=== Exercise 4: Negate predicate ===");
        Predicate<Integer> notEven = isEven.negate();
        List<Integer> oddNumbers = numbers.stream()
                                          .filter(notEven)
                                          .collect(Collectors.toList());
        System.out.println(oddNumbers); // Output: [1, 3, 5]

        // --- Exercise 5: Predicate with objects ---
        System.out.println("\n=== Exercise 5: Predicate with objects ===");
        class Person {
            String name;
            int age;
            Person(String name, int age) { this.name = name; this.age = age; }
            @Override
            public String toString() { return name + " (" + age + ")"; }
        }

        List<Person> people = Arrays.asList(
                new Person("Alice", 20),
                new Person("Bob", 25),
                new Person("Charlie", 30)
        );

        Predicate<Person> adult = person -> person.age >= 21;
        List<Person> adults = people.stream()
                                    .filter(adult)
                                    .collect(Collectors.toList());
        adults.forEach(System.out::println); // Output: Bob (25), Charlie (30)
    }
}
