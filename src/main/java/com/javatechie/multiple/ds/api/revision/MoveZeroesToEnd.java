package com.javatechie.multiple.ds.api.revision;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MoveZeroesToEnd {

    //Given an array nums, write a function to move all 0's to the end of it
    //while maintaining the relative order of the non-zero elements.
    //Input: nums = [0, 1, 0, 3, 12]
    //Output: [1, 3, 12, 0, 0]

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};

        List<Integer> list = new ArrayList<>(Arrays.stream(nums)
                .boxed()
                .filter(n -> n != 0)
                .toList());
        int frequency = Collections.frequency(Arrays.stream(nums).boxed().toList(), 0);
        List<Integer> list1 = Collections.nCopies(frequency, 0);
        list.addAll(list1);
//        int length = nums.length - list.size();
//        for (int i = 0; i < length; i++) {
//            list.add(0);
//        }
        System.out.println("list = " + list);

        //2nd way
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
        System.out.println("nums = " + Arrays.toString(nums));

        //3rd way
        int[] numbers = {0, 1, 0, 3, 12};
        CopyOnWriteArrayList<Integer> arrayList = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
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
        System.out.println("arrayList = " + arrayList);

        List<Integer> list2 = IntStream.concat(
                        IntStream.of(numbers).filter(n -> n != 0),
                        IntStream.of(numbers).filter(n -> n == 0)
                )
                .boxed()
                .toList();
        System.out.println("list2 = " + list2);
    }
}
