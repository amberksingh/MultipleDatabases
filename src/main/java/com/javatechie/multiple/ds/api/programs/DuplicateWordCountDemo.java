package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DuplicateWordCountDemo {

    public static void main(String[] args) {

        String str = " hellO great morning hello world night great people people";

        //duplicate word
        String[] words = str.toUpperCase().split(" ");
        System.out.println("Duplicate words : ");
        Stream.of(words)
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
                //.map(entry -> entry.getKey())
                .forEach(e -> System.out.print(e + " "));

        //Collections.frequency
        System.out.println("\nDuplicate words using Collections.frequency : ");
        List<String> list = Arrays.stream(words).toList();
        Stream.of(words)
                .filter(x -> Collections.frequency(list, x) > 1)
                .distinct()
                .forEach(e -> System.out.print(e + " "));



    }
}
