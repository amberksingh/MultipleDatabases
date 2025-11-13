package com.javatechie.multiple.ds.api.again;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NumbersStartingWithGivenNumber {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(10, 14, 22, 25, 88, 84, 17, 15);
        Function<Integer, Character> function = (num) -> String.valueOf(num).charAt(0);

        //groupingBy
        Map<Character, List<Integer>> collect = list.stream()
                .collect(
                        Collectors.groupingBy(
                                function
                        )
                );
        System.out.println("collect = " + collect);

        //toMap
        Map<Character, List<Integer>> collect1 = list.stream()
                .collect(
                        Collectors.toMap(
                                function,
                                num -> {
                                    List<Integer> l = new ArrayList<>();
                                    l.add(num);
                                    return l;
                                },
                                (oldVal, newVal) -> {
                                    oldVal.addAll(newVal);
                                    return oldVal;
                                }

                        )
                );
        System.out.println("collect1 = " + collect1);
    }
}
