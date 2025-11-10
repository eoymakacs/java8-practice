package com.example;

import java.util.*;
import java.util.function.Consumer;

/**
Consumer<T>

- Abstract method: `void accept(T t)`
- Purpose: Performs an action on a single input, returns nothing.
- Exercise ideas:
    - Print all elements of a list
    - Update objects in a list (e.g., capitalize names)
    - Logging or auditing actions on objects
 */

public class ConsumerExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Print all elements in a list ---
        System.out.println("=== Exercise 1: Print all elements ===");
        List<String> names1 = Arrays.asList("Alice", "Bob", "Charlie");
        Consumer<String> printName = name -> System.out.println(name);
        names1.forEach(printName);

        // --- Exercise 2: Capitalize all names and print ---
        System.out.println("\n=== Exercise 2: Uppercase names ===");
        List<String> names2 = Arrays.asList("alice", "bob", "charlie");
        names2.forEach(name -> System.out.println(name.toUpperCase()));

        // --- Exercise 3: Print name with its length ---
        System.out.println("\n=== Exercise 3: Name with length ===");
        List<String> names3 = Arrays.asList("Alice", "Bob", "Charlie");
        Consumer<String> printNameWithLength = name -> System.out.println(name + " (" + name.length() + ")");
        names3.forEach(printNameWithLength);

        // --- Exercise 4: Chain Consumers ---
        System.out.println("\n=== Exercise 4: Chain Consumers ===");
        List<String> names4 = Arrays.asList("Alice", "Bob", "Charlie");
        Consumer<String> printUpper = name -> System.out.println(name.toUpperCase());
        Consumer<String> printLength = name -> System.out.println("Length: " + name.length());
        Consumer<String> combinedConsumer = printUpper.andThen(printLength);
        names4.forEach(combinedConsumer);

        // --- Exercise 5: Modify objects using Consumer ---
        System.out.println("\n=== Exercise 5: Modify Person objects ===");
        class Person {
            String name;
            int age;
            Person(String name, int age) { this.name = name; this.age = age; }
            @Override
            public String toString() { return name + ": " + age; }
        }

        List<Person> people = Arrays.asList(
                new Person("Alice", 20),
                new Person("Bob", 25)
        );

        Consumer<Person> incrementAge = person -> person.age += 1;
        people.forEach(incrementAge);
        people.forEach(person -> System.out.println(person));
    }
}
