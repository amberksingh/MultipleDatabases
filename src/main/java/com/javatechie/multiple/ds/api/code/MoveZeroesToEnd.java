package com.javatechie.multiple.ds.api.code;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class MoveZeroesToEnd {

    //Given an array nums, write a function to move all 0's to the end of it
    //while maintaining the relative order of the non-zero elements.
    //Input:  nums = [0, 1, 0, 3, 12]
    //Output: [1, 3, 12, 0, 0]

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("nums original array = " + Arrays.toString(nums));

        //1-old skool
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        while (pos < nums.length) {
            nums[pos] = 0;
            pos++;
        }
        System.out.println("nums old skool way = " + Arrays.toString(nums));

        //2-Collections.nCopies()
        long count = Arrays.stream(nums)
                .boxed()
                .filter(n -> n == 0)
                .count();

        List<Integer> zeroList = Collections.nCopies((int) count, 0);
        List<Integer> nonZero = new java.util.ArrayList<>(Arrays.stream(nums)
                .boxed()
                .filter(n -> n != 0)
                .toList());
        nonZero.addAll(zeroList);
        System.out.println("Collections.nCopies() way = " + nonZero);

        //concat
        int[] nums1 = {0, 1, 0, 3, 12};
        List<Integer> concat = Stream.concat(
                Arrays.stream(nums1).boxed().filter(n -> n != 0),
                Arrays.stream(nums1).boxed().filter(n -> n == 0)
        ).toList();
        System.out.println("concat way = " + concat);

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(Arrays.stream(nums1).boxed().toList());
        Iterator<Integer> iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 0) {
                copyOnWriteArrayList.remove(next);
            }
        }
        while (copyOnWriteArrayList.size() < nums1.length) {
            copyOnWriteArrayList.add(0);
        }
        System.out.println("copyOnWriteArrayList way = " + copyOnWriteArrayList);
    }
}
