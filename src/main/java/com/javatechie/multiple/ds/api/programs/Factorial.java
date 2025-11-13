package com.javatechie.multiple.ds.api.programs;

public class Factorial {

    static int factorial(int n) {

        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        int factorial = factorial(n);
        System.out.println("Factorial of " + n + " = " + factorial);
    }
}
