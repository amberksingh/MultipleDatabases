package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerPrograms {

    public static void main(String[] args) {

        //Find the count/sum/average/minimum/maximum of integers
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        IntSummaryStatistics intSummaryStatistics = Arrays.stream(arr)
                .summaryStatistics();
        System.out.println("intSummaryStatistics sum = " + intSummaryStatistics.getSum());
        System.out.println("intSummaryStatistics max = " + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics min = " + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics count = " + intSummaryStatistics.getCount());
        System.out.println("intSummaryStatistics average = " + intSummaryStatistics.getAverage());

        IntSummaryStatistics collect = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.summarizingInt(x -> x)
                );
        System.out.println("collect = " + collect);

        long count = Arrays.stream(arr)
                .count();
        System.out.println("count = " + count);

        int sum = Arrays.stream(arr)
                .sum();
        System.out.println("sum = " + sum);

        double average = Arrays.stream(arr)
                .average()
                .orElse(0d);
        System.out.println("average = " + average);

        int max = Arrays.stream(arr)
                .max()
                .orElse(0);
        System.out.println("max = " + max);

        int min = Arrays.stream(arr)
                .min()
                .orElse(0);
        System.out.println("min = " + min);

        //map to square
        double[] squareArray = Arrays.stream(arr)
                .mapToDouble(x -> Math.pow(x, 2))
                .toArray();
        System.out.println("squareArray = " + Arrays.toString(squareArray));

        Double[] squareArrayDouble = Arrays.stream(arr)
                .mapToObj(x -> Math.pow(x, 2))
                .toArray(Double[]::new);
        System.out.println("squareArrayDouble = " + Arrays.toString(squareArrayDouble));

        double[] array = Arrays.stream(arr)
                .mapToObj(x -> Math.pow(x, 2))
                .mapToDouble(Double::doubleValue)
                .toArray();
        System.out.println("array = " + array);

        double[] array5 = Arrays.stream(arr)
                .map(n -> (int) Math.pow(n, 2))
                .asDoubleStream()
                .toArray();
        System.out.println("square array = " + Arrays.toString(array5));

        //Find the product of all elements in the array
        int product = Arrays.stream(arr)
                .reduce((x1, x2) -> x1 * x2)
                .orElse(0);
        System.out.println("product = " + product);

        //Sort the array in ascending order
        int[] sortedArrayAsc = Arrays.stream(arr)
                .sorted()
                .toArray();
        System.out.println("sortedArray asc order = " + Arrays.toString(sortedArrayAsc));

        //Sort the array in descending order
        Integer[] sortedArrayDesc = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
        System.out.println("sortedArray desc order = " + Arrays.toString(sortedArrayDesc));

        //Get all the elements in the array that are unique (remove duplicates)
        int[] dupContainArray = {1, 7, 3, 4, 3, 6, 9, 8, 9};
        List<Integer> list = IntStream.of(dupContainArray)
                .distinct()
                .boxed()
                .toList();
        System.out.println("unique values of array = " + list);

        //set way
        HashSet<Integer> uniqueValuesSet = new HashSet<>();
        Set<Integer> repeatingValues = Arrays.stream(dupContainArray)
                .boxed()
                .filter(x -> !uniqueValuesSet.add(x))
                .collect(Collectors.toSet());
        System.out.println("repeatingValues = " + repeatingValues);
        System.out.println("uniqueValuesSet = " + uniqueValuesSet);

        //Partition the array into two lists: one with even numbers and another with odd numbers
        Map<Boolean, List<Integer>> oddEvenList = Arrays.stream(dupContainArray)
                .boxed()
                .collect(
                        Collectors.partitioningBy(
                                num -> num % 2 == 0,
                                Collectors.toList()
                        )
                );
        System.out.println("even nos list : " + oddEvenList.get(true));
        System.out.println("odd nos list : " + oddEvenList.get(false));

        //Group elements by their remainder when divided by 3?
        System.out.println("Elements grouped by their remainder when divided by 3 : ");
        Arrays.stream(dupContainArray)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                num -> num % 3
                        )
                )
                .entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey()+" -> "+entry.getValue()));

        //Calculate sum of numeric values in a string
        String numString = "384";//3+8+4=15
        int sumOfDigits = Stream.of(numString.split(""))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sumOfDigits = " + sumOfDigits);

        int sumOfDigits1 = Stream.of(numString.split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sumOfDigits1 = " + sumOfDigits1);

        Integer sumOfDigits2 = Stream.of(numString.split(""))
                .map(Integer::valueOf)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("sumOfDigits2 = " + sumOfDigits2);

        int sumOfDigits3 = numString.chars()
                //.map(c -> Character.getNumericValue(c))
                .map(Character::getNumericValue)
                .sum();
        System.out.println("sumOfDigits3 = " + sumOfDigits3);


    }
}
