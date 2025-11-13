package com.javatechie.multiple.ds.api.code;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntegerPrograms {

    public static void main(String[] args) {

        //Find the count/sum/average/minimum/maximum of integers
        int[] arr = {2, 3, 4, 6, 7, 8, 9, 1, 5, 8};
        IntSummaryStatistics intSummaryStatistics = IntStream.of(arr)
                .summaryStatistics();
        System.out.println("intSummaryStatistics count = " + intSummaryStatistics.getCount());
        System.out.println("intSummaryStatistics max = " + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics min = " + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics sum = " + intSummaryStatistics.getSum());
        System.out.println("intSummaryStatistics avg = " + intSummaryStatistics.getAverage());

        //Filter out even/odd numbers from the array
        List<Integer> evenList = Arrays.stream(arr)
                .filter(n -> n % 2 == 0)
                .boxed()
                .toList();
        System.out.println("evenList = " + evenList);

        List<Integer> oddList = Arrays.stream(arr)
                .filter(n -> n % 2 != 0)
                .boxed()
                .toList();
        System.out.println("oddList = " + oddList);

        //square array
        List<Double> squareList = Arrays.stream(arr)
                .mapToDouble(n -> Math.pow(n, 2))
                .boxed()
                .toList();
        System.out.println("squareList = " + squareList);

        //2nd way
        List<Integer> squareListIntStream = Arrays.stream(arr)
                .map(n -> n * n)
                .boxed()
                .toList();
        System.out.println("squareListIntStream = " + squareListIntStream);

        //3rd way
        int[] squareArray = Arrays.stream(arr)
                .map(n -> (int) Math.pow(n, 2))
                .toArray();
        System.out.println("Arrays.toString(squareArray) = " + Arrays.toString(squareArray));

        //4th way
        double[] doubleStream = Arrays.stream(arr)
                .mapToObj(n -> Math.pow(n, 2))
                .mapToDouble(Double::doubleValue)
                .toArray();
        System.out.println("doubleStream = " + Arrays.toString(doubleStream));

        //5th way
        double[] array = Arrays.stream(arr)
                .map(n -> n * n)
                .asDoubleStream()
                .toArray();
        System.out.println("array = " + Arrays.toString(array));

        //Find the product of all elements in the array
        int productOfArrayValues = Arrays.stream(arr)
                .reduce((num1, num2) -> num1 * num2)
                .orElse(0);
        System.out.println("productOfArrayValues = " + productOfArrayValues);

        //2nd way
        int productOfArrayValuesReduce = Arrays.stream(arr)
                .reduce(1, (num1, num2) -> num1 * num2);
        System.out.println("productOfArrayValuesReduce = " + productOfArrayValuesReduce);

        //Sort the array in ascending order
        int[] arrayAscOrder = Arrays.stream(arr)
                .sorted()
                .toArray();
        System.out.println("arrayAscOrder = " + Arrays.toString(arrayAscOrder));

        //Sort the array in descending order
        Integer[] arrayDescOrder = Arrays.stream(arr)
                .boxed()
                //.sorted(Comparator.reverseOrder())
                //.sorted((num1, num2) -> num2 - num1)
                .sorted((num1, num2) -> Integer.compare(num2, num1))
                .toArray(Integer[]::new);
        System.out.println("arrayDescOrder = " + Arrays.toString(arrayDescOrder));

        //Get all the elements in the array that are unique (remove duplicates)
        int[] dupContainArray = {1, 7, 3, 4, 3, 6, 9, 8, 9};
        List<Integer> dupsContainList = Arrays.stream(dupContainArray)
                .boxed()
                .toList();

        List<Integer> uniqueValList = Arrays.stream(dupContainArray)
                .filter(num -> Collections.frequency(dupsContainList, num) == 1)
                .boxed()
                .toList();
        System.out.println("uniqueValList = " + uniqueValList);

        Set<Integer> uniqueValueSet = new HashSet<>();
        Set<Integer> dupList = Arrays.stream(dupContainArray)
                .boxed()
                .filter(num -> !uniqueValueSet.add(num))
                .collect(Collectors.toSet());
        System.out.println("dupList = " + dupList);
        System.out.println("uniqueValueSet after removing duplicates = " + uniqueValueSet);

        //Partition the array into two lists: one with even numbers and another with odd numbers
        /*Map<Boolean, List<Integer>> collect = */
        Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.partitioningBy(
                                num -> num % 2 == 0
                        )
                )
                .entrySet()
                .stream()
                .forEach(entry -> {
                    if (entry.getKey()) {
                        System.out.println("even = " + entry.getValue());
                    } else {
                        System.out.println("odd = " + entry.getValue());
                    }
                    //return entry;
                });

        //Group elements by their remainder when divided by 3?

        Map<Integer, List<Integer>> collect = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                num -> num % 3
                        )
                );
        System.out.println("Elements grouped by their remainder when divided by 3 : " + collect);

        System.out.println("Elements mapped to their remainder when divided by 3 toMap : ");

        Map<Integer, List<Integer>> collect2 = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.toMap(
                                num -> num % 3,
                                val -> {
                                    List<Integer> valList = new ArrayList<>();
                                    valList.add(val);
                                    return valList;
                                },
                                (oldVal, newVal) -> {
                                    oldVal.addAll(newVal);//IMPORTANT
                                    return oldVal;
                                }
                        )
                );
        collect2.forEach((key, value) -> System.out.println(key + " -> " + value));

        //Calculate sum of numeric values in a string
        String numString = "384";//15

        //1st way
        int sum = numString.chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println("sum Character::getNumericValue = " + sum);

        int sum2 = numString.chars()
                .mapToObj(Character::getNumericValue)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum2 = " + sum2);

        int sum1 = Stream.of(numString.split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("sum1 Integer::parseInt = " + sum1);

        Integer reduce = Stream.of(numString.split(""))
                .map(Integer::parseInt)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("reduce sum = " + reduce);

    }
}
