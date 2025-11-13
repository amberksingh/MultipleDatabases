package com.javatechie.multiple.ds.api.again;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GoodBadString {

    //Given a string s, return true if s is a "good" string, or false otherwise.
    //A string s is good if all characters that appear in s have the same number of
    //occurrences (i.e., the same frequency).
    public static void main(String[] args) {

        String str = "hheelloo";
        String str1 = "hhelloo";
        String str2 = "hannah";
        String str3 = "night";
        Set<Long> frequencies = new HashSet<>(Arrays.stream(str3.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .values());
        if (frequencies.size() == 1)
            System.out.println("Good String");
        else
            System.out.println("Bad String");

    }
}
