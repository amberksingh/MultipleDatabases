package com.javatechie.multiple.ds.api.again;

import java.util.*;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream3Stuff {

    public static void main(String[] args) {

        //primitive stream
        IntStream intStream = IntStream.of(1, 8, 4, 9, 4, 45, 8, 7, 22);

        //double each value
        List<Integer> list = intStream
                .map(n -> n * 2)
                .boxed()
                .toList();
        System.out.println("list double values = " + list);

        //range
        List<Integer> collect = IntStream.range(2, 8)
                .filter(n -> n % 2 == 0)
                .boxed()//works for Stream<T> not IntStream
                .toList();
        System.out.println("\ncollect = " + collect);

        List<String> nums = new ArrayList<>(List.of("8", "1", "2", "3", "6"));

        //takeWhile
        List<Integer> takeWhile = nums.stream()
                .map(Integer::parseInt)
                .takeWhile(n -> n > 2)//stops at first failure
                .toList();
        System.out.println("takeWhile = " + takeWhile);// [8]

        //dropWhile
        List<Integer> dropWhile = nums.stream()
                .map(Integer::valueOf)
                .dropWhile(n -> n % 2 == 0)//drops till pred is true, includes rest even if pred is false
                .toList();
        System.out.println("dropWhile = " + dropWhile);//[1, 2, 3, 6]
        
        //Stream.of(list) instead of list.stream
        String[] array = Stream.of(nums)//gives Stream<List> not individual string
                .flatMap(x -> x.stream())
                .map(y -> y.toUpperCase())
                .toArray(String[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        String[] arr = new String[]{"8", "1", "2", "3", "6"};
        //Stream.of(array)
        List<Integer> list1 = Stream.of(arr)//gives individual string not Stream<List>
                // The varargs unpack each element of the array into the stream
                .map(Integer::parseInt)
                .toList();
        System.out.println("list1 = " + list1);

        LongSupplier supplier = () -> 0L;
        //longStream
        long longMax = nums.stream()
                .mapToLong(Long::parseLong)
                .max()
                .orElseGet(supplier);
        System.out.println("longMax = " + longMax);
        
        //summaryStatistics
        LongSummaryStatistics longSummaryStatistics = nums.stream()
                .mapToLong(Long::parseLong)
                .summaryStatistics();
        System.out.println("longSummaryStatistics = " + longSummaryStatistics);

        //doubleStream
        nums.stream()
                .mapToDouble(Double::parseDouble)
                .average()
                .ifPresent(System.out::println);

        //summarizingInt
        ToIntFunction<String> toIntFunction = Integer::parseInt;
        IntSummaryStatistics intSummaryStatistics = nums.stream()
                .collect(
                        Collectors.summarizingInt(toIntFunction)
                );
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        //reverse sorting
        List<Integer> reverseList = nums.stream()
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("reverseList = " + reverseList);

        List<Integer> reverseCompare = nums.stream()
                .map(Integer::parseInt)
                .sorted((n1, n2) -> Integer.compare(n2, n1))
                .toList();
        System.out.println("reverseCompare = " + reverseCompare);

        long count = nums.stream()
                .map(Integer::parseInt)
                .sorted(Integer::compareTo)
                .count();
        System.out.println("count = " + count);

        Collections.sort(nums);
        System.out.println("nums = " + nums);

        Collections.sort(nums, Comparator.reverseOrder());
        System.out.println("nums reverse order = " + nums);

        Collections.sort(nums, Collections.reverseOrder());
        System.out.println("nums reverse order = " + nums);

        Comparator<String> comparator = Comparator.comparing(Integer::parseInt, Comparator.reverseOrder());
        Collections.sort(nums, comparator);
        System.out.println("nums reverse order = " + nums);


    }
}
