package com.javatechie.multiple.ds.api.programs;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> list = List.of(10, 20, 30, 77, 8, 44, 5, 9, 100, 12, 100);
        Integer second = list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().orElse(0);
        System.out.println("SecondHighestNumber using streams = " + second);

        int secondHighest = Integer.MIN_VALUE;//7
        int highest = Integer.MIN_VALUE;//10

        for (int n : list) {
            if (n > highest) {
                secondHighest = highest;
                highest = n;
            } else if (n > secondHighest && n!=highest) {
                secondHighest = n;
            }
        }
        System.out.println("secondHighest = " + secondHighest);


    }
}
