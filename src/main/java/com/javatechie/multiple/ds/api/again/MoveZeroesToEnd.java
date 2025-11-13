package com.javatechie.multiple.ds.api.again;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MoveZeroesToEnd {

    //Given an array nums, write a function to move all 0's to the end of it
    //while maintaining the relative order of the non-zero elements.
    //Input:  nums = [0, 1, 0, 3, 12]
    //Output: [1, 3, 12, 0, 0]

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("nums original array = " + Arrays.toString(nums));

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

        System.out.println("Array after moving zeroes to end : " + Arrays.toString(nums));

        //2nd way streams
        int[] numbers = {0, 1, 0, 3, 12};
        long zeroCount = Arrays.stream(numbers)
                .filter(n -> n == 0)
                .count();
        List<Integer> list = Arrays.stream(numbers)
                .boxed()
                .filter(n -> n != 0)
                .collect(Collectors.toList());
        for (int i = 0; i < zeroCount; i++) {
            list.add(0);
        }
        Object[] array = list.toArray();
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        //concat
        int[] array1 = IntStream.concat(
                Arrays.stream(numbers).filter(n -> n != 0),
                Arrays.stream(numbers).filter(n -> n == 0)
        ).toArray();
        System.out.println("Arrays.toString(array1) concat = " + Arrays.toString(array1));

        //
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>(Arrays.stream(numbers).boxed().toList());
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 0) {
                arrayList.remove(next);
            }
        }
        while (arrayList.size() < numbers.length) {
            arrayList.add(0);
        }
        Object[] array2 = arrayList.toArray();
        System.out.println("array using CopyOnWriteArrayList : "+Arrays.toString(array2));
    }
}
