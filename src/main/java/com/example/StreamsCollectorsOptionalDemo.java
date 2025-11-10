package com.example;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
Key points:
    - Streams + Collectors + Optional can be chained together for powerful data processing.
    - Optional ensures safe handling of missing elements.
    - reduce() is useful for aggregations like sum, max, or custom reductions.
    - groupingBy and counting provide summary statistics easily.
 */

public class StreamsCollectorsOptionalDemo {

    public static void main(String[] args) {

        // --- Sample data ---
        class Person {
            String name;
            int age;
            String city;
            Person(String name, int age, String city) {
                this.name = name;
                this.age = age;
                this.city = city;
            }
            @Override
            public String toString() {
                return name + " (" + age + ", " + city + ")";
            }
        }

        List<Person> people = Arrays.asList(
                new Person("Alice", 23, "New York"),
                new Person("Bob", 30, "Chicago"),
                new Person("Charlie", 25, "New York"),
                new Person("David", 30, "Chicago"),
                new Person("Eve", 35, "Boston"),
                new Person("Frank", 28, "New York"),
                new Person("Grace", 23, "Boston")
        );

        // --- 1. Filtering → Mapping → Grouping ---
        System.out.println("=== 1. Filtering → Mapping → Grouping ===");

        // Filter people older than 25, map to their names, group by city
        Map<String, List<String>> namesByCity = people.stream()
                .filter(p -> p.age > 25)                 // filtering
                .map(p -> p.name)                        // mapping
                .collect(Collectors.groupingBy(
                        name -> people.stream()
                                       .filter(p -> p.name.equals(name))
                                       .findFirst()
                                       .get().city
                ));                                      // grouping

        namesByCity.forEach((city, names) -> 
                System.out.println(city + " -> " + names));

        // --- 2. Safe extraction with Optional ---
        System.out.println("\n=== 2. Safe extraction with Optional ===");

        // Find the first person in New York older than 40
        Optional<Person> maybePerson = people.stream()
                .filter(p -> p.city.equals("New York") && p.age > 40)
                .findFirst();

        // Safe extraction
        String nameOrDefault = maybePerson.map(p -> p.name)
                                          .orElse("No person found");
        System.out.println("Person found: " + nameOrDefault);

        // --- 3. Reducing collections into summaries ---
        System.out.println("\n=== 3. Reducing collections into summaries ===");

        // Total age of all people
        int totalAge = people.stream()
                             .map(p -> p.age)
                             .reduce(0, Integer::sum);
        System.out.println("Total age: " + totalAge);

        // Find the oldest person using reduce
        Optional<Person> oldest = people.stream()
                                        .reduce((p1, p2) -> p1.age >= p2.age ? p1 : p2);
        oldest.ifPresent(p -> System.out.println("Oldest person: " + p));

        // Count of people in each city
        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(p -> p.city, Collectors.counting()));
        System.out.println("Count by city: " + countByCity);
    }
}
