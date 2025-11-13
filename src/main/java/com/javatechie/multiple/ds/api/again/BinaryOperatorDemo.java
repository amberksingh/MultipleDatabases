package com.javatechie.multiple.ds.api.again;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class BinaryOperatorDemo {

    public static void main(String[] args) {

        List<Integer> emptyList = List.of();//empty list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        BinaryOperator<Integer> binaryOperator = Integer::sum;
        IntBinaryOperator intBinaryOperator = Integer::sum;
        Optional<Integer> reduce = numbers.stream()
                .reduce(binaryOperator);
        System.out.println("reduce.get() = " + reduce.get());

        int asInt = numbers.stream()
                .mapToInt(Integer::intValue)
                //.reduce((n1, n2) -> n1 + n2)
                .reduce(intBinaryOperator)
                .getAsInt();
        System.out.println("asInt = " + asInt);

//        Integer integer = emptyList.stream()
//                .reduce(binaryOperator)
//                .orElseThrow();//NoSuchElementException

        //max
        int max = numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        System.out.println("max = " + max);

        Integer integer = numbers.stream()
                .reduce(BinaryOperator.maxBy(Comparator.naturalOrder()))
                .orElse(0);
        System.out.println("integer = " + integer);

        //min
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(Comparator.comparingInt(Integer::intValue));
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(Comparator.comparingInt(x -> x));

        Integer integer1 = numbers.stream()
                .reduce(maxBy)
                .orElse(0);
        System.out.println("maxBy = " + integer1);

        Integer integer2 = numbers.stream()
                .reduce(minBy)
                .orElse(0);
        System.out.println("minBy = " + integer2);

    }
}
