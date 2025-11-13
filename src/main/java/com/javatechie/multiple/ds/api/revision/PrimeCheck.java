package com.javatechie.multiple.ds.api.revision;

public class PrimeCheck {

    static boolean isPrime(int number) {

        boolean flag = true;

        if (number < 2)
            flag = false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        if (isPrime(11))
            System.out.println("PRIME NUmber");
        else
            System.out.println("NOT PRIME Number");
    }
}
