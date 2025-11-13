package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonElementsBetweenArrays {

    public static void main(String[] args) {

        int arr1[] = {1, 2, 3, 4, 5, 6, 6};
        int arr2[] = {1, 2, 9, 0, 0, 6};

        Set<Integer> set1 = Arrays.stream(arr1)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> set2 = Arrays.stream(arr2)
                .boxed()
                .collect(Collectors.toSet());

        //1st way
        set2.retainAll(set1);
        System.out.println("Common elements : " + set2);

        //2nd way
        //Set<Integer> set = new HashSet<>();
        Set<Integer> commonElements = Arrays.stream(arr1)
                .boxed()
                .filter(e -> set2.contains(e))
                .collect(Collectors.toSet());
        System.out.println("commonElements = " + commonElements);


    }
}