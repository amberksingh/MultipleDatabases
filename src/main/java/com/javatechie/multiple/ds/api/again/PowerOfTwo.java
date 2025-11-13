package com.javatechie.multiple.ds.api.again;

public class PowerOfTwo {

    public static void main(String[] args) {

        int num = -1;
        if (num > 0 && (num & (num - 1)) == 0)
            System.out.println("Power of two using bitwise and");
        else
            System.out.println("NOT power of 2 using bitwise and ");

        //old skool
        while (num > 0 && num % 2 == 0) {
            num /= 2;
        }

        if (num == 1)
            System.out.println("Power of two old skool");
        else
            System.out.println("NOT power of 2 old skool");
    }
}
