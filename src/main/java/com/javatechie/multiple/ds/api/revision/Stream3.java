package com.javatechie.multiple.ds.api.revision;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Stream3 {

    public static void main(String[] args) {

        //primitive stream
        IntStream intStream = IntStream.of(1, 8, 4, 9, 4, 45, 8, 7, 22);
        System.out.println("original stream = ");
        intStream.forEach(n -> System.out.print(n + " "));

        int asIntMin = IntStream.range(4, 11)
                .min()
                .getAsInt();
        System.out.println("\nasIntMin = " + asIntMin);

        System.out.println("IntStream.rangeClosed(3, 7) = ");
        IntStream.rangeClosed(3, 7)//7 inclusive so 3,4,5,6,7
                .forEach(n -> System.out.print(n + " "));

        //takewhile, dropWhile, mapToInt
        List<String> nums = List.of("8", "1", "2", "3", "6");
        
        //sum
        int sum = nums.stream()
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("\nsum = " + sum);

        Integer reduceSum = nums.stream()
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
        System.out.println("reduceSum = " + reduceSum);

        List<Integer> takeWhile = Stream.of(5, 7, 8, 4, 2, 9)
                .takeWhile(n -> n % 2 != 0)//accepts till pred is true, rest are ignored
                .toList();
        System.out.println("takeWhile = " + takeWhile);

        boolean anyMatch = nums.stream()
                .map(Integer::parseInt)
                .anyMatch(n -> n > 7);
        System.out.println("anyMatch = " + anyMatch);

        boolean noneMatch = nums.stream()
                .map(Integer::parseInt)
                .noneMatch(n -> n > 100);
        System.out.println("noneMatch = " + noneMatch);

        IntStream.of(4, 8, 7, 50, 3)
                .dropWhile(x -> x % 2 == 0)//drops till pred is true, includes rest even if pred is false
                .forEach(s -> System.out.print(s + " "));

        long sum1 = LongStream.of(4, 5, 5, 7, 1, 9, 10, 3, 10)
                .mapToObj(String::valueOf)
                .map(String::length)
                //.mapToLong(x -> x)
                .mapToLong(Integer::longValue)
                .sum();
        System.out.println("long sum1 = " + sum1);


    }
}
