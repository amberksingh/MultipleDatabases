package com.javatechie.multiple.ds.api.again;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Anagram {

    public static void main(String[] args) {

        String s1 = "TeAcher";
        String s2 = "cheatER";

        //1
        char[] array1 = s1.toUpperCase().toCharArray();
        char[] array2 = s2.toUpperCase().toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2))
            System.out.println("Anagram");
        else
            System.out.println("NOT Anagram");

        //2
        String collect1 = Arrays.stream(s1.split(""))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining());
        String collect2 = Arrays.stream(s2.split(""))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining());
        if (collect1.equals(collect2))
            System.out.println("Anagram");
        else
            System.out.println("NOT Anagram");

        //

    }
}
