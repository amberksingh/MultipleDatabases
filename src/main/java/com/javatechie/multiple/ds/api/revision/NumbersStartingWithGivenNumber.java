package com.javatechie.multiple.ds.api.revision;

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
        Map<Character, List<String>> collect = list.stream()
                .map(String::valueOf)
                .collect(
                        Collectors.groupingBy(
                                function
                        )
                );
        System.out.println("collect = " + collect);

        //toMap

        Map<Character, List<String>> collect1 = list.stream()
                .map(String::valueOf)
                .collect(
                        Collectors.toMap(
                                function,
                                num -> {
                                    List<String> l = new ArrayList<>();
                                    l.add(num);
                                    return l;
                                },
                                (oldVal, newVal) -> {
                                    oldVal.addAll(newVal);
                                    return oldVal;
                                }

                        )
                );
        System.out.println("collect1 = " + collect1);
    }
}
