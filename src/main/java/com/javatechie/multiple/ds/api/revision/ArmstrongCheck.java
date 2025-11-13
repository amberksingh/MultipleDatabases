package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class ArmstrongCheck {

    //153 = 1^3 +5^3 + 3^3 = 153
    static boolean isArmstrong(int number) {

        int sum = 0;
        int original = number;
        int length = String.valueOf(number).length();
        while (number > 0) {
            int r = number % 10;
            sum += Math.pow(r, length);
            number /= 10;
        }
        System.out.println("sum old = " + sum);
        return Objects.equals(original, sum);
    }

    static boolean stringArmstrong(int number) {

        int original = number;
        int length = String.valueOf(number).length();
        double sum = Arrays.stream(String.valueOf(number).split(""))
                .mapToInt(Integer::valueOf)
                .mapToDouble(n -> Math.pow(n, length))
                .sum();
        System.out.println("sum string = " + sum);
        return sum == original;
    }

    static boolean charArmstrong(int number) {

        int original = number;
        String str = String.valueOf(number);
        double sum = str.chars()
                .map(Character::getNumericValue)
                .mapToObj(n -> Math.pow(n, str.length()))
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println("sum char = " + sum);
        return sum == original;
    }


    public static void main(String[] args) {

        System.out.println("Enter number : ");
        Scanner sc = new Scanner(System.in);
        //1
        int number = sc.nextInt();
        if (isArmstrong(number))
            System.out.println("Armstrong number");
        else
            System.out.println("NOT Armstrong");

        //2
        if (stringArmstrong(number))
            System.out.println("Armstrong number string way ");
        else
            System.out.println("NOT Armstrong string way");

        //3
        if (charArmstrong(number))
            System.out.println("Armstrong number char way ");
        else
            System.out.println("NOT Armstrong char way");
    }
}
