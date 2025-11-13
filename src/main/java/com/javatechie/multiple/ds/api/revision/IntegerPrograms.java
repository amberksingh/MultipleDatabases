package com.javatechie.multiple.ds.api.revision;

import java.util.*;
import java.util.stream.Collectors;

public class IntegerPrograms {

    public static void main(String[] args) {

        //Find the count/sum/average/minimum/maximum of integers
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        IntSummaryStatistics intSummaryStatistics = Arrays.stream(arr).summaryStatistics();
        System.out.println("intSummaryStatistics count = " + intSummaryStatistics.getCount());
        System.out.println("intSummaryStatistics max = " + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics min = " + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics sum = " + intSummaryStatistics.getSum());
        System.out.println("intSummaryStatistics avg = " + intSummaryStatistics.getAverage());

        IntSummaryStatistics collect = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.summarizingInt(x -> x)
                );
        System.out.println("collect = " + collect);

        //map to square
        List<Double> list = Arrays.stream(arr)
                .mapToDouble(n -> Math.pow(n, 2))
                .boxed()
                .toList();
        System.out.println("list = " + list);

        Double[] array = Arrays.stream(arr)
                .mapToObj(n -> Math.pow(n, 2))
                .toArray(Double[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        //Get all the elements in the array that are unique (remove duplicates)
        int[] dupContainArray = {1, 7, 3, 4, 3, 6, 9, 8, 9};
        List<Integer> list1 = Arrays.stream(dupContainArray)
                .distinct()
                .boxed()
                .toList();

        System.out.println("unique values of array = " + list1);

        //set way
        HashSet<Integer> uniqueValuesSet = new HashSet<>();
        LinkedHashSet<Integer> collect1 = Arrays.stream(dupContainArray)
                .boxed()
                .filter(e -> !uniqueValuesSet.add(e))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("dups = " + collect1);
        System.out.println("unique val = " + uniqueValuesSet);

        //Partition the array into two lists: one with even numbers and another with odd numbers
        Map<Boolean, List<Integer>> collect2 = Arrays.stream(dupContainArray)
                .boxed()
                .collect(
                        Collectors.partitioningBy(
                                n -> n % 2 == 0,
                                Collectors.toList()
                        )
                );
        System.out.println("even  = " + collect2.get(true));
        System.out.println("odd  = " + collect2.get(false));

        //Group elements by their remainder when divided by 3?
        System.out.println("Elements grouped by their remainder when divided by 3 : ");
        Map<Integer, List<Integer>> collect3 = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                num -> num % 3
                        )
                );
        System.out.println(collect3);

        //Calculate sum of numeric values in a string
        String numString = "384";//3+8+4=15
        int sum = Arrays.stream(numString.split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sum = " + sum);

        Integer integer = Arrays.stream(numString.split(""))
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("integer = " + integer);

        long sum1 = Arrays.stream(numString.split(""))
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .asLongStream()
                .sum();
        System.out.println("sum1 = " + sum1);

        int sum2 = numString.chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println("sum2 = " + sum2);

        int sum3 = numString.chars()
                .mapToObj(Character::toString)
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sum3 = " + sum3);

    }
}
