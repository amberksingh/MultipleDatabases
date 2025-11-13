package com.javatechie.multiple.ds.api.code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams1 {

    public static void main(String[] args) {
        
        int[] emptyArr = {};
        int[] arr = {2, 5, 5, 7, 3, 4, 6, 8, 1, 9};
        
        //max
        int max = IntStream.of(arr)
                .max()
                .orElseThrow(() -> new RuntimeException("invalid data"));
        System.out.println("max = " + max);

        Integer integer = Arrays.stream(arr)
                .boxed()
                .max(Comparator.comparingInt(x -> x))
                .orElse(0);
        System.out.println("max integer = " + integer);

        //1. Case A: arr is a primitive int[]
        //int[] arr = {1, 4, 2};
        //
        //
        //Arrays.stream(arr) gives an IntStream (primitive stream).
        //
        //Methods on IntStream like reduce(...) expect:
        //
        //IntBinaryOperator (int, int -> int)
        //
        //
        //But you are passing:
        //
        //BinaryOperator<Integer> (Integer, Integer -> Integer)
        //
        //
        //That’s a mismatch. ❌ Compile-time error.
        //
        //That’s why you see the issue: in a primitive IntStream, x is a primitive int, not an Integer object

        //2. Case B: arr is an object Integer[]
        //Integer[] arr = {1, 4, 2};
        //
        //
        //Arrays.stream(arr) gives a Stream<Integer> (object stream).
        //
        //reduce(BinaryOperator.maxBy(...)) expects a BinaryOperator<T>.
        //
        //Here, T = Integer.
        //
        //So Comparator.comparingInt(x -> x) works because x is an Integer, auto-unboxed to int for the comparison. ✅ Works fine.

        //Why your reduce(BinaryOperator<Integer>) fails on int[]
        //
        //Because:
        //
        //IntStream.reduce() expects an IntBinaryOperator (signature: (int, int) -> int).
        //
        //You gave it a BinaryOperator<Integer> (signature: (Integer, Integer) -> Integer).
        //
        //Java can’t auto-convert IntStream → Stream<Integer> or auto-box at the operator boundary.
        //
        //✅ Summary
        //
        //Integer does not “become int on its own” inside an IntStream — the stream is fundamentally primitive.
        //
        //BinaryOperator<Integer> works only on Stream<Integer>.
        //
        //To make it work with int[], you must box the stream (.boxed()) so conversion from int → Integer is explicit.
        int i = Arrays.stream(arr)
                //.reduce(BinaryOperator.maxBy(Comparator.comparingInt(x -> x)))//doesn't work without .boxed()
                .boxed()
                .reduce(BinaryOperator.maxBy(Comparator.comparing(x -> x)))
                .orElseThrow();
        System.out.println("int max = " + i);

        Integer integer1 = Arrays.stream(arr)
                .mapToObj(Integer::valueOf)
                .max(Comparator.comparing(x -> x))
                .orElse(0);
        System.out.println("integer1 max = " + integer1);

        //frequency of odd and even numbers
        Map<Integer, Long> oddEven = Arrays.stream(arr)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                num -> num % 2,
                                Collectors.counting()
                        )
                );
        System.out.println("even frequency = " + oddEven.get(0));
        System.out.println("odd frequency = " + oddEven.get(1));


    }
}
