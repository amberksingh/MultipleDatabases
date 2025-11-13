package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArraysWithStreams {
    

    public static void main(String[] args) {
        // Arrays.stream(String)
        String[] fruits = {"apple", "banana", "cherry"};
        System.out.println("fruits array after converting to stream using Arrays.stream(fruits) : ");
        Arrays.stream(fruits)
                .forEach(s -> System.out.print(s + " "));

        Stream.of(fruits)
                .peek(s -> System.out.println())
                .forEach(s -> System.out.print(s + " "));

        int[] nums = {7, 11, 9, 1, 2, 3, 4};
        Integer[] nums1 = {7, 11, 9, 1, 2, 3, 4};
        Arrays.stream(nums)
                .forEach(s -> System.out.print(s + " "));
        Stream.of(nums1)
                .forEach(s -> System.out.print(s + " "));
        //Stream.of works with wrapper integer array
        //Stream.of doesn't work with int primitive array
        
        //average
        double average = Arrays.stream(nums)
                .average()
                .getAsDouble();
        System.out.println("average = " + average);

        Double averageUsingCollect = Arrays.stream(nums1)
                .collect(
                        Collectors.averagingInt(x -> x)
                );
        System.out.println("averageUsingCollect = " + averageUsingCollect);

        double avg = Arrays.stream(nums1)
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
        System.out.println("avg = " + avg);

    }
}
