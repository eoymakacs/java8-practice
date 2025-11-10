package com.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 1. Lambda with custom functional interface
        Add add = (a, b) -> a + b;
        System.out.println("Sum: " + add.addition(10, 20));

        // 2. Method references
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Zeki");
        names.forEach(System.out::println); // Instance method reference

        // 3. Streams
        List<String> shortNames = names.stream()
                                       .filter(name -> name.length() <= 4)
                                       .collect(Collectors.toList());
        System.out.println("Short names: " + shortNames);

        // 4. Optional
        Optional<String> maybeName = names.stream()
                                          .filter(name -> name.startsWith("Z"))
                                          .findFirst();
        System.out.println("Name starting with Z: " + maybeName.orElse("Not found"));

        // 5. Built-in functional interfaces
        Predicate<String> startsWithA = name -> name.startsWith("A");
        Function<String, String> toUpperCase = String::toUpperCase;
        Consumer<String> print = System.out::println;

        System.out.println("Names starting with A in uppercase:");
        names.stream()
             .filter(startsWithA)
             .map(toUpperCase)
             .forEach(print);

        // Rewritten using explicit lambdas:
        names.stream()
            .filter(name -> name.startsWith("A"))  // Predicate as lambda
            .map(name -> name.toUpperCase())              // Function as lambda
            .forEach(name -> System.out.println(name));   // Consumer as lambda

        // Optional: You can still keep method references for brevity:
        names.stream()
            .filter(name -> name.startsWith("A"))
            .map(String::toUpperCase)
            .forEach(System.out::println);

        // 6. Supplier
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.addAll(names);

        // 7. BinaryOperator
        BinaryOperator<Integer> multiply = (x, y) -> x * y;
        System.out.println("Multiply 5 * 6 = " + multiply.apply(5, 6));
    }

    // Custom functional interface
    @FunctionalInterface
    interface Add {
        int addition(int a, int b);
    }
}
