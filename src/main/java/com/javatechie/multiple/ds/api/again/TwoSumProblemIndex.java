package com.javatechie.multiple.ds.api.again;

import java.util.HashMap;
import java.util.Map;

public class TwoSumProblemIndex {

    //Given an array of integers nums and an integer target, return indices
    //of the two numbers such that they add up to target.

    public static void main(String[] args) {

        int[] nums = {3, 7, 11, 15};
        //int target = 14;
        int target = 18;
        //int target = 10;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                System.out.println(map.get(diff) + ", "+i);
                break;
            } else {
                map.put(nums[i], i);
            }
        }
    }
}
