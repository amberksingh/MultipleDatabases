package com.javatechie.multiple.ds.api.again;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerPrograms {

    public static void main(String[] args) {

        //Find the count/sum/average/minimum/maximum of integers
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        IntSummaryStatistics intSummaryStatistics = IntStream.of(arr)
                .mapToObj(String::valueOf)
                .mapToInt(Integer::parseInt)
                .summaryStatistics();
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        //square of every number
        double[] array = Arrays.stream(arr)
                .mapToDouble(x -> Math.pow(x, 2))
                .toArray();
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        //Find the product of all elements in the array
        double product = Arrays.stream(arr)
                .asDoubleStream()
                .reduce(1, (n1, n2) -> n1 * n2);
        System.out.println("product = " + product);

        //Partition the array into two lists: one with even numbers and another with odd numbers
        Map<Boolean, List<Integer>> collect = IntStream.of(arr)
                .boxed()
                .collect(
                        Collectors.partitioningBy(
                                n -> n % 2 == 0
                        )
                );
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("even", collect.get(true));
        map.put("odd", collect.get(false));
        System.out.println("map = " + map);

        //Group elements by their remainder when divided by 3?
        System.out.println("Elements grouped by their remainder when divided by 3 : ");
        Map<Integer, List<Integer>> collect1 = IntStream.of(arr)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                n -> n % 3
                        )
                );
        System.out.println("collect1 = " + collect1);

        //Calculate sum of numeric values in a string
        String numString = "384";
        int sum = numString.chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println("sum = " + sum);

        int sum1 = Arrays.stream(numString.split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sum1 = " + sum1);

        Integer sumReduce = Arrays.stream(numString.split(""))
                .map(Integer::parseInt)
                .reduce(0, (Integer::sum));
        System.out.println("sumReduce = " + sumReduce);

    }
}
