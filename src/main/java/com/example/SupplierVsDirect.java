package com.example;

import java.util.*;
import java.util.function.Supplier;

/**
Key Takeaways:
    - Supplier is useful when you want flexibility or lazy creation (e.g., generic factories, streams).
    - Direct approach is simpler and works fine for concrete types.
    - Streams and Collectors often require a Supplier (Collectors.toCollection(ArrayList::new)), which is where it becomes important.
 */
public class SupplierVsDirect {

    public static void main(String[] args) {

        // --- 1. Using Supplier ---
        System.out.println("=== Using Supplier ===");
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> supplierList = listSupplier.get(); // create new list
        supplierList.addAll(Arrays.asList("Alice", "Bob", "Charlie"));
        System.out.println("Supplier list: " + supplierList);

        // --- 2. Without Supplier (direct approach) ---
        System.out.println("\n=== Without Supplier ===");
        List<String> directList = new ArrayList<>();
        directList.addAll(Arrays.asList("Alice", "Bob", "Charlie"));
        System.out.println("Direct list: " + directList);

        // --- 3. Using Supplier in a generic method ---
        System.out.println("\n=== Generic copy method using Supplier ===");
        List<String> copiedList = copyList(directList, ArrayList::new);
        System.out.println("Copied list: " + copiedList);

        // --- 4. Generic copy without Supplier ---
        System.out.println("\n=== Generic copy method without Supplier ===");
        List<String> copiedDirect = copyListDirect(directList);
        System.out.println("Copied direct list: " + copiedDirect);
    }

    // Generic copy method using Supplier
    public static <T> List<T> copyList(List<T> source, Supplier<List<T>> factory) {
        List<T> copy = factory.get(); // use supplied factory to create list
        copy.addAll(source);
        return copy;
    }

    // Generic copy method without Supplier (just uses ArrayList)
    public static <T> List<T> copyListDirect(List<T> source) {
        List<T> copy = new ArrayList<>();
        copy.addAll(source);
        return copy;
    }
}
