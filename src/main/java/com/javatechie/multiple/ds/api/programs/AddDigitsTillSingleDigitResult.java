package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;

public class AddDigitsTillSingleDigitResult {

    //Given a non-negative integer num, repeatedly add all its digits until the
    //result has only one digit.
    public static void main(String[] args) {

        int num = 9876;//3 answer
        int intNumber = num;//copy for stream
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {//true//true//true//false
                int r = num % 10;//6//7//8//9
                sum += r;//6//13//21//30
                num /= 10;//987//98//9//0
            }
            num = sum;//30
        }
        System.out.println("result is " + num);//answer 3

        //int intNumber =
        //String number = String.valueOf(num);
        while (intNumber >= 10) {
            //String number = String.valueOf(intNumber);
            int numSum = Arrays.stream(String.valueOf(intNumber).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
            intNumber = numSum;
        }
        System.out.println("result using streams = " + intNumber);
    }
}
