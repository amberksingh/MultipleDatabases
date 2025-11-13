package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.DoubleSupplier;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams4 {

    public static void main(String[] args) {

        //arrays with stream
        Integer[] nums = {5, 4, 6, 2, 8, 7, 9};
        int[] values = new int[]{4, 5, 7, 1, 2, 9, 6, 8, 10, 11};
        String[] fruits = {"apple", "banana", "cherry", "mango"};

        //average
        double avgDouble = Stream.of(nums)
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
        System.out.println("avgDouble = " + avgDouble);

        double asDoubleAvg = Arrays.stream(values)
                .average()
                .getAsDouble();
        System.out.println("asDoubleAvg = " + asDoubleAvg);

        DoubleSupplier doubleSupplier = () -> 0D;
        OptionalDouble average = Arrays.stream(values)
                .mapToObj(String::valueOf)
                //.mapToDouble(Double::parseDouble)
                .mapToDouble(Double::valueOf)
                .average();
        double valAvgDouble = average.orElseGet(doubleSupplier);
        System.out.println("valAvgDouble = " + valAvgDouble);

        //sort
        //lexi
        List<String> list = Stream.of(fruits)
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("asc list lex = " + list);

        List<String> list1 = Stream.of(fruits)
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("desc list lex = " + list1);

        //length
        List<String> list2 = Stream.of(fruits)
                .sorted(Comparator.comparing(String::length))
                .toList();
        System.out.println("list asc length wise = " + list2);

        List<String> list3 = Stream.of(fruits)
                .sorted(Comparator.comparing(String::length, Comparator.reverseOrder()))
                .toList();
        System.out.println("list desc length wise = " + list3);

        //concat
        int[] arr1 = new int[]{4, 8, 1, 9, 6};
        int[] arr2 = new int[]{11, 10, 15, 6, 8};
        //IntFunction<Integer[]> intFunction = (i) -> new Integer[i];
        IntFunction<Integer[]> intFunction =Integer[]::new;

        System.out.println("Arrays after concat : ");
        Integer[] array = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2))
                .distinct()
                .boxed()
                .toArray(intFunction);
        Arrays.sort(array);
        System.out.println("sorted array = " + Arrays.toString(array));


    }
}
