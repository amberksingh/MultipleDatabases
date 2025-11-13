package com.javatechie.multiple.ds.api.revision;

import java.util.stream.Stream;

public class AddDigitsTillSingleDigitResult {

    public static void main(String[] args) {
        //Given a non-negative integer num, repeatedly add all its digits until the
        //result has only one digit.
        int num = 9876;//3 answer
        int temp;
        while (num >= 10) {
            temp = 0;
            while (num > 0) {//t//t//t//f
                int r = num % 10;//6//7//8//9
                temp += r;//6//13//21//30
                num /= 10;//987//98//9//0
            }
            num = temp;
        }
        System.out.println("num = " + num);

        //streams
        int number = 9876;
        while (number >= 10) {
            int sum = Stream.of(String.valueOf(number).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
            number = sum;
        }
        System.out.println("num using streams = " + number);
    }
}
