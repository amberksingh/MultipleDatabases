package com.javatechie.multiple.ds.api.revision;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {

    public static void main(String[] args) {

        List<Integer> emptyList = List.of();//empty list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

       BinaryOperator<Integer> binaryOperator = (num1, num2) -> num1 + num2;
        Integer integerSum = numbers.stream()
                .reduce(binaryOperator)
                .orElse(0);
        System.out.println("integerSum = " + integerSum);

        Integer integerSum1 = numbers.stream()
                .reduce(0, binaryOperator);
        System.out.println("integerSum1 = " + integerSum1);

//        Integer integer = emptyList.stream()
//                .reduce(binaryOperator)
//                .orElseThrow(() -> new RuntimeException("emptyList hai jee!!!"));
//        System.out.println("integer = " + integer);

//        Integer integer1 = emptyList.stream()
//                .reduce(binaryOperator)
//                .orElseThrow();
//        System.out.println("integer1 = " + integer1);

        //max, min
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(Comparator.naturalOrder());
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(Comparator.naturalOrder());
        Integer integerMax = numbers.stream()
                .reduce(maxBy)
                .orElse(0);
        Integer integerMin = numbers.stream()
                .reduce(minBy)
                .orElse(0);
        System.out.println("integerMin = " + integerMin);
        System.out.println("integerMax = " + integerMax);

        int iMax = numbers.stream()
                .mapToInt(Integer::intValue)
                .max().orElse(0);
        int iMin = numbers.stream()
                .mapToInt(Integer::intValue)
                .min().orElse(0);
        System.out.println("integerMin = " + iMin);
        System.out.println("integerMax = " + iMax);
    }
}
