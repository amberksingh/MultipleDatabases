package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PalindromeDemo {

    static boolean isPalindrome(String str) {

        String[] split = str.split("");
        String reverse = Arrays.stream(split)
                .reduce((s1, s2) -> s2 + s1)
                .orElse(null);
        System.out.println("rev reduce = " + reverse);
        return str.equals(reverse);
    }

    static boolean isPalindromeMethod(String str) {

        //works only for string words case. not reverse individual chars in single word
        String[] split = str.split("");
//        String rev = Arrays.stream(split)
//                .map(StringBuffer::new)
//                .map(StringBuffer::reverse)
//                .map(StringBuffer::toString)
//                .collect(Collectors.joining());
        StringBuffer sb = new StringBuffer(str);
        String rev = sb.reverse().toString();
        System.out.println("rev stringbuffer = " + rev);
        return rev.equals(str);
    }

    static boolean numberWay(int number) {
        int rev = 0;
        int original = number;
        while (number > 0) {
            int r = number % 10;
            rev = (rev * 10) + r;
            number /= 10;
        }
        System.out.println("rev number : " + rev);
        return rev == original;
    }

    public static void main(String[] args) {

        String str = "hannah";
        String str1 = "malayalam";
        String str2 = "abc";

        boolean palindrome = isPalindrome(str2);
        String res = palindrome ? "palindrome" : "NOT palindrome";
        System.out.println(res);

        boolean palindrome1 = isPalindromeMethod(str2);
        String res1 = palindrome1 ? "palindrome" : "NOT palindrome";
        System.out.println(res1);

        int number = 4053;
        int number1 = 5115;
        int number2 = 4004;
        boolean palindrome2 = numberWay(number);
        String res2 = palindrome2 ? "palindrome" : "NOT palindrome";
        System.out.println(res2);

    }
}
