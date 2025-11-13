package com.javatechie.multiple.ds.api.programs;

import java.util.Scanner;
import java.util.stream.Stream;

public class ArmstrongCheck {

    static boolean oldWayNoString(int number) {
        //153
        int length = String.valueOf(number).length();
        int original = number;
        int sum = 0;
        while (number > 0) {//true//true
            int r = number % 10;//3//5//
            sum += Math.pow(r, length);//27//152//153
            number = number / 10;//15//1
        }
        return original == sum;
    }

    static boolean stringWay(int number) {
        //153
        String stringNum = String.valueOf(number);
        double sum = Stream.of(stringNum.split(""))
                .mapToInt(Integer::parseInt)
                .mapToDouble(digit -> Math.pow(digit, stringNum.length()))
                .sum();
        return number == sum;
    }

    static boolean characterNumericWay(int number) {

        String stringNum = String.valueOf(number);
        Double sum = stringNum.chars()
                //.peek(System.out::println)
                .map(Character::getNumericValue)//IMPORTANT
                .mapToObj(digit -> Math.pow(digit, stringNum.length()))
                .reduce(Double::sum).orElse(0d);
        return number == sum;
    }

    public static void main(String[] args) {

        System.out.println("Enter number : ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        String oldWay = oldWayNoString(number) ? "Armstrong number" : "NOT Armstrong number";
        System.out.println("oldway result = " + oldWay);

        String stringWay = stringWay(number) ? "Armstrong number" : "NOT Armstrong number";
        System.out.println("stringWay result = " + stringWay);

        String characterNumericWay = characterNumericWay(number) ? "Armstrong number" : "NOT Armstrong number";
        System.out.println("characterNumericWay result = " + characterNumericWay);

    }
}
