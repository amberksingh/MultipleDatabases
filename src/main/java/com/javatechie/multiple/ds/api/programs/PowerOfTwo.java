package com.javatechie.multiple.ds.api.programs;

public class PowerOfTwo {

    static void usingBitwiseAnd(int number) {

        if (number > 0 && (number & (number - 1)) == 0)
            System.out.println("Power of two using bitwise and");
        else
            System.out.println("NOT Power of two using bitwise and");
    }

    static void oldSkool(int number) {

        if (number <= 0)
            System.out.println("NOT Power of 2 using old method");
        while (number % 2 == 0) {
            number = number / 2;
        }
        if (number == 1)
            System.out.println("Power of Two using old method");
        else
            System.out.println("NOT Power of two using old method");
    }

    public static void main(String[] args) {

        usingBitwiseAnd(7);
        oldSkool(7);

    }
}
