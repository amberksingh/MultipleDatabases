package com.javatechie.multiple.ds.api.again;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Stream2Stuff {

    public static void main(String[] args) {
        //number list/////
        List<Integer> numList = new ArrayList<>();
        numList.add(20);
        numList.add(10);
        numList.add(30);
        numList.add(27);
        numList.add(52);
        numList.add(48);
        numList.add(66);

        BiFunction<Integer, String, Integer> biFunction = (num, str) -> {
            return (num + str).toUpperCase().length();
        };
        Integer arunLength = biFunction.apply(10, "arun");
        System.out.println("arunLength = " + arunLength);

        BinaryOperator<Integer> binaryOperator = (num1, num2) -> num1 * num2;
        Integer square = binaryOperator.apply(10, 10);
        System.out.println("square = " + square);

        final String constant = "x";
        List<String> list = List.of("Bisht", "Kaul", "sharMa", "sIngh", "rao", "Dev");
        BinaryOperator<String> stringBinaryOperator = (s1, s2) -> s1.toLowerCase().concat(s2.toUpperCase());

        String[] array = list.stream()
                .map(s -> stringBinaryOperator.apply(s, constant))
                .toArray(String[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        BinaryOperator<Integer> minBy = BinaryOperator.minBy(Comparator.naturalOrder());
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(Comparator.naturalOrder());
        Integer min = numList.stream()
                .reduce(minBy)
                .orElse(0);
        System.out.println("min = " + min);

        Integer max = numList.stream()
                .reduce(maxBy)
                .orElse(0);
        System.out.println("max = " + max);
        
        //sum
        BinaryOperator<Integer> sum = Integer::sum;
        Integer sumReduce = numList.stream()
                .reduce(sum)
                .orElse(0);
        System.out.println("sumReduce = " + sumReduce);

        int sum1 = numList.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum1 = " + sum1);

        //longer string by length
        Comparator<String> strLengthComp = Comparator.comparing(String::length);
        Comparator<String> strLengthComp1 = Comparator.comparing(String::length, Comparator.reverseOrder());

        //asc
        List<String> list1 = list.stream()
                .sorted(strLengthComp)
                .toList();
        System.out.println("asc order : ");
        list1.forEach(System.out::println);

        System.out.println("desc order : ");
        List<String> list2 = list.stream()
                .sorted(strLengthComp1)
                .toList();
        list2.forEach(System.out::println);

        //lexicographically
        Comparator<String> strLexAsc = Comparator.comparing(Function.identity());

        List<String> list3 = list.stream()
                .sorted(strLexAsc)
                .toList();
        System.out.println("lexico asc order = " + list3);

        //this doesn't work due to comparable type issue.
        //Comparator<String> strLexDesc = Comparator.comparing(Function.identity(), Comparator.reverseOrder());

        Comparator<String> strLexDesc1 = Comparator.comparing(x -> x, Comparator.reverseOrder());
        Comparator<String> strLexDesc2 = Comparator.comparing(Function.<String>identity(), Comparator.reverseOrder());
        Comparator<String> strLexDesc3 = Comparator.comparing(Function.<String>identity()).reversed();
        Comparator<String> strLexDesc4 = Comparator.comparing((String x) -> x).reversed();
        List<String> list4 = list.stream()
                //.sorted(strLexDesc)
                //.sorted(strLexDesc1)
                .sorted(strLexDesc2)
                //.sorted(strLexDesc3)
                //.sorted(strLexDesc4)
                .toList();
        System.out.println("lexico desc order = " + list4);

    }

}
