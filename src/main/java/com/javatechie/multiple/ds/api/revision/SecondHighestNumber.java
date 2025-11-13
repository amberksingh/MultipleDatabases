package com.javatechie.multiple.ds.api.revision;

import java.util.Comparator;
import java.util.List;

public class SecondHighestNumber {

    public static void main(String[] args) {

        List<Integer> list = List.of(10, 20, 30, 77, 8, 44, 5, 9, 87, 12, 100);
        Integer integer = list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("SecondHighestNumber = " + integer);

        //old way
        int secondHighest = Integer.MIN_VALUE;
        int highest = Integer.MIN_VALUE;
        for (int num : list) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num != highest) {
                secondHighest = num;
            }
        }
        System.out.println("SecondHighestNumber old way  = " + secondHighest);

    }
}
