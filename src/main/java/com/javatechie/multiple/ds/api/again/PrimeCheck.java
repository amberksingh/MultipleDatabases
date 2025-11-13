package com.javatechie.multiple.ds.api.again;

public class PrimeCheck {

    static boolean isPrime(int number) {

        boolean flag = true;
        if (number < 2) {
            flag = false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;

    }

    public static void main(String[] args) {

        //int number  = 5;
        //int number  = 7;
        //int number  = 16;
        //int number  = 17;
        int number  = 33;

        if (isPrime(number))
            System.out.println("prime");
        else
            System.out.println("Not Prime");

    }
}
