package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonElementsBetweenArrays {

    public static void main(String[] args) {

        int arr1[] = {1, 2, 3, 4, 5, 6, 6};
        int arr2[] = {1, 2, 9, 0, 0, 6};

        Set<Integer> set1 = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> set2 = Arrays.stream(arr2)
                .boxed()
                .collect(Collectors.toSet());
        set1.retainAll(set2);
        Stream.of(set1)
                .forEach(System.out::println);

        Set<Integer> commonElements = Arrays.stream(arr1)
                .boxed()
                .filter(val -> set2.contains(val))
                .collect(Collectors.toSet());
        Stream.of(commonElements)
                .forEach(System.out::println);


    }
}
