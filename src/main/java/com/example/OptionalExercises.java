package com.example;

import java.util.*;

/* 
| **Method**                      | **Description**                                                                                           | **Example**                                                    | **Notes / Behavior**                                                        |
| ------------------------------- | --------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `Optional.of(value)`            | Creates an `Optional` containing the given **non-null value**.                                            | `Optional<String> opt = Optional.of("Alice");`                 | Throws `NullPointerException` if `value` is `null`.                         |
| `Optional.ofNullable(value)`    | Creates an `Optional` containing the value if it’s **non-null**, otherwise creates an **empty Optional**. | `Optional<String> opt = Optional.ofNullable(possibleNull);`    | Safe for values that may be `null`.                                         |
| `optional.orElse(defaultValue)` | Returns the value inside the `Optional` if present, otherwise returns the **provided default value**.     | `String name = opt.orElse("Default Name");`                    | Always evaluates the argument `defaultValue`, even if Optional has a value. |
| `optional.map(Function)`        | Transforms the value inside the `Optional` using a **Function**, returning a new Optional.                | `Optional<String> upper = opt.map(String::toUpperCase);`       | Returns `Optional.empty()` if original Optional is empty.                   |
| `optional.filter(Predicate)`    | Returns the Optional if the **value satisfies the predicate**, otherwise returns empty Optional.          | `Optional<String> aName = opt.filter(n -> n.startsWith("A"));` | Useful for conditional checks inside Optional.                              |
| `optional.ifPresent(Consumer)`  | Executes the given **Consumer** if a value is present; does nothing if empty.                             | `opt.ifPresent(System.out::println);`                          | Useful for side effects without checking for null.                          |
 */

/* 
Key points:
    - Optional<T> helps avoid null checks and NullPointerExceptions
    - Supports functional-style transformations with map and flatMap
    - Can be chained for complex pipelines without nested null checks 
*/
public class OptionalExercises {

    public static void main(String[] args) {


        // --- Exercise 1: Create Optional from a value ---
        System.out.println("=== Exercise 1: Create Optional ===");
        // Throws java.lang.NullPointerException:
        //Optional<String> optionalNameNull = Optional.of(null);
        Optional<String> optionalName = Optional.of("Alice");
        optionalName.ifPresent(name -> System.out.println("Name: " + name)); // Output: Name: Alice

        // --- Exercise 2: Create empty Optional and provide default ---
        System.out.println("\n=== Exercise 2: Empty Optional with default ===");
        Optional<String> emptyOptional = Optional.empty();
        String defaultName = emptyOptional.orElse("Default Name");
        System.out.println(defaultName); // Output: Default Name

        // --- Exercise 3: Optional with orElseGet ---
        System.out.println("\n=== Exercise 3: Optional with orElseGet ===");
        Optional<String> optionalNull = Optional.ofNullable(null);
        String nameFromSupplier = optionalNull.orElseGet(() -> "Generated Name");
        System.out.println(nameFromSupplier); // Output: Generated Name

        // --- Exercise 4: Optional with orElseThrow ---
        System.out.println("\n=== Exercise 4: Optional with orElseThrow ===");
        Optional<String> optionalMissing = Optional.ofNullable(null);
        try {
            optionalMissing.orElseThrow(() -> new IllegalArgumentException("Value not present"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Output: Value not present
        }

        // --- Exercise 5: Transform Optional with map ---
        System.out.println("\n=== Exercise 5: Transform Optional with map ===");
        Optional<String> optionalLower = Optional.of("alice");
        Optional<String> optionalUpper = optionalLower.map(String::toUpperCase);
        optionalUpper.ifPresent(System.out::println); // Output: ALICE

        // --- Exercise 6: Transform Optional with flatMap ---
        System.out.println("\n=== Exercise 6: Transform Optional with flatMap ===");
        class Person {
            String name;
            Optional<String> email;
            Person(String name, String email) {
                this.name = name;
                this.email = Optional.ofNullable(email);
            }
        }

        Person p1 = new Person("Alice", "alice@example.com");
        Person p2 = new Person("Bob", null);

        // Extract email if present
        Optional<String> email1 = p1.email.flatMap(email -> Optional.of(email.toLowerCase()));
        Optional<String> email2 = p2.email.flatMap(email -> Optional.of(email.toLowerCase()));

        email1.ifPresent(e -> System.out.println("Email1: " + e)); // Output: alice@example.com
        System.out.println("Email2 is present? " + email2.isPresent()); // Output: false

        // --- Exercise 7: Filter Optional ---
        System.out.println("\n=== Exercise 7: Filter Optional ===");
        Optional<String> optionalFiltered = Optional.of("Alice");
        Optional<String> resultFiltered = optionalFiltered.filter(name -> name.startsWith("A"));
        System.out.println("Filtered present? " + resultFiltered.isPresent()); // Output: true

        Optional<String> resultFiltered2 = optionalFiltered.filter(name -> name.startsWith("B"));
        System.out.println("Filtered present? " + resultFiltered2.isPresent()); // Output: false
    }
}
