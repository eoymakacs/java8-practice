package com.example;

/* 
| Type                                        | Example               |
| ------------------------------------------- | --------------------- |
| Static method reference                     | `Integer::parseInt`   |
| Instance method reference (unbound)         | `String::toUpperCase` |
| Instance method reference (bound to object) | `prefix::concat`      |
| Constructor reference                       | `ArrayList::new`      | 
*/


// MethodReferenceExamples.java
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class MethodReferenceExamples {

    public static void main(String[] args) {

        // 1. Static Method Reference
        Function<String, Integer> parseInt1 = s -> Integer.parseInt(s);
        Function<String, Integer> parseInt2 = Integer::parseInt; // method reference

        System.out.println(parseInt2.apply("42"));


        // 2. Instance Method Reference (non-bound)
        Function<String, String> toUpper1 = s -> s.toUpperCase();
        Function<String, String> toUpper2 = String::toUpperCase;

        System.out.println(toUpper2.apply("hello"));


        // 3. Instance Method Reference (bound to specific object)
        String prefix = "Hello, ";
        UnaryOperator<String> greet1 = s -> prefix.concat(s);
        UnaryOperator<String> greet2 = prefix::concat;

        System.out.println(greet2.apply("Emy"));


        // 4. Constructor Reference
        Supplier<List<String>> listSupplier1 = () -> new ArrayList<>();
        Supplier<List<String>> listSupplier2 = ArrayList::new;

        List<String> list = listSupplier2.get();
        list.add("A");
        list.add("B");
        System.out.println(list);


        // 5. Method Reference in Streams
        List<String> names = Arrays.asList("john", "JANE", "Emy", "ali");

        // Using lambda
        List<String> upper1 = names.stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());

        // Using method reference
        List<String> upper2 = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(upper2);


        // 6. Static method reference in sorting
        List<Integer> numbers = Arrays.asList(5, 1, 10, 3);

        // Lambda
        numbers.sort((a, b) -> Integer.compare(a, b));

        // Method reference (cleaner)
        numbers.sort(Integer::compare);

        System.out.println(numbers);


        // 7. Instance method reference in loop iteration
        names.forEach(System.out::println);


        // 8. Method reference to custom instance method
        MethodReferenceExamples example = new MethodReferenceExamples();
        names.forEach(example::printFormatted);
    }

    public void printFormatted(String value) {
        System.out.println("-> " + value);
    }
}
