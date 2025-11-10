package com.example;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
Supplier<T>

- Abstract method: `T get()`
- Purpose: Generates a value, no input.
- Exercise ideas:
    - Generate random numbers
    - Generate unique IDs for objects
    - Create new lists, sets, or custom objects lazily
 */
public class SupplierExamples {

    public static void main(String[] args) {

        // 1. Simple Supplier
        Supplier<String> helloSupplier = () -> "Hello, Supplier!";
        System.out.println(helloSupplier.get()); // prints: Hello, Supplier!

        // 2. Supplier for object creation
        Supplier<List<String>> arrayListSupplier = ArrayList::new;
        List<String> names = arrayListSupplier.get(); // creates a new ArrayList
        names.addAll(Arrays.asList("Alice", "Bob", "Charlie"));
        System.out.println("Names list: " + names);

        // 3. Supplier with Stream.collect
        List<String> shortNames = names.stream()
                                       .filter(name -> name.length() <= 4)
                                       .collect(Collectors.toCollection(ArrayList::new)); // Supplier used here
        System.out.println("Short names: " + shortNames);

        // 4. Generic method using Supplier
        List<String> copiedList = copyList(names, ArrayList::new);
        System.out.println("Copied list: " + copiedList);

        List<String> linkedListCopy = copyList(names, LinkedList::new);
        System.out.println("LinkedList copy: " + linkedListCopy);

        // 5. Lazy evaluation with Supplier
        Supplier<Double> randomSupplier = () -> Math.random(); // only generates value when get() is called
        System.out.println("Random value 1: " + randomSupplier.get());
        System.out.println("Random value 2: " + randomSupplier.get()); // different value
    }

    // Generic copy method using Supplier
    public static <T> List<T> copyList(List<T> source, Supplier<List<T>> factory) {
        List<T> copy = factory.get(); // creates the list using supplied factory
        copy.addAll(source);
        return copy;
    }
}
