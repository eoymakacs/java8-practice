package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionalInterfaceExercises {
    
    public static void main(String[] args){
        // Example: Predicate<String> to filter a list of names.
        System.out.println("\n=== Exercise: Predicate<String> to filter a list of names ===");
        List<String> names = Arrays.asList("Alice", "Bob", "Andrew", "Charlie");
        Predicate<String> endsWithE = name -> name.endsWith("e");
        List<String> eNames = names.stream()
                                   .filter(endsWithE)
                                   .collect(Collectors.toList());
        System.out.println(eNames); // Output: [Alice, Charlie]

        //Example: Supplier<Double> to generate 5 random numbers.
        System.out.println("\n=== Exercise: Supplier<Double> to generate 5 random numbers ===");
        Supplier<Double> randomSupplier = () -> Math.random();
        List<Double> randomNumbers = java.util.stream.Stream.generate(randomSupplier).limit(5).collect(Collectors.toList());
        System.out.println(randomNumbers);

        // Example: List<String> → filter with Predicate, transform with Function, print with Consumer.
        System.out.println("\n=== Exercise: List<String> → filter with Predicate, transform with Function, print with Consumer ===");
        Predicate<String> startsWithA = name -> name.startsWith("A");
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        Consumer<String> printName = name -> System.out.println(name);
        names.stream().filter(startsWithA).map(toUpperCase).forEach(printName);
        
        // Example: Create a factory method that takes a Supplier<List<T>> and fills it with generated elements.
        // Generic methods + functional interfaces
        System.out.println("\n=== Exercise: Create a factory method that takes a Supplier<List<T>> and fills it with generated elements. ===");
        List<String> copiedList = copyList(names, ArrayList::new);
        System.out.println("Copied list: " + copiedList);

        // Example: Combine BinaryOperator and Predicate to reduce a list conditionally.
        System.out.println("\n=== Example: Combine BinaryOperator and Predicate to reduce a list conditionally. ===");
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        Predicate<Integer> isEven = n -> n % 2 == 0;


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // Sum using reduce
        int totalSum = numbers.stream().filter(isEven).reduce(0, sum); // identity 0
        System.out.println("Original list: " + numbers);
        System.out.println("Sum of evens: " + totalSum); // Output: 50


    }   

    // Generic copy method using Supplier
    public static <T> List<T> copyList(List<T> source, Supplier<List<T>> factory) {
        List<T> copy = factory.get(); // creates the list using supplied factory
        copy.addAll(source);
        return copy;
    }
}

