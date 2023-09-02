package org.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestFunctional {
    public static void main(String[] args) {
        List<Person> people = getPeople();

        //Imperative approach ❌
        /*List<Person> females = new ArrayList<>();

        for (Person person:people) {
            if(person.getGender().equals(Gender.FEMALE))
                females.add(person);
        }

        females.forEach(System.out::println);*/

        //Declarative approach ✅
        //Filter
        List<Person> females = people.stream() //Abstraction
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        //females.forEach(System.out::println);

        //Sort
        List<Person> sorted =people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        //sorted.forEach(System.out::println);

        //All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge()>30);
        //System.out.println(allMatch);

        //Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge()<10);
        //System.out.println(anyMatch);

        //None match
        people.stream()
                .noneMatch(person -> person.getName().equals("Spider-Man"));

        //Max
        people.stream()
                .max(Comparator.comparing(Person::getAge));
                //.ifPresent(person -> System.out.println(person));
                //.ifPresent(System.out::println);

        //Min
        people.stream()
                .min(Comparator.comparing(Person::getAge));
                //.ifPresent(System.out::println);

        //Group
        Map<Gender, List<Person>> groupGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        /*groupGender.forEach(((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        }));*/

        people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName)
                .ifPresent(System.out::println);

    }

    private static List<Person> getPeople(){
        return List.of(
                new Person("Batman",35,Gender.MALE),
                new Person("Superman",33,Gender.MALE),
                new Person("Wonder Woman",30,Gender.FEMALE),
                new Person("Flash",26,Gender.MALE),
                new Person("Black Canary",22,Gender.FEMALE),
                new Person("Batgirl",21,Gender.FEMALE),
                new Person("Shazam",12,Gender.MALE)
        );
    };
}
