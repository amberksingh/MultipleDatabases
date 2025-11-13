package com.javatechie.multiple.ds.api.programs;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams1 {

    public static void main(String[] args) {

        List<String> names = List.of("Alice", "Bob", "Ankit");
        System.out.println("Original list : " + names);

        Predicate<String> predicate = (str) -> str.startsWith("A");
        System.out.println("Names starting with A : " + names);
        names.stream()
                .filter(predicate)
                .forEach(s -> System.out.print(s + " "));

        //to uppercase
        Function<String, String> upperCase = String::toUpperCase;
        System.out.println("\nNames in uppercase : ");
        names.stream()
                .map(upperCase)
                .forEach(s -> System.out.print(s + " "));

        //sorted() / sorted(Comparator)
        List<Integer> nums = List.of(3, 1, 4, 2, 9);
        System.out.println("\nnums sorted in ascending order : ");
        nums.stream()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\nnums sorted in descending order : ");
        nums.stream()
                //.sorted(Comparator.reverseOrder())
                .sorted((x1, x2) -> x2 - x1)
                .forEach(s -> System.out.print(s + " "));

        boolean noneMatch = names.stream()
                .noneMatch(str -> str.endsWith("x"));//true
        System.out.println("noneMatch() -> All names list element ends with 'x' ? : " + noneMatch);

        List<String> emptyList = List.of();
        String emptyFirst = emptyList.stream()
                .findFirst().orElse("orElse() no element");
        System.out.println("emptyFirst = " + emptyFirst);

        //reduce
        //SUM
        int sum = nums.stream()
                .reduce(0, Integer::sum)
                .intValue();
        System.out.println("Sum : " + sum);

        //Multiply
        Supplier<Integer> supplier = () -> 0;
        Integer product = nums.stream()
                .reduce((x1, x2) -> x1 * x2)
                .orElseGet(supplier);
        System.out.println("Product : " + product);

        //max
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(Comparator.naturalOrder());
        BinaryOperator<Integer> objectBinaryOperator = BinaryOperator.maxBy(Comparator.comparing(x -> x));
        Integer maxUsingReduce = nums.stream()
                .reduce(maxBy)
                .orElse(0);
        System.out.println("maxUsingReduce = " + maxUsingReduce);

        Integer maxUsingMax = nums.stream()
                .max(Comparator.comparingInt(x -> x))
                .orElse(0);
        System.out.println("maxUsingMax = " + maxUsingMax);

        //min
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(Comparator.comparingInt(x -> x));
        Integer minUsingReduce = nums.stream()
                .reduce(minBy)
                .orElse(0);
        System.out.println("minUsingReduce = " + minUsingReduce);

        Integer minUsingMin = nums.stream()
                .min(Comparator.comparing(x -> x))
                .orElse(0);
        System.out.println("minUsingMin = " + minUsingMin);

        //
        System.out.println("takeWhile example : ");
        Stream.of(1, 2, 3, 4, 5, 0, 6)
                .takeWhile(num -> num > 0)
                .forEach(s -> System.out.print(s + " "));//skips 6

        System.out.println("\ndropWhile example : ");
        Stream.of(1, 2, 3, 4, 5, 0, 6)
                .dropWhile(num -> num > 0)
                .forEach(s -> System.out.print(s + " "));//0,6

        List<Integer> collect = IntStream.of(1, 2, 3, 4, 5)
                .boxed()
                .toList();
        System.out.println("\ncollect = " + collect);

    }
}
