package com.javatechie.multiple.ds.api.programs;

import java.util.Map;

public class PrimeCheck {

    //Why i <= sqrt(num) is enough?
    //Because:
    //
    //If num = a * b, then at least one of the factors a or b must be ≤ √num
    //
    //Let’s see examples:
    //
    //🔸 Example 1: num = 36
    //Factors:
    //
    //(2, 18)
    //
    //(3, 12)
    //
    //(4, 9)
    //
    //(6, 6) ← square root
    //
    //After this, pairs like (9, 4) and (18, 2) repeat in reverse.
    //
    //✅ So if num has a divisor, it will be found before or at √num.
    //
    //🔸 Example 2: num = 37 (a prime)
    //sqrt(37) ≈ 6.08
    //
    //So we only check divisibility for: i = 2 to 6
    //
    //Since none of these divide 37, it's prime ✅

    static boolean checkPrime(int number) {

        boolean flag = true;
        if (number < 2) {
            flag = false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        int number = 7;
        String result = checkPrime(number) ? "PRIME" : "NOT PRIME";
        System.out.println("result = " + result);

    }
}
