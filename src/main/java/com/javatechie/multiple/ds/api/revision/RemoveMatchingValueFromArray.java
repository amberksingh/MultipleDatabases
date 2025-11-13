package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;

public class RemoveMatchingValueFromArray {

    public static void main(String[] args) {
        //Given an array nums and a value val, remove all instances of that
        //value in-place and return the new length of the array. Do not allocate extra
        //space for another array. You must modify the input array in-place with O(1)
        //extra memory.

        int[] nums = {1, 5, 3, 6, 7, 4, 3}; //val to remove 3
        System.out.println("Initial nums array : " + Arrays.toString(nums));
        int val = 3;
        nums = Arrays.stream(nums)
                .filter(n -> n != val)
                .toArray();
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        System.out.println("length of new array after removing val = " + pos);

    }
}
