package com.javatechie.multiple.ds.api.revision;

public class RecursionStaircase {

    //In the staircase problem, the way we usually define it is:
    //
    //n = the number of steps to climb (top is at step n).
    //
    //You start at step 0.
    //
    //You want to count distinct ways to reach exactly step n.
    //Example: n = 2
    //
    //Means: the staircase has 2 steps, and the top is at step 2.
    //
    //Ways to reach the top:
    //
    //Take 1 + 1
    //
    //Take 2
    //
    //So countWays(2) = 2.
    //
    //Example: n = 3
    //
    //Means: the staircase has 3 steps, and the top is at step 3.
    //
    //Ways:
    //
    //1 + 1 + 1
    //
    //1 + 2
    //
    //2 + 1
    //
    //So countWays(3) = 3.
    //
    //Base cases in this context
    //
    //countWays(0) = 1 → already at the top (one way = do nothing).
    //
    //countWays(1) = 1 → only one way (take a single step).
    //
    //✅ SO YES: N IS THE NUMBER OF STEPS, AND THE TOP IS AT N.

    //====================================================
    //countWaysFixed(4) example
    //ChatGPT said:
    //
    //Great! Let’s carefully work out countWaysFixed(4) step by step.
    //
    //Your function is:
    //
    //static int countWaysFixed(int n) {
    //    if (n == 0 || n == 1)
    //        return 1;
    //    return countWaysFixed(n - 1) + countWaysFixed(n - 2);
    //}
    //
    //Step 1: Expand countWaysFixed(4)
    //countWaysFixed(4)
    //= countWaysFixed(3) + countWaysFixed(2)
    //
    //Step 2: Evaluate each part
    //A) Solve countWaysFixed(3)
    //countWaysFixed(3)
    //= countWaysFixed(2) + countWaysFixed(1)
    //
    //
    //countWaysFixed(2) = countWaysFixed(1) + countWaysFixed(0) = 1 + 1 = 2
    //
    //countWaysFixed(1) = 1
    //
    //So,
    //
    //countWaysFixed(3) = 2 + 1 = 3
    //
    //B) Solve countWaysFixed(2)
    //countWaysFixed(2)
    //= countWaysFixed(1) + countWaysFixed(0)
    //= 1 + 1 = 2
    //
    //Step 3: Put it back
    //countWaysFixed(4) = countWaysFixed(3) + countWaysFixed(2)
    //= 3 + 2
    //= 5
    //
    //Step 4: Interpret the result (5 ways)
    //
    //The 5 distinct ways to climb 4 steps are:
    //
    //1 + 1 + 1 + 1
    //
    //1 + 1 + 2
    //
    //1 + 2 + 1
    //
    //2 + 1 + 1
    //
    //2 + 2
    //
    //✅ So countWaysFixed(4) = 5, which matches the Fibonacci sequence.
    //
    //Do you want me to also draw the recursion tree for n=4, so you can see how each call branches and adds up to 5?
    //
    //You said:
    //
    //`countWaysFixed(4) = countWaysFixed(3) + countWaysFixed(2)`
    //
    //so that means in logical way that to reach 4th step/top is 4 here. we need to find ways to reach 3 and add to
    // it no.of ways to reach 2? and this split already has base cases considered in it which will get added using recursion?
    //ChatGPT said:
    //
    //Exactly 👍 — you’ve got the logic right. Let’s restate it clearly:
    //
    //To reach step 4 (the top), your last move could only be:
    //
    //A 1-step from step 3 → so you must first find the number of ways to reach step 3.
    //
    //A 2-step from step 2 → so you must first find the number of ways to reach step 2.
    //
    //That’s why the formula is:
    //
    //ways(4) = ways(3) + ways(2)
    //
    //Where recursion + base cases come in
    //
    //When you break it down (ways(3) and ways(2)), each of those again splits into their subproblems (ways(2) + ways(1), etc.).
    //
    //Eventually you hit the base cases:
    //
    //ways(0) = 1 → one way (do nothing, you’re already at the start/top).
    //
    //ways(1) = 1 → one way (just take a single 1-step).
    //
    //Those base case values automatically bubble up through recursion and get summed at each level.
    //
    //For n=4
    //
    //So yes:
    //
    //Step 4 is solved by looking back at step 3 and step 2.
    //
    //Their counts are already including all possible smaller combinations (like 1+1+2, 2+1+1, etc.),
    // thanks to recursion reaching the base cases.
    //
    //Adding them together gives the total number of ways.
    //
    //👉 So the recursion formula doesn’t “invent” the ways itself — it just keeps reducing the problem until the simple
    // truths (base cases) are reached, and then builds the answer by combining them.
    //
    //Would you like me to sketch this as a recursion tree for n=4 so you can literally see how the base cases (1’s) add up to 5?
    //
    //You said:
    //
    //A **2-step** from step 2 → so you must first find the number of ways to reach step 2.
    //
    //2 step meaning it will include both 1,1 and 2 directlt right eventually?
    //ChatGPT said:
    //
    //Good question 👌 — let’s clear this carefully.
    //
    //When we say “a 2-step from step 2”, we mean:
    //
    //Your last jump to reach step 4 was a single 2-step.
    //
    //That means you must have been standing on step 2 just before.
    //
    //So the total ways contributed here = all the possible ways to reach step 2 (whatever those are),
    // and then you add this final 2-step move.
    //
    //Does this include both 1+1 and 2 directly?
    //
    //✅ Yes, but indirectly through recursion.
    //
    //To reach step 2, recursion has already counted:
    //
    //1 + 1
    //
    //2 directly
    //
    //So when we say ways to reach step 2, those possibilities are already included.
    //
    //Then from each of those, you just append the final +2 move to jump from step 2 → step 4.
    //
    //Example (n = 4)
    //
    //From step 3 + 1 step
    //
    //(1 + 1 + 1) + 1
    //
    //(1 + 2) + 1
    //
    //(2 + 1) + 1
    //
    //From step 2 + 2 step
    //
    //(1 + 1) + 2
    //
    //(2) + 2
    //
    //So the recursion naturally handles both the “two 1’s” and the “direct 2” inside ways(2).
    //
    //👉 In other words:
    //You don’t separately code for 1+1 and 2 when taking a 2-step. The recursive call ways(n-2) already ensures those are included.
    static int countWaysFixed(int n) {

        //base case
        if (n == 0 || n == 1)
            return 1;
        return countWaysFixed(n - 1) + countWaysFixed(n - 2);
    }

    static int countWaysWithVariableSteps(int n, int m) {

        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        int res = 0;
        for (int step = 1; step <= m; step++) {
            res += countWaysWithVariableSteps(n - step, m);
        }
        return res;
    }

    public static void main(String[] args) {

        int n = 5;
        System.out.println("no.of ways to reach " + n + "th step : " + countWaysFixed(n));

        int n1 = 3;//n1 is top
        int m1 = 3;//(m1=2 means 1 or 2 steps at a time)
        System.out.println("Ways to reach " + n + "th step : " + countWaysWithVariableSteps(n1, m1));
    }
}
