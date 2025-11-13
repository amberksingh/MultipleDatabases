package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesArrayDemo {

    public static void main(String[] args) {

        int[] arr = {45, 12, 12, 87, 66, 99, 45, 200, 200, 77};
        System.out.println("original array : " + Arrays.toString(arr));

        //duplicate using toMap
        System.out.println("duplicate numbers using toMap : ");
        Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                x -> 1,
                                Integer::sum
                        )
                )
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(e -> System.out.print(e + " "));

        //Collections.frequency
        List<Integer> list = Arrays.stream(arr)
                .boxed()
                .toList();
        System.out.println("\nduplicate numbers using Collections.frequency : ");
        Arrays.stream(arr)
                .boxed()
                .filter(x -> Collections.frequency(list, x) > 1)
                .distinct()
                .forEach(e -> System.out.print(e + " "));

        //using set
        Set<Integer> uniqueSet = new HashSet<>();
        Set<Integer> duplicateSet = list.stream()
                .filter(e -> !uniqueSet.add(e))
                .collect(Collectors.toSet());
        System.out.println("\nduplicateSet = " + duplicateSet);
    }
}
