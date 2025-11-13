package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterAndWordFrequency {

    public static void main(String[] args) {

        String str = "hello guys world right west hello good country world west";
        //word frequency using old method of map
        Map<String, Integer> map = new HashMap<>();
        for (String s : str.split(" ")) {
            if (map.containsKey(s)) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            } else {
                map.put(s, 1);
            }
        }
        System.out.println("words with frequency : " + map);

        //above using toMap
        LinkedHashMap<String, Integer> wordFrequenciesUsingToMap = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                value -> 1,
                                Integer::sum,
                                LinkedHashMap::new//maintains traverse order
                        )
                );
        System.out.println("wordFrequenciesUsingToMap = " + wordFrequenciesUsingToMap);

        //Collections.frequency
        List<String> list = Arrays.stream(str.split(" "))
                .toList();
        TreeMap<String, Integer> wordFrequenciesUsingFrequency = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                word -> Collections.frequency(list, word),
                                (oldVal, newVal) -> oldVal,
                                TreeMap::new//maintains ascending order
                        )
                );
        System.out.println("wordFrequenciesUsingFrequency = " + wordFrequenciesUsingFrequency);

        //Same for word frequency - groupingBy
        LinkedHashMap<String, Long> collect = list.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                );
        System.out.println("wordFrequenciesUsingGroupingBy : ");
        collect.entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        String[] st1 = {"abc", "def"};
        Stream.of(st1)
                .forEach(System.out::println);//fine for string

        int[] a1 = {10, 20};
        Stream.of(a1)
                .forEach(System.out::println);
        //don't use for primitive(it gives Stream<int[]> not individual elements but same works well for wrapper
        // i.e Integer[] array or String[])


        Arrays.stream(a1)
                .forEach(System.out::println);//use this for primitive

        Integer[] a2 = {50, 60};
        Stream.of(a2)
                .forEach(System.out::println);//fine for wrapper class but if primitive int[], then Stream<int[]> which is not right

        Arrays.stream(a2)
                .forEach(System.out::println);//fine for wrapper class



    }
}
