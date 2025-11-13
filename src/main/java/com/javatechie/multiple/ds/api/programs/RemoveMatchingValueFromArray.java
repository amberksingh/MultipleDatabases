package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RemoveMatchingValueFromArray {

    //Given an array nums and a value val, remove all instances of that
    //value in-place and return the new length of the array. Do not allocate extra
    //space for another array. You must modify the input array in-place with O(1)
    //extra memory.

    public static void main(String[] args) {
        int[] nums = {1, 5, 3, 6, 7, 4, 3}; //val to remove 3
        System.out.println("Initial nums array : " + Arrays.toString(nums));
        int val = 3;
        int[] arr = IntStream.of(nums)
                .filter(n -> n != val)
                .toArray();
        System.out.println("Arrays.toString(intStream) after modifying = " + Arrays.toString(arr));

        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        System.out.println("Length of modified array :" + pos);//5
        System.out.println("Modified array :" + Arrays.toString(nums));
    }
}
