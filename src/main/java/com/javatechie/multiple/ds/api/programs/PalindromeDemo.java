package com.javatechie.multiple.ds.api.programs;

import java.util.stream.Stream;
//break; — Immediate Exit from the Closest Loop
//It exits the nearest enclosing loop or switch statement entirely, not just the if block.
//continue vs   break
//Keyword	    Effect
//break 	    Exits the entire loop
//continue	    Skips current iteration, moves to next loop iteration

//for (int i = 1; i <= 5; i++) {
//    if (i == 3) break;      // Exits the loop when i == 3
//    System.out.println(i);
//}
//Output: 1 2

//for (int i = 1; i <= 5; i++) {
//    if (i == 3) continue;   // Skips i == 3
//    System.out.println(i);
//}
//Output: 1 2 4 5
//✅ Summary
//break exits the whole loop, even if written inside an if.
//
//break does not just exit the if block.
//
//Use continue if you only want to skip to the next iteration.
public class PalindromeDemo {

    static boolean stringPalindrome(String str) {

        System.out.println("original string = " + str);
        StringBuffer sb = new StringBuffer(str);//StringBuffer is synchronized not StringBuilder
        String revString = sb.reverse().toString();
        System.out.println("revString = " + revString);
        if (revString.equals(str))
            return true;
        return false;
    }

    static boolean stringPalindromeMethod(String str) {

        System.out.println("original string = " + str);
        String revString = Stream.of(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .orElse("");
        System.out.println("revString = " + revString);
        return revString.equals(str);
    }

    static boolean numberPalindrome(int number) {

        System.out.println("original number = " + number);
        int originalCopy = number;//3223
        int rev = 0, r;
        while (number > 0) {
            r = number % 10;//3//2//2//3
            rev = (rev * 10) + r;//3//32//322//3223
            number = number / 10;//322//32//3//0
        }
        System.out.println("reverse number = " + rev);
        return rev == originalCopy;
    }


    public static void main(String[] args) {

        String stringResult = PalindromeDemo.stringPalindrome("Arun") ? "Palindrome" : "NOT Palindrome";
        System.out.println("stringResult = " + stringResult);
        System.out.println();

        String numberResult = PalindromeDemo.numberPalindrome(874985) ? "Palindrome" : "NOT Palindrome";
        System.out.println("numberResult = " + numberResult);
        System.out.println();

        String stringReduce = PalindromeDemo.stringPalindromeMethod("hannah") ? "Palindrome" : "NOT Palindrome";
        System.out.println("stringReduce = " + stringReduce);
        System.out.println();

    }
}
