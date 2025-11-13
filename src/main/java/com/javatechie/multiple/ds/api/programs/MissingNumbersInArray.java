package com.javatechie.multiple.ds.api.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingNumbersInArray {
    //Given an array nums of n integers where nums[i] is in the range [1, n],
    //return an array of all the integers in the range [1, n] that do not appear in
    //nums.


    public static void main(String[] args) {
//        Input: nums = [4, 3, 2, 7, 8, 2, 3, 1]
//        n = 8
//        Output: [5, 6]
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                list.add(i + 1);
        }
        System.out.println("missing values = " + list);

        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}
