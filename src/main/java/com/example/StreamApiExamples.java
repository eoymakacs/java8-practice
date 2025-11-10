package com.example;

/* 
| Category     | Operation              | Type (Intermediate / Terminal) | Description                                      | Example                                       |
| ------------ | ---------------------- | ------------------------------ | ------------------------------------------------ | --------------------------------------------- |
| Creation     | `stream()`             | N/A                            | Creates a stream from a collection               | `list.stream()`                               |
| Creation     | `Stream.of()`          | N/A                            | Creates a stream from values                     | `Stream.of(1,2,3)`                            |
| Intermediate | `filter(Predicate)`    | Intermediate                   | Keeps only elements matching a condition         | `stream.filter(x -> x > 10)`                  |
| Intermediate | `map(Function)`        | Intermediate                   | Transforms each element                          | `stream.map(String::toUpperCase)`             |
| Intermediate | `flatMap(Function)`    | Intermediate                   | Flattens nested streams/collections              | `listOfLists.stream().flatMap(List::stream)`  |
| Intermediate | `distinct()`           | Intermediate                   | Removes duplicates (uses `equals()`)             | `stream.distinct()`                           |
| Intermediate | `sorted()`             | Intermediate                   | Sorts stream elements                            | `stream.sorted()`                             |
| Intermediate | `sorted(Comparator)`   | Intermediate                   | Sorts using a custom comparator                  | `stream.sorted(Comparator.comparing(x -> x))` |
| Intermediate | `peek(Consumer)`       | Intermediate                   | Executes action (usually for debugging)          | `stream.peek(System.out::println)`            |
| Intermediate | `limit(long)`          | Intermediate                   | Restricts stream to N elements                   | `stream.limit(5)`                             |
| Intermediate | `skip(long)`           | Intermediate                   | Skips first N elements                           | `stream.skip(2)`                              |
| Terminal     | `collect(Collector)`   | Terminal                       | Converts stream to List/Set/Map, etc.            | `stream.collect(Collectors.toList())`         |
| Terminal     | `reduce()`             | Terminal                       | Reduces stream to a single value (sum, etc.)     | `stream.reduce(0, Integer::sum)`              |
| Terminal     | `forEach(Consumer)`    | Terminal                       | Iterates over elements                           | `stream.forEach(System.out::println)`         |
| Terminal     | `count()`              | Terminal                       | Counts number of elements                        | `stream.count()`                              |
| Terminal     | `findFirst()`          | Terminal                       | Returns the first element wrapped in `Optional`  | `stream.findFirst()`                          |
| Terminal     | `findAny()`            | Terminal                       | Returns any element (useful in parallel streams) | `stream.findAny()`                            |
| Terminal     | `anyMatch(Predicate)`  | Terminal                       | Returns true if any element matches              | `stream.anyMatch(x -> x > 10)`                |
| Terminal     | `allMatch(Predicate)`  | Terminal                       | Returns true if all match                        | `stream.allMatch(x -> x > 10)`                |
| Terminal     | `noneMatch(Predicate)` | Terminal                       | Returns true if none match                       | `stream.noneMatch(x -> x > 10)`               |
| Terminal     | `toArray()`            | Terminal                       | Converts stream to array                         | `stream.toArray(String[]::new)`               |
 */

// StreamApiExamples.java
import java.util.*;
import java.util.stream.*;

public class StreamApiExamples {

    static class Person {
        String name;
        int age;
        String city;

        Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

        public String toString() {
            return name + " (" + age + ", " + city + ")";
        }
    }

    public static void main(String[] args) {

        List<String> names = Arrays.asList("alice", "bob", "ALICE", "charlie", "bob");
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "London"),
                new Person("Bob", 20, "Paris"),
                new Person("Charlie", 25, "Berlin"),
                new Person("Bob", 20, "Paris")
        );

        // 1. filter + map + distinct + sorted + collect
        List<String> processedNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(processedNames);

        // 2. peek to observe transformations (debugging)
        names.stream()
                .map(String::toUpperCase)
                .peek(val -> System.out.println("Debug: " + val))
                .collect(Collectors.toList());

        // 3. flatMap: convert List<List<Integer>> → List<Integer>
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5)
        );
        List<Integer> flat = nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(flat);

        // 4. Extract fields from objects (map)
        List<String> personNames = people.stream()
                .map(p -> p.name)
                .collect(Collectors.toList());
        System.out.println(personNames);

        // 5. Business rule with Predicate (filter)
        List<Person> adults = people.stream()
                .filter(p -> p.age >= 25)
                .collect(Collectors.toList());
        System.out.println(adults);

        // 6. reduce (sum of integer list)
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // 7. forEach (terminal)
        people.stream().forEach(System.out::println);

        // 8. count, findFirst, anyMatch
        long countLondon = people.stream()
                .filter(p -> p.city.equals("London"))
                .count();
        System.out.println(countLondon);

        Optional<Person> firstParisResident = people.stream()
                .filter(p -> p.city.equals("Paris"))
                .findFirst();
        firstParisResident.ifPresent(System.out::println);

        boolean hasTeenager = people.stream()
                .anyMatch(p -> p.age < 20);
        System.out.println(hasTeenager);
    }
}

