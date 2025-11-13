package com.javatechie.multiple.ds.api.code;

public class FibonacciIterative {

    static void fib(int n) {

        System.out.println("\nFib series till " + n + "th element : ");
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int c = a + b;
            System.out.print(a + " ");
            a = b;
            b = c;
        }


    }


    public static void main(String[] args) {
        //numbers : 0, 1, 1, 2, 3, 5, 8..
        //index   : 0, 1, 2, 3, 4, 5, 6..
        //till 10th fibonacci number, print //n=10 means 10 values to be printed till index 9

        int n = 5;
        fib(n);
        n = 1;
        fib(n);
        n = 2;
        fib(n);
    }
}
