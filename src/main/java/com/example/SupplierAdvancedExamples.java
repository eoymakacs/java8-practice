package com.example;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
Key Takeaways:

- Supplier is very useful in generic code, lazy evaluation, and streams/collectors.
- Without Supplier, you can do simple things, but you lose flexibility for:
    - Changing collection types
    - Lazy object generation
    - Generic factories
- Streams often require a Supplier for dynamic collection creation.
 */
public class SupplierAdvancedExamples {

    public static void main(String[] args) {

        // --- 1. Simple Supplier example ---
        Supplier<String> greetingSupplier = () -> "Hello, Supplier!";
        System.out.println(greetingSupplier.get());

        // --- 2. Lazy evaluation ---
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random 1: " + randomSupplier.get());
        System.out.println("Random 2: " + randomSupplier.get()); // generates new value

        // --- 3. Supplier for object creation ---
        Supplier<List<String>> arrayListSupplier = ArrayList::new;
        List<String> names = arrayListSupplier.get();
        names.addAll(Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve"));
        System.out.println("Names list: " + names);

        // --- 4. Using Supplier in Stream.collect ---
        List<String> shortNames = names.stream()
                                       .filter(name -> name.length() <= 4)
                                       .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Short names (<=4 letters): " + shortNames);

        // --- 5. Generic factory method with Supplier ---
        List<String> copiedList = copyList(names, ArrayList::new);
        System.out.println("Copied list via Supplier: " + copiedList);

        List<String> linkedListCopy = copyList(names, LinkedList::new);
        System.out.println("Copied list via Supplier (LinkedList): " + linkedListCopy);

        // --- 6. Without Supplier: fixed type ---
        List<String> directCopy = copyListDirect(names);
        System.out.println("Copied list without Supplier: " + directCopy);

        // --- 7. Complex example: generating objects lazily ---
        Supplier<Person> personSupplier = () -> new Person(UUID.randomUUID().toString(), "John Doe");
        Person p1 = personSupplier.get();
        Person p2 = personSupplier.get();
        System.out.println("Generated persons: " + p1 + ", " + p2);

        // --- 8. Using Supplier with Stream.generate ---
        System.out.println("\nGenerating 5 random numbers using Stream.generate and Supplier:");
        List<Double> randomNumbers = java.util.stream.Stream.generate(randomSupplier)
                                                            .limit(5)
                                                            .collect(Collectors.toList());
        System.out.println(randomNumbers);

        // --- 9. Stream.collect into different collections using Supplier ---
        Set<String> nameSet = names.stream()
                                   .filter(n -> n.startsWith("A") || n.startsWith("B"))
                                   .collect(Collectors.toCollection(HashSet::new)); // Supplier decides collection
        System.out.println("Names in HashSet: " + nameSet);
    }

    // Generic copy method using Supplier
    public static <T> List<T> copyList(List<T> source, Supplier<List<T>> factory) {
        List<T> copy = factory.get();
        copy.addAll(source);
        return copy;
    }

    // Copy method without Supplier (fixed ArrayList)
    public static <T> List<T> copyListDirect(List<T> source) {
        List<T> copy = new ArrayList<>();
        copy.addAll(source);
        return copy;
    }

    // Sample class for object generation
    static class Person {
        String id;
        String name;

        Person(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " (" + id + ")";
        }
    }
}
