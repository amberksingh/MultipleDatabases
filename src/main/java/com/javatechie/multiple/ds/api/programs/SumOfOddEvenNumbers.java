package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumOfOddEvenNumbers {

    public static void main(String[] args) {

        int[] emptyArr = {};
        int[] arr = {2, 5, 5, 7, 3, 4, 6, 8, 1, 9};

        //max
        int max = IntStream.of(arr)
                .max()
                .orElseThrow();
        System.out.println("max = " + max);

//        int i = IntStream.of(emptyArr)
//                .max()
//                .orElseThrow();//throws java.util.NoSuchElementException
//        System.out.println("i = " + i);
        Integer maxBoxed = Arrays.stream(arr)
                .boxed()
                .max(Comparator.naturalOrder())
                .orElseGet(() -> 0);
        System.out.println("maxBoxed = " + maxBoxed);

        //BinaryOperator.maxBy(...) needs an object stream (Stream<T>).
        Integer maxUsingReduce = Arrays.stream(arr)
                .boxed()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(x -> x)))
                .orElse(0);
        System.out.println("maxUsingReduce = " + maxUsingReduce);

        Integer integer = Arrays.stream(arr)
                .boxed()
                .reduce(BinaryOperator.maxBy(Comparator.comparing(x -> x)))
                .orElse(0);
        System.out.println("maxUsingReduce = " + integer);

        //.map() on IntStream → gives another IntStream (primitive).
        //
        //If you need Stream<Integer> → use .mapToObj(...) or .boxed().
        //
        //If you need other primitive types → use .mapToDouble() / .mapToLong().


        //min
        int min = Arrays.stream(arr)
                .min()
                .orElse(0);
        System.out.println("min = " + min);

        Integer integer1 = IntStream.of(arr)
                .mapToObj(Integer::valueOf)
                .min(Comparator.comparing(x -> x))
                .orElse(0);
        System.out.println("integer1 = " + integer1);

        //sum
        int sum = IntStream.of(arr)
                .sum();
        System.out.println("sum = " + sum);

        //using reduce
        Integer integer2 = IntStream.of(arr)
                .boxed()
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println("sum using reduce = " + integer2);

        //two minimum elements
        System.out.println("Two least values in array : ");
        Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.naturalOrder())
                .limit(2)
                .forEach(x -> System.out.print(x + " "));

        //two maximum elements
        System.out.println("\nTwo maximum values in array : ");
        Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .forEach(x -> System.out.print(x + " "));

        //frequency of odd and even numbers
        Map<Boolean, Long> frequencyOddEven = Arrays.stream(arr)
                .mapToObj(Integer::valueOf)
                .collect(
                        Collectors.partitioningBy(
                                num -> num % 2 == 0,
                                Collectors.counting()
                        )
                );
        System.out.println("\nodd frequency : "+frequencyOddEven.get(false));
        System.out.println("even frequency : "+frequencyOddEven.get(true));

        //sum of odd, even numbers
        Map<Boolean, Integer> sumOddEven = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.partitioningBy(
                                num -> num % 2 == 0,
                                Collectors.summingInt(x -> x)
                        )
                );
        System.out.println("\nsum of odd nums : "+sumOddEven.get(false));
        System.out.println("sum of even nums : "+sumOddEven.get(true));


    }
}
