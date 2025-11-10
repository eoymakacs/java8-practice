package com.example;

import java.util.*;
import java.util.stream.*;

public class ParallelStreamDemo {

    public static void main(String[] args) {

        // Sample list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // --- Sequential Stream ---
        System.out.println("=== Sequential Stream ===");
        numbers.stream()
               .map(n -> {
                   System.out.println("Processing (sequential): " + n + " by " + Thread.currentThread().getName());
                   return n * 2;
               })
               .forEach(result -> System.out.println("Result (sequential): " + result));

        // --- Parallel Stream ---
        System.out.println("\n=== Parallel Stream ===");
        numbers.parallelStream()
               .map(n -> {
                   System.out.println("Processing (parallel): " + n + " by " + Thread.currentThread().getName());
                   return n * 2;
               })
               .forEach(result -> System.out.println("Result (parallel): " + result));
    }
}
