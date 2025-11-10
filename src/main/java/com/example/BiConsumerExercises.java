package com.example;

import java.util.*;
import java.util.function.BiConsumer;

/**
BiConsumer<T, U>

- Abstract method: `void accept(T t, U u)`
- Purpose: Performs an action on two inputs, returns nothing.
- Exercise ideas:
    - Merge two maps
    - Print key-value pairs
    - Update object fields using two inputs
 */
public class BiConsumerExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Print key-value pairs in a map ---
        System.out.println("=== Exercise 1: Print key-value pairs ===");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.put("Charlie", 95);

        BiConsumer<String, Integer> printEntry = (name, score) -> System.out.println(name + " -> " + score);
        scores.forEach(printEntry);

        // --- Exercise 2: Increase each score by a value and print ---
        System.out.println("\n=== Exercise 2: Increase score ===");
        int bonus = 5;
        BiConsumer<String, Integer> increaseAndPrint = (name, score) -> 
            System.out.println(name + " new score: " + (score + bonus));
        scores.forEach(increaseAndPrint);

        // --- Exercise 3: Chain BiConsumers ---
        System.out.println("\n=== Exercise 3: Chain BiConsumers ===");
        BiConsumer<String, Integer> printName = (name, score) -> System.out.println("Name: " + name);
        BiConsumer<String, Integer> printScore = (name, score) -> System.out.println("Score: " + score);
        BiConsumer<String, Integer> combined = printName.andThen(printScore);
        scores.forEach(combined);

        // --- Exercise 4: Modify objects using BiConsumer ---
        System.out.println("\n=== Exercise 4: Update Person objects ===");
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

        // BiConsumer that updates age using a given increment
        BiConsumer<Person, Integer> increaseAge = (person, increment) -> person.age += increment;
        people.forEach(person -> increaseAge.accept(person, 2));
        people.forEach(System.out::println);

        // --- Exercise 5: Map of objects with BiConsumer ---
        System.out.println("\n=== Exercise 5: Map of objects ===");
        Map<String, Person> personMap = new HashMap<>();
        personMap.put("A", new Person("Alice", 20));
        personMap.put("B", new Person("Bob", 25));

        BiConsumer<String, Person> updateAndPrint = (key, person) -> {
            person.age += 1;
            System.out.println(key + ": " + person);
        };
        personMap.forEach(updateAndPrint);
    }
}
