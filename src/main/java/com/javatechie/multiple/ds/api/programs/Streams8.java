package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams8 {

    public static void main(String[] args) {

        //arrays with stream
        Integer[] nums = {5, 4, 6, 2, 8, 7, 9};
        int[] values = new int[]{4, 5, 7, 1, 2, 9, 6, 8, 10, 11};
        String[] fruits = {"apple", "banana", "cherry", "mango"};


        //average
        Double avg = Stream.of(nums)
                .collect(Collectors.averagingInt(n -> n));
        System.out.println("avg = " + avg);

        double average = Arrays.stream(nums)
                .mapToInt(Integer::intValue)
                .average().orElse(0d);
        System.out.println("average = " + average);

        Map<String, Integer> ageMap = Map.of(
                "Alice", 25,
                "Bob", 30,
                "Amber", 20,
                "harish", 40,
                "kailas", 23
        );

        BiPredicate<String, Integer> biPredicate = (name, age) -> name.startsWith("A") && age > 20;
        BiConsumer<String, Integer> biConsumer = (s, a) -> {
            if (biPredicate.test(s, a))
                System.out.println(s + " -> " + a);
        };
        ageMap.forEach(biConsumer);

    }
}
