package com.javatechie.multiple.ds.api.programs;

//The logic behind countWays(1) and countWays(0)
//
//Think of climbing stairs as a journey to the top.
//At each step, you ask: “How many ways can I get to the top from here?”
//
//🔹 countWays(1)
//
//This means:
//👉 “If the staircase has 1 step total, how many ways can I reach the top?”
//
//Logically:
//
//You’re standing at the ground.
//
//There’s only 1 step to climb.
//
//Only one way exists → take a single step.
//
//So:
//
//countWays(1) = 1
//
//🔹 countWays(0)
//
//This means:
//👉 “If the staircase has 0 steps, how many ways can I reach the top?”
//
//Logically:
//
//You’re already at the top.
//
//You don’t need to move.
//
//There is exactly 1 trivial way: “do nothing.”
//
//So:
//
//countWays(0) = 1
//
//🔑 The Recursion Intuition
//
//When you write:
//
//countWays(2) = countWays(1) + countWays(0)
//
//
//It means:
//
//To finish a 2-step staircase, your last move was either:
//
//1 step (so you were at the top of a 1-step staircase before), OR
//
//2 steps (so you were at the top of a 0-step staircase before).
//
//That’s why you add countWays(1) and countWays(0).
//
//✅ In simple words
//
//countWays(1) → the number of ways to climb a staircase with 1 step.
//
//countWays(0) → the number of ways to climb a staircase with 0 steps (i.e. already at the top).
//
//They’re not positions below the top — they are sizes of the staircase.
public class RecursionStaircase {

    static int countWaysWithVariableSteps(int n, int m) {

        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        int result = 0;
        for (int step = 1; step <= m; step++) {
            result += countWaysWithVariableSteps(n - step, m);
        }
        return result;
    }

    static int countWays(int n) {

        if (n == 0)
            return 1;
        if (n == 1)
            return 1;
        if (n < 0)
            return 0;
        return countWays(n - 1) + countWays(n - 2);
    }

    public static void main(String[] args) {

        int n = 4, m = 2;
        System.out.println("Ways to reach " + n + "th step : " + countWaysWithVariableSteps(n, m));
        n = 3;
        //System.out.println("Ways to reach " + n + "th step : " + countWaysWithVariableSteps(n, m));
//        n = 0;
//        System.out.println("Steps required from " + n + "th step : " + countWays(n));//1
//        n = 1;
//        System.out.println("Steps required from " + n + "th step : " + countWays(n));//1
//        n = 2;
//        System.out.println("Steps required from " + n + "th step : " + countWays(n));//2
//        n = 3;
//        System.out.println("Steps required from " + n + "th step : " + countWays(n));//3
    }
}
