package com.javatechie.multiple.ds.api.code;

public class FibonacciRecursive {

    static int fib(int n) {
        //base case
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

        //int n =4 means -> fibonacci(0), fibonacci(1), fibonacci(2), fibonacci(3) till 4th fib number
        int n = 5;//0,1,1,2,3. n=5 means 5 values to be printed till index 4
        System.out.println("Fib series till " + n + "th :");
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + " ");
        }

    }
}
