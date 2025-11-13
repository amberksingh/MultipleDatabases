package com.javatechie.multiple.ds.api.code;

public class PrimeCheck {

    static boolean checkPrime(int num) {

        boolean flag = true;
        if (num < 2) {
            flag = false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                flag = false;
                break;
            }

        }
        return flag;
    }

    public static void main(String[] args) {
        int num = 11;
        boolean res = checkPrime(num);
        System.out.println("Is " + num + " prime : " + res);
    }
}
