package com.javatechie.multiple.ds.api.again;

import java.util.*;
import java.util.stream.Stream;

public class Streams4Stuff {

    public static void main(String[] args) {

        //arrays with stream
        Integer[] nums = {5, 4, 6, 2, 8, 7, 9};
        int[] values = new int[]{4, 5, 7, 1, 2, 9, 6, 8, 10, 11};
        String[] fruits = {"apple", "banana", "cherry", "mango"};
        String[] stringNumbers = {"5", "1", "7", "51", "20", "8", "14", "1", "30", "5"};

        //Arrays.stream(Integer/wrapper) gives Stream<Integer>
        //Arrays.stream(int/primitive) gives IntStream
        
        //avg nums
        double avg = Stream.of(nums)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("avg = " + avg);

        //max values
        int max = Arrays.stream(values)
                .max()
                .orElse(0);
        System.out.println("max = " + max);

        //fruits lengths
        Integer[] array = Stream.of(fruits)
                .map(String::length)
                .toArray(Integer[]::new);
        System.out.println("array of lengths = " + Arrays.toString(array));
        
        //fruits sort by lexicographically
        List<String> list = Arrays.stream(fruits)
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("list = " + list);

        //length basis desc sort
        List<String> list1 = Arrays.stream(fruits)
                .sorted(Comparator.comparing(String::length, Comparator.reverseOrder()))
                .toList();
        System.out.println("list1 = " + list1);
        
        //remove dups, convert to int average of those numbers
        OptionalDouble average = Arrays.stream(stringNumbers)
                .distinct()
                .mapToInt(Integer::parseInt)
                .average();
        System.out.println("average.getAsDouble() = " + average.getAsDouble());

        //reverse each word in fruits
        List<String> reverse = Stream.of(fruits)
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .toList();
        System.out.println("reverse = " + reverse);

        //2nd way
        List<String> list2 = Arrays.stream(fruits)
                .map(s -> {
                    String[] split = s.split("");
                    return Arrays.stream(split)
                            .reduce("", (c1, c2) -> c2 + c1);
                })
                .toList();
        System.out.println("reverse each word using reduce = " + list2);

        //reverse entire array words
        Optional<String> reduce = Stream.of(fruits)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .reduce((c1, c2) -> c2 + c1);
        System.out.println("reverse entire array words = " + reduce.get());//ognamyrrehcananabelppa




    }
}
