package com.javatechie.multiple.ds.api.again;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyString {

    public static void main(String[] args) {

        String str = "hello world java space hello space nick diaz world worm food food foot";
        String[] words = str.split(" ");

        List<String> list = Arrays.stream(words)
                .toList();

        //old skool way
        System.out.println("old skool way : ");
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            if (map.containsKey(w)) {
                map.put(w, map.getOrDefault(w, 1) + 1);
            } else {
                map.put(w, 1);
            }
        }
        map.forEach((key, value) -> System.out.println(key + " -> " + value));

        //
        Map<String, Long> groupingBy = Arrays.stream(words)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("groupingBy way = " + groupingBy);

        //toMap
        Map<String, Integer> toMap = Arrays.stream(words)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                word -> Collections.frequency(list, word),
                                (oldVal, newVal) -> oldVal
                        )
                );
        System.out.println("toMap way = " + toMap);


    }
}
