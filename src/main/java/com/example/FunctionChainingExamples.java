package com.example;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/*
| Concept                              | Example in File                | Purpose                    |
| ------------------------------------ | ------------------------------ | -------------------------- |
| `Function.andThen()`                 | `(x + 1).andThen(x * 2)`       | Do this, *then* that       |
| `Function.compose()`                 | `(x * 2).compose(x + 1)`       | Do the next function first |
| `Predicate.and()`                    | `isEven.and(isGreaterThanTen)` | Logical AND                |
| `Predicate.or()`                     | `isEven.or(isGreaterThanTen)`  | Logical OR                 |
| `Predicate.negate()`                 | `isEven.negate()`              | Logical NOT                |
| `Consumer.andThen()`                 | Print then print length        | Sequence side effects      |
| `Comparator.thenComparing()`         | Name then age sorting          | Multi-field sort           |
| `BinaryOperator.maxBy()` / `minBy()` | Pick larger / smaller          | Conditional reduction      | 
*/


public class FunctionChainingExamples {

    public static void main(String[] args) {

        // ===========================
        // 1. Function: andThen & compose
        // ===========================
        Function<Integer, Integer> increment = x -> x + 1;
        Function<Integer, Integer> doubleValue = x -> x * 2;

        System.out.println("Function andThen: (5+1)*2 = " + increment.andThen(doubleValue).apply(5));
        System.out.println("Function compose: (5*2)+1 = " + increment.compose(doubleValue).apply(5));

        // ===========================
        // 2. Predicate: and, or, negate
        // ===========================
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> isGreaterThanTen = x -> x > 10;

        System.out.println("\nPredicate and (12 is even & > 10): " + isEven.and(isGreaterThanTen).test(12));
        System.out.println("Predicate or (9 is even or > 10): " + isEven.or(isGreaterThanTen).test(9));
        System.out.println("Predicate negate (!even): " + isEven.negate().test(7));

        // ===========================
        // 3. Consumer: andThen
        // ===========================
        Consumer<String> print = s -> System.out.println("Output: " + s);
        Consumer<String> printLength = s -> System.out.println("Length: " + s.length());

        System.out.println("\nConsumer andThen:");
        print.andThen(printLength).accept("Hello");

        // ===========================
        // 4. Comparator: thenComparing
        // ===========================
        System.out.println("\nComparator thenComparing:");

        class Person {
            String name;
            int age;
            Person(String name, int age) { this.name = name; this.age = age; }
            public String toString() { return name + " (" + age + ")"; }
        }

        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Bob", 20),
                new Person("Charlie", 25)
        );

        Comparator<Person> comparator = Comparator.comparing((Person p) -> p.name).thenComparingInt(p -> p.age);

        List<Person> sorted = people.stream().sorted(comparator).collect(Collectors.toList());
        sorted.forEach(System.out::println);

        // ===========================
        // 5. BinaryOperator: maxBy & minBy
        // ===========================
        System.out.println("\nBinaryOperator maxBy & minBy:");

        Comparator<Integer> naturalOrder = Integer::compareTo;

        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(naturalOrder);
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(naturalOrder);

        System.out.println("Max(8, 3): " + maxBy.apply(8, 3));
        System.out.println("Min(8, 3): " + minBy.apply(8, 3));
    }
}
