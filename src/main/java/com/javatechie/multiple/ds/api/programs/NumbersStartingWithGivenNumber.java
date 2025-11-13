package com.javatechie.multiple.ds.api.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NumbersStartingWithGivenNumber {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(10, 14, 22, 25, 88, 84, 17, 15);

        Function<String, Character> function = str -> str.charAt(0);

        //using groupingBy
        Map<Character, List<String>> collect = list.stream()
                .map(String::valueOf)
                .collect(
                        Collectors.groupingBy(
                                function
                        )
                );
        System.out.println("Numbers starting with given first digit using groupingBy : ");
        collect.entrySet()
                .stream()
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

        //toMap
        //final List<String> arrList;// = new ArrayList<>();
        System.out.println("Numbers starting with given first digit using toMap : ");
        list.stream()
                .map(String::valueOf)
                .collect(
                        Collectors.toMap(
                                function,
                                e -> {
                                    List<String> arrList = new ArrayList<>();
                                    arrList.add(e);
                                    return arrList;
                                },
                                (oldVal, newVal) -> {                      // merge logic
                                    oldVal.addAll(newVal);                 // keep existing, add new
                                    return oldVal;
                                }
                        )
                )
                .entrySet()
                .stream()
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }
}
