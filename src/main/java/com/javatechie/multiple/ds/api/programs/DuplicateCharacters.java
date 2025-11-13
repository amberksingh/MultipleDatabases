package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateCharacters {

    public static void main(String[] args) {

        String str = "new old value good bad";
        System.out.println("Characters which are duplicate : ");
        Arrays.stream(str.split(""))
                .filter(e -> !e.equalsIgnoreCase(" "))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);


        List<String> list = Arrays.stream(str.split(""))
                .toList();
        System.out.println("Characters which are duplicate using Collections.frequency(list, e) : ");
        Set<String> collect = Arrays.stream(str.split(""))
                .filter(e -> (!e.equalsIgnoreCase(" ") && Collections.frequency(list, e) > 1))
                .collect(Collectors.toSet());
        System.out.println("collect = " + collect);

        Arrays.stream(str.split(""))
                .filter(e -> (!e.equalsIgnoreCase(" ") && Collections.frequency(list, e) > 1))
                .distinct()
                .forEach(System.out::println);

    }
}
