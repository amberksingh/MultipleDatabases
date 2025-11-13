package com.javatechie.multiple.ds.api.revision;

public class PowerOfTwo {

    public static void main(String[] args) {

        int number = 7;
        if (number > 0 && (number & (number - 1)) == 0)
            System.out.println("power of two");
        else
            System.out.println("NOT power of two");

        if (number <= 0)
            System.out.println("NOT Power of 2 using old method");
        while (number % 2 == 0) {
            number /= 2;
        }
        if (number == 1)
            System.out.println("power of two using old way");
        else
            System.out.println("NOT power of two using old way");
    }
}
