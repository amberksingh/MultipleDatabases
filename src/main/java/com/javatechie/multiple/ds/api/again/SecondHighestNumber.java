package com.javatechie.multiple.ds.api.again;

import java.util.Comparator;
import java.util.List;

public class SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> list = List.of(10, 20, 30, 77, 8, 44, 5, 9, 98, 12, 110);
        Integer integer = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("SecondHighestNumber = " + integer);

        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num != highest) {
                secondHighest = num;
            }
        }
        System.out.println("secondHighest = " + secondHighest);
    }
}
