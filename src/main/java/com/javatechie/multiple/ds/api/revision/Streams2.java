package com.javatechie.multiple.ds.api.revision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class Streams2 {

    public static void main(String[] args) {

        List<Integer> numList = new ArrayList<>();
        numList.add(20);
        numList.add(10);
        numList.add(30);
        numList.add(27);
        numList.add(52);
        numList.add(48);
        numList.add(66);

        //max, min
        Integer integerMax = numList.stream()
                .max(Comparator.naturalOrder())
                .orElse(0);
        System.out.println("integerMax = " + integerMax);

        Integer integerMin = numList.stream()
                .min(Comparator.naturalOrder())
                //.min(Comparator.reverseOrder())//doesn't work logically as min selects leftmost
                .orElse(0);
        System.out.println("integerMin = " + integerMin);

        Integer integer1Max = numList.stream()
                .max(Comparator.comparing(Function.identity()))
                .orElse(0);
        System.out.println("integer1Max = " + integer1Max);

        IntConsumer consumer = (num) -> System.out.println("Max using IntStream/mapToInt : " + num);
        OptionalInt max = numList.stream()
                .mapToInt(Integer::intValue)
                .max();
        max.ifPresent(consumer);

        BinaryOperator<Integer> binaryOperator = BinaryOperator.maxBy(Comparator.naturalOrder());
        Integer binOperatorMax = numList.stream()
                .reduce(binaryOperator)
                .orElse(0);
        System.out.println("binOperatorMax = " + binOperatorMax);

        BinaryOperator<Integer> binaryOperatorMin = BinaryOperator.minBy(Comparator.naturalOrder());
        Integer binOperatorMin = numList.stream()
                .reduce(binaryOperatorMin)
                .orElse(0);
        System.out.println("binOperatorMin = " + binOperatorMin);

        //sum
        BinaryOperator<Integer> sumBinOperator = Integer::sum;
        Integer integerSum = numList.stream()
                .reduce(sumBinOperator)
                .orElse(0);
        System.out.println("integerSum = " + integerSum);

        int sum = numList.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("sum = " + sum);




    }
}
