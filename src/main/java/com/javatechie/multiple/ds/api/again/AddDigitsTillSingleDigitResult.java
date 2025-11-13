package com.javatechie.multiple.ds.api.again;

import java.util.Arrays;

public class AddDigitsTillSingleDigitResult {

    //Given a non-negative integer num, repeatedly add all its digits until the
    //result has only one digit.
    public static void main(String[] args) {

        //int num = 9876;//3 answer
        int num = 1345;//4 answer
        //int number = 9876;//3 answer
        int number = 1345;//4 answer

        //old skool
        //int sum = 0;
        while (num >= 10) {
            int r = 0;
            while (num > 0) {
                r += num % 10;//6//7//8//9--0//3
                num = num / 10;//987//98//9//0--3//0
            }
            num = r;
        }
        System.out.println("Ans = " + num);

        //2nd stream way
        while (number >= 10) {
            int sum = Arrays.stream(String.valueOf(number).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
            number = sum;
        }
        System.out.println("Ans = " + number);
    }
}
