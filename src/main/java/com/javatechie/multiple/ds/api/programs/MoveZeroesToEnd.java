package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class MoveZeroesToEnd {

    //Given an array nums, write a function to move all 0's to the end of it
    //while maintaining the relative order of the non-zero elements.
    //Input:  nums = [0, 1, 0, 3, 12]
    //Output: [1, 3, 12, 0, 0]

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};

        //Collections.nCopies(n, T)
        List<Integer> list1 = new ArrayList<>(Arrays.stream(nums)
                .boxed()
                .filter(e -> e != 0)
                .toList());
        long count = Arrays.stream(nums)
                .filter(e -> e == 0)
                .count();
        List<Integer> list = Collections.nCopies((int) count, 0);
        list1.addAll(list);
        System.out.println("1st way list1 = " + list1);

        //
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>(list1);
        Iterator<Integer> iterator = arrayList.iterator();
        int zeroCount = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 0) {
                arrayList.remove(next);
                zeroCount++;
            }
        }
        for (int i = 0; i < zeroCount; i++) {
            arrayList.add(0);
        }
        System.out.println("2nd way arrayList = " + arrayList);

        //
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
        System.out.println("3rd way old skool = " + Arrays.toString(nums));

        //concat
        //IntFunction<Integer[]> function = (i) -> new Integer[i];
        IntFunction<Integer[]> function = Integer[]::new;
        Integer[] array = Stream.concat(Arrays.stream(nums).boxed().filter(e -> e != 0),
                        Arrays.stream(nums).boxed().filter(e -> e == 0))
                //.toArray(Integer[]::new);
                .toArray(function);
        System.out.println("4yh way concat = " + Arrays.toString(array));

    }
}
