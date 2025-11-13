package com.javatechie.multiple.ds.api.programs;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

//1. Integer.parseInt(String s)
//
//Signature:
//
//public static int parseInt(String s)
//
//
//Return type: int (primitive).
//
//Behavior: Parses the string and returns a primitive integer.
//
//Example:
//
//int x = Integer.parseInt("123");
//System.out.println(x + 1); // 124
//
//
//No caching / object creation, just raw int.
//
//2. Integer.valueOf(String s)
//
//Signature:
//
//public static Integer valueOf(String s)
//
//
//Return type: Integer (wrapper object).
//
//Behavior: Parses the string into a primitive first, then boxes it into an Integer object.
//
//Uses Integer caching for values between -128 and 127.
//
//Example:
//
//Integer y = Integer.valueOf("123");
//System.out.println(y + 1); // 124 (autounboxed to int)
//
//
//If value is in cache range, no new object is created; outside range, a new Integer is allocated.
//
//3. Relationship between them
//
//Actually, Integer.valueOf internally calls Integer.parseInt:
//
//public static Integer valueOf(String s) throws NumberFormatException {
//    return Integer.valueOf(parseInt(s));
//}
//
//
//So the parsing logic is the same; the only difference is whether you want a primitive int or an Integer object.
//
//4. When to use which?
//
//Use parseInt → when you need a primitive int. Faster, no boxing.
//
//Use valueOf → when you specifically need an Integer object (e.g., working with collections like List<Integer> or
// using APIs that expect objects).

//public class ParseVsValueOfDemo {
//    public static void main(String[] args) {
//        // Using parseInt
//        int p = Integer.parseInt("-123");
//        System.out.println("parseInt result: " + p);  // -123
//
//        // Using valueOf
//        Integer v = Integer.valueOf("-123");
//        System.out.println("valueOf result: " + v);   // -123
//
//        // Caching check
//        Integer a = Integer.valueOf("-128");
//        Integer b = Integer.valueOf("-128");
//        System.out.println("(-128) a == b ? " + (a == b)); // true (cached)
//
//        Integer c = Integer.valueOf("-129");
//        Integer d = Integer.valueOf("-129");
//        System.out.println("(-129) c == d ? " + (c == d)); // false (outside cache)
//    }
//}

//Explanation
//
//parseInt("-123") → returns primitive int -123.
//
//valueOf("-123") → wraps it into Integer -123.
//
//Caching works for all integers in the range -128 to 127.
//
//valueOf("-128") → returns the cached instance, so a == b is true.
//
//valueOf("-129") → outside cache, so two different objects, c == d is false.
//
//✅ So even with negatives, caching works the same way for the range [-128, 127].

public class Stream2 {

    public static void main(String[] args) {

        List<String> nums = List.of("1", "2", "3");
        //sum of above number in int
        int sum = nums.stream()
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("int sum = " + sum);

        int sum1 = nums.stream()
                .mapToInt(Integer::parseInt)
                //.mapToInt(Integer::intValue)
                .sum();
        System.out.println("int sum1 = " + sum1);

        //mapToDouble
        double sum2 = nums.stream()
                .mapToDouble(Double::parseDouble)
                .sum();
        System.out.println("double sum2 = " + sum2);

        double sum3 = nums.stream()
                .mapToDouble(Double::valueOf)
                .sum();
        System.out.println("double sum3 = " + sum3);

        //mapToLong
        long longMax = nums.stream()
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0);
        System.out.println("long max = " + longMax);

        nums.stream()
                .mapToLong(Long::valueOf)
                .average()
                .ifPresent(s -> System.out.println(s));

        //Binaryoperator
        List<Integer> list = List.of();
        BinaryOperator<Integer> multiply = (n1, n2) -> n1 * n2;
        BinaryOperator<Integer> sum11 = Integer::sum;

        Integer reduce = list.stream()
                .reduce(1, multiply);
        System.out.println("multiply : " + reduce);

        Optional<Integer> reduce1 = list.stream()
                .reduce(sum11);
        if (reduce1.isPresent()) {
            System.out.println("sum : " + reduce1.get());
        } else {
            System.out.println("invalid sum : " + reduce1.orElse(0));
        }
    }
}
