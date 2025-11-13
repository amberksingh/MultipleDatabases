package com.javatechie.multiple.ds.api.programs;

public class FibonacciRecursive {

    static int fib(int n) {

        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

        //fib(5);//till 4th index 0,1,1,2,3
        //int n = 1;//till 0
        int n = 5;//till 4th index and 5 no.s in total
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": " + fib(i) + " ");
        }

    }
}
