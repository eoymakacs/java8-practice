package com.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
Function<T, R>

- Abstract method: `R apply(T t)`
- Purpose: Transforms a value of type T to type R.
- Exercise ideas:
    - Convert String to Integer
    - Extract a property from an object
    - Map objects to DTOs
 */
public class FunctionExercises {

    public static void main(String[] args) {

        // --- Exercise 1: Convert String to Integer ---
        System.out.println("=== Exercise 1: String to Integer ===");
        List<String> numbersStr = Arrays.asList("1", "2", "3", "10");
        Function<String, Integer> toInteger = str -> Integer.parseInt(str);
        List<Integer> numbers = numbersStr.stream()
                                          .map(toInteger)
                                          .collect(Collectors.toList());
        System.out.println(numbers); // Output: [1, 2, 3, 10]

        // --- Exercise 2: Convert names to uppercase ---
        System.out.println("\n=== Exercise 2: Names to uppercase ===");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        List<String> upperNames = names.stream()
                                       .map(toUpperCase)
                                       .collect(Collectors.toList());
        System.out.println(upperNames); // Output: [ALICE, BOB, CHARLIE]

        // --- Exercise 3: Extract property from object ---
        System.out.println("\n=== Exercise 3: Extract property from object ===");
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

        Function<Person, String> getName = person -> person.name;
        List<String> personNames = people.stream()
                                         .map(getName)
                                         .collect(Collectors.toList());
        System.out.println(personNames); // Output: [Alice, Bob, Charlie]

        // --- Exercise 4: Function chaining ---
        System.out.println("\n=== Exercise 4: Function chaining ===");
        Function<String, String> addHello = str -> "Hello, " + str;
        Function<String, String> addExclamation = str -> str + "!";
        Function<String, String> combinedFunction = addHello.andThen(addExclamation);

        List<String> greetings = names.stream()
                                      .map(combinedFunction)
                                      .collect(Collectors.toList());
        System.out.println(greetings); // Output: [Hello, Alice!, Hello, Bob!, Hello, Charlie!]

        // --- Exercise 5: Map objects to DTO ---
        System.out.println("\n=== Exercise 5: Map objects to DTO ===");
        class PersonDTO {
            String name;
            PersonDTO(String name) { this.name = name; }
            @Override
            public String toString() { return "DTO(" + name + ")"; }
        }

        Function<Person, PersonDTO> toDTO = person -> new PersonDTO(person.name);
        List<PersonDTO> dtos = people.stream()
                                     .map(toDTO)
                                     .collect(Collectors.toList());
        dtos.forEach(System.out::println); // Output: DTO(Alice), DTO(Bob), DTO(Charlie)
    }
}
