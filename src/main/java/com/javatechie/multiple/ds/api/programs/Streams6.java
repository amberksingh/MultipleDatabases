package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams6 {

    public static void main(String[] args) {

        String[] fruits = {"apple", "banana", "cherry"};
        System.out.println("fruits using Stream.of() : ");
        Stream.of(fruits)
                .forEach(System.out::println);

        System.out.println("fruits using Arrays.stream() : ");
        Arrays.stream(fruits)
                .forEach(System.out::println);

        int[] nums = {7, 11, 9, 1, 2, 3, 4};
        Integer[] numbers = {7, 11, 9, 1, 2, 3, 4};
        int[] array = Arrays.stream(nums)//returns IntStream
                .sorted()
                .toArray();
        System.out.println("Sorted array : " + Arrays.toString(array));

        //average
        OptionalDouble average = IntStream.of(nums).average();
        if (average.isPresent()) {
            System.out.println("average : " + average.getAsDouble());
        } else {
            System.out.println("Empty optional object");
        }

        //IntFunction<String[]> intFunction = (i) -> new String[i];
        IntFunction<String[]> intFunction = String[]::new;
        String[] stringArray = Arrays.stream(numbers)//returns Stream<Integer>
                //.map(String::valueOf)
                .map(x -> Integer.toString(x))
                //.toArray(String[]::new);
                .toArray(intFunction);
        System.out.println("array1 = " + Arrays.toString(stringArray));

        //concat
        int[] arr1 = new int[]{4, 8, 1, 9, 6};
        int[] arr2 = new int[]{11, 10, 15, 6};
        System.out.println("Arrays after concat : ");
        int[] sortedConcatenatedArray = IntStream.concat(IntStream.of(arr1), IntStream.of(arr2))
                .distinct()
                .sorted()
                .toArray();
        System.out.println("Arrays.toString(sortedConcatenatedArray) = " + Arrays.toString(sortedConcatenatedArray));

    }
}
