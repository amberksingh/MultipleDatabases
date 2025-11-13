package com.javatechie.multiple.ds.api.code;

public class PowerOfTwo {

    public static void main(String[] args) {

        int number = 32, value = 32;
        //1st way
        if (number < 1)
            System.out.println("NOT power of two");
        while (number % 2 == 0) {
            number /= 2;
        }
        if (number == 1)
            System.out.println("Power of two");
        else
            System.out.println("NOT power of two");
        //2nd way

        if (value >= 1 && (value & (value - 1)) == 0)
            System.out.println("Power of two");
        else
            System.out.println("NOT power of two");

    }
}
