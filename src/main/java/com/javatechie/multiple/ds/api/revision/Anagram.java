package com.javatechie.multiple.ds.api.revision;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Anagram {

    public static void main(String[] args) {

        String s1 = "TeAcher";
        String s2 = "cheatER";

        //1
        char[] arr = s1.toUpperCase().toCharArray();
        char[] array = s2.toUpperCase().toCharArray();
        Arrays.sort(arr);
        Arrays.sort(array);
        boolean equals = Arrays.equals(arr, array);
        if (equals)
            System.out.println("Is Anagram = " + equals);
        else
            System.out.println("NOT Anagram");

        //2
        boolean equals1 = Arrays.stream(s1.toUpperCase().split(""))
                .sorted()
                .collect(Collectors.joining())
                .equals(Arrays.stream(s2.toUpperCase().split(""))
                        .sorted()
                        .collect(Collectors.joining()));
        if (equals1)
            System.out.println("Is Anagram = " + equals1);
        else
            System.out.println("NOT Anagram");
    }
}
