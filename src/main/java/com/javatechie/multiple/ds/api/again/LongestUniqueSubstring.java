package com.javatechie.multiple.ds.api.again;

import java.util.HashSet;
import java.util.Map;

public class LongestUniqueSubstring {

    static int subString(String str) {

        HashSet<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0, right = 0;
        while (right < str.length()) {
            char c = str.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                set.remove(str.charAt(left));
                left++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {

        //String input = "abcabcbb";
        String input = "zabczdfz";
        int subStringLength = subString(input);
        System.out.println("subStringLength = " + subStringLength);
    }
}
