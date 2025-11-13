package com.javatechie.multiple.ds.api.programs;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoodBadString {

    public static void main(String[] args) {

        //same frequency of chars in words -> good else bad
        String str = "arunarun";
        Set<Long> collect = Stream.of(str.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
        if (collect.size() == 1)
            System.out.println("Good String");
        else
            System.out.println("Bad String");

    }
}
