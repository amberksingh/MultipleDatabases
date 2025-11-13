package com.javatechie.multiple.ds.api.programs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class Streams7 {

    public static void main(String[] args) {

        List<Integer> numList = new ArrayList<>();
        numList.add(20);
        numList.add(10);
        numList.add(30);
        numList.add(27);
        numList.add(52);
        numList.add(48);
        numList.add(66);

        //BiFunction
        final String CONSTANT = "X";
        BiFunction<Integer, String, Integer> biFunc = (x1, x2) -> (x1.toString() + x2).length();
        numList.stream()
                .map(e -> biFunc.apply(e, CONSTANT))
                .forEach(System.out::println);

        //binaryOperator
        List<String> list = List.of("Bisht", "Kaul", "sharMa", "sIngh", "rao", "Dev");
        BinaryOperator<String> binaryOperator = (x1, x2) -> x1 + x2;
        List<String> list1 = list.stream()
                .map(e -> binaryOperator.apply(e, CONSTANT))
                .map(String::toLowerCase)
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("list1 = " + list1);

        //reduce
        //max
        Integer integerMax = numList.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(x -> x)))
                .orElse(0);
        System.out.println("integerMax = " + integerMax);
        //min
        Integer integerMin = numList.stream()
                .reduce(BinaryOperator.minBy(Comparator.comparing(x -> x)))
                .orElse(0);
        System.out.println("integerMin = " + integerMin);

        //lexicographical comparison
        BinaryOperator<String> stringBinaryOperator = BinaryOperator.maxBy(Comparator.naturalOrder());
        String lexiCompare = list.stream()
                .reduce(stringBinaryOperator)
                .orElse("");
        System.out.println("lexiCompare = " + lexiCompare);

        //length wise comparison
        Comparator<String> comparator = (s1, s2) -> s1.length() - s2.length();
        Comparator<String> comparator1 = Comparator.comparing(String::length);
        Comparator<String> comparator2 = Comparator.comparingInt(String::length);
        BinaryOperator<String> strLengthBinOperator = BinaryOperator.maxBy(comparator1);

        String lengthCompare = list.stream()
                .reduce(strLengthBinOperator)
                .orElse("");
        System.out.println("lengthCompare = " + lengthCompare);

    }
}
