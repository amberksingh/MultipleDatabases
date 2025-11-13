package com.javatechie.multiple.ds.api.programs;

public class FibonacciIterative {

    //In Fibonacci, the series usually starts from index 0 like this:
    //Index:       0  1  2  3  4  5 ...
    //Value:       0  1  1  2  3  5 ...
    //So:
    //
    //fibonacci(0) → 0
    //
    //fibonacci(1) → 1
    //
    //fibonacci(2) → 1
    //
    //fibonacci(3) → 2
    static void fib(int n) {

        System.out.println("Fib series : ");
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int c = a + b;
            System.out.print(a + " ");//0 1
            a = b;
            b = c;
        }

    }

    public static void main(String[] args) {

        fib(5);//till 10th fibonacci number, print //n=10 means 10 values to be printed till index 9
    }
}
