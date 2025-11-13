package com.javatechie.multiple.ds.api.again;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesArrayDemo {

    public static void main(String[] args) {

        int[] arr = {45, 12, 12, 87, 66, 99, 45, 200, 200, 77};
        System.out.println("original array : " + Arrays.toString(arr));

        System.out.println("duplicate values of array using toMap() : ");
        Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                val -> 1,
                                Integer::sum
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

        //using set
        Set<Integer> uniqueValues = new HashSet<>();
        Set<Integer> repeatingValues = Arrays.stream(arr)
                .boxed()
                .filter(e -> !uniqueValues.add(e))
                .collect(Collectors.toSet());
        System.out.println("repeatingValues set = " + repeatingValues);
        System.out.println("uniqueValues set = " + uniqueValues);

        //Collection.frequency
        int[] array = Arrays.stream(arr)
                .filter(val -> Collections.frequency(Arrays.stream(arr).boxed().toList(), val) > 1)
                .distinct()
                .toArray();
        System.out.println("repeatingValues array = " + Arrays.toString(array));

    }
}
