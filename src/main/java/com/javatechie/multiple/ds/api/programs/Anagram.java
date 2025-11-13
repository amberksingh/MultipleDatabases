package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagram {

    public static void main(String[] args) {

        String s1 = "TeAcher";
        String s2 = "cheatER";

        //method 1
        char[] array1 = s1.toUpperCase().toCharArray();
        char[] array2 = s2.toUpperCase().toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2))
            System.out.println("Anagram");
        else
            System.out.println("Not Anagram");


        //method 2
        String collect1 = Stream.of(s1.toUpperCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        String collect2 = Stream.of(s2.toUpperCase().split(""))
                .sorted()
                .collect(Collectors.joining());
        if (collect1.equals(collect2))
            System.out.println("Anagram");
        else
            System.out.println("Not Anagram");

    }
}
