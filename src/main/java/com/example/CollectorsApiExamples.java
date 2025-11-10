package com.example;

import java.util.*;
import java.util.stream.Collectors;

/* 
| Purpose                | Example                                                 |
| ---------------------- | ------------------------------------------------------- |
| Convert to List        | `collect(Collectors.toList())`                          |
| Convert to Set         | `collect(Collectors.toSet())`                           |
| Join text              | `collect(Collectors.joining(", "))`                     |
| Grouping               | `collect(Collectors.groupingBy(Person::getDepartment))` |
| Counting/Summing       | `collect(Collectors.counting())`, `summingInt(...)`     |
| Mapping inside collect | `mapping()`, `flatMapping()`                            | 
*/


// CollectorsApiExamples.java
public class CollectorsApiExamples {

    static class Person {
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

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "London"),
                new Person("Bob", 20, "London"),
                new Person("Charlie", 25, "Berlin"),
                new Person("Daniel", 30, "Berlin"),
                new Person("Eve", 35, "Paris")
        );

        // 1. Collect to List
        List<String> names =
                people.stream()
                        .map(p -> p.name)
                        .collect(Collectors.toList());
        System.out.println(names);

        // 2. Collect to Set (removes duplicates)
        Set<String> cities =
                people.stream()
                        .map(p -> p.city)
                        .collect(Collectors.toSet());
        System.out.println(cities);

        // 3. Join strings
        String joinedNames =
                people.stream()
                        .map(p -> p.name)
                        .collect(Collectors.joining(", "));
        System.out.println(joinedNames);

        // 4. Count
        long countLondon =
                people.stream()
                        .filter(p -> p.city.equals("London"))
                        .collect(Collectors.counting());
        System.out.println(countLondon);

        // 5. Group By -> Map<City, List<Person>>
        Map<String, List<Person>> groupedByCity =
                people.stream()
                        .collect(Collectors.groupingBy(p -> p.city));
        System.out.println(groupedByCity);

        // 6. Group By -> Map<City, Count>
        Map<String, Long> countByCity =
                people.stream()
                        .collect(Collectors.groupingBy(p -> p.city, Collectors.counting()));
        System.out.println(countByCity);

        // 7. Group By + Mapping -> Map<City, List<Name>>
        Map<String, List<String>> cityToNames =
                people.stream()
                        .collect(Collectors.groupingBy(p -> p.city,
                                Collectors.mapping(p -> p.name, Collectors.toList())));
        System.out.println(cityToNames);

        // 8. Averaging (average ages)
        double averageAge =
                people.stream()
                        .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);

        // 9. Summing (sum ages)
        int sumAges =
                people.stream()
                        .collect(Collectors.summingInt(p -> p.age));
        System.out.println(sumAges);

        // 10. Max/Min using Collectors.maxBy / minBy
        Optional<Person> oldest =
                people.stream()
                        .collect(Collectors.maxBy(Comparator.comparingInt(p -> p.age)));
        oldest.ifPresent(System.out::println);

        // 11. Convert to Map<name, city>
        Map<String, String> nameToCity =
                people.stream()
                        .collect(Collectors.toMap(p -> p.name, p -> p.city));
        System.out.println(nameToCity);

        // 12. Partitioning -> Two groups (age >= 30 and age < 30)
        Map<Boolean, List<Person>> partitioned =
                people.stream()
                        .collect(Collectors.partitioningBy(p -> p.age >= 30));
        System.out.println(partitioned);
    }
}
