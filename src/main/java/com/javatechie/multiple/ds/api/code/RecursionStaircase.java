package com.javatechie.multiple.ds.api.code;

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

    //for static 1,2 steps
    static int countWaysFixed(int n) {

        //base case
        if (n == 0 || n == 1)
            return 1;
        return countWaysFixed(n - 1) + countWaysFixed(n - 2);
    }


   //Function Meaning
   //static int countWaysWithVariableSteps(int n, int m)
   //
   //
   //n = total steps (the top you want to reach).
   //
   //m = maximum step size you are allowed to take in one move.
   //
   //At every position, you can choose to climb 1 step, 2 steps, … up to m steps.
   //
   //So:
   //👉 ways(n, m) = ways(n-1, m) + ways(n-2, m) + ... + ways(n-m, m)
   //
   //Base Cases
   //
   //If n < 0: invalid path → 0.
   //
   //If n == 0: exactly at the top → 1 (one valid way).
   //
   //The n == 1 special case isn’t needed, because recursion + n==0 + n<0 cover it automatically.
   //
   //Example Walkthrough
   //
   //Let’s try n = 3, m = 3 (you can take 1, 2, or 3 steps at a time).
   //
   //countWaysWithVariableSteps(3, 3)
   //= ways(2,3) + ways(1,3) + ways(0,3)
   //
   //A) ways(2,3)
   //= ways(1,3) + ways(0,3) + ways(-1,3)
   //= (ways(0,3)+ways(-1,3)+ways(-2,3)) + 1 + 0
   //= (1+0+0) + 1 + 0
   //= 2
   //
   //B) ways(1,3)
   //= ways(0,3) + ways(-1,3) + ways(-2,3)
   //= 1 + 0 + 0
   //= 1
   //
   //C) ways(0,3) = 1
   //Putting it together
   //ways(3,3) = ways(2,3) + ways(1,3) + ways(0,3)
   //= 2 + 1 + 1
   //= 4
   //
   //The 4 ways (n=3, m=3)
   //
   //1 + 1 + 1
   //
   //1 + 2
   //
   //2 + 1
   //
   //3
   //
   //Key Point
   //
   //The for loop tries every possible jump size from 1 to m.
   //
   //Recursion breaks it down until n==0 (valid) or n<0 (invalid).
   //
   //This is essentially the generalized Fibonacci (also called the m-step Fibonacci).
   //
   //👉 Do you want me to also show how this code reduces back to the classic Fibonacci staircase when m=2?
   //
   //You said:
   //
   //ways(0,3) = 1
   //
   //this means already on top while we had the chance to use 1,2,3 steps to reach top?
   //ChatGPT said:
   //
   //Yes 👍 exactly — that’s the meaning of
   //
   //if (n == 0) return 1;
   //
   //Why ways(0, m) = 1 ?
   //
   //n == 0 means you are already at the top.
   //
   //That situation represents one complete valid path you took to reach there.
   //
   //You don’t “take another step” after reaching the top — the journey is finished.
   //
   //So the 1 here doesn’t mean “one more step to take”, it means “we’ve successfully found one way”.
   //
   //How it works with m = 3
   //
   //When we compute:
   //
   //ways(3,3) = ways(2,3) + ways(1,3) + ways(0,3)
   //
   //
   //ways(2,3) = all paths where the last jump was 1 step.
   //
   //ways(1,3) = all paths where the last jump was 2 steps.
   //
   //ways(0,3) = all paths where the last jump was 3 steps.
   //
   //That ways(0,3) = 1 represents the case:
   //👉 “You directly jumped 3 steps to the top.”
   //
   //Intuition
   //
   //Think of ways(0) not as “standing still”, but as “a completed journey — success, count it as one way.”
   //
   //✅ So yes, when you see ways(0, m) = 1, it is telling you:
   //
   //“There was exactly one valid way that used the jump size (in this case 3) to finish at the top.”
   //
   //Would you like me to map each of the 4 paths for n=3,m=3 to these recursive calls so you see which branch (n-1, n-2, n-3) produces which path?
   //
   //You said:
   //
   //ways(2,3)
   //
   //this means we are below 2 steps below top and can use 1,2,3 steps to reach top?
   //ChatGPT said:
   //
   //Ahh 👍 you’re thinking correctly — let’s zoom in on that case:
   //
   //When we call
   //ways(2, 3)
   //
   //
   //it means:
   //
   //We are 2 steps below the top (because n = 2).
   //
   //We are allowed to climb up to 3 steps at a time.
   //
   //So the recursive formula will try:
   //
   //ways(2,3) = ways(1,3) + ways(0,3) + ways(-1,3)
   //
   //Interpret each branch
   //
   //ways(1,3): take 1 step first → now 1 step remains to the top.
   //
   //ways(0,3): take 2 steps → boom, you land exactly on the top.
   //
   //ways(-1,3): take 3 steps → you overshoot (invalid) → return 0.
   //
   //Put it together
   //
   //So being 2 below the top with m=3 means:
   //
   //You can still try 1, 2, or 3 steps.
   //
   //But only the 1-step and 2-step choices are valid paths.
   //
   //The 3-step overshoots, so that branch contributes 0.
   //
   //Example: paths from step 2 (n=2, m=3)
   //
   //1 + 1 (two 1-steps)
   //
   //2 (direct 2-step jump)
   //
   //That’s why ways(2,3) = 2. ✅
   //
   //👉 So your intuition is right:
   //“Being 2 below the top” means you can still attempt 1, 2, or 3 steps, but recursion + base cases make sure only the valid ones count.
    static int countWaysWithVariableSteps(int n, int m) {

        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
//        if (n == 1)
//            return 1;not needed here as it will get reduced to base case
//          countways(1,2)= countWays(0,2) + countWays(-1,2)
//                  = 1 + 0 = 1)
        int res = 0;
        for (int step = 1; step <= m; step++) {
            res += countWaysWithVariableSteps(n - step, m);
        }
        return res;
    }

    public static void main(String[] args) {

        int n = 3;
        System.out.println("no.of ways to reach " + n + "th step : " + countWaysFixed(n));

        n = 4;
        System.out.println("no.of ways to reach " + n + "th step : " + countWaysFixed(n));

        n = 6;
        System.out.println("no.of ways to reach " + n + "th step : " + countWaysFixed(n));

        n = 5;
        System.out.println("no.of ways to reach " + n + "th step : " + countWaysFixed(n));

        n = 4;//n is top
        int m = 2;//(2 means 1 or 2 steps at a time)
        System.out.println("Ways to reach " + n + "th step : " + countWaysWithVariableSteps(n, m));
    }
}
