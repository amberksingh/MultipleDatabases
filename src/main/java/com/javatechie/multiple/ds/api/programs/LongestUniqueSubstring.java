package com.javatechie.multiple.ds.api.programs;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {

    static int lengthOfLongestSubstring(String input) {
        int left = 0, right = 0, maxLength = 0;
        Set<Character> set = new HashSet<>();

        while (right < input.length()) {
            char current = input.charAt(right);
            if (!set.contains(current)) {
                set.add(current);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                set.remove(input.charAt(left));
                left++;
            }

        }

        return maxLength;
    }

    public static void main(String[] args) {
        //String input = "abcabcbb";
        String input = "zabczdfz";
        int longest = lengthOfLongestSubstring(input);
        System.out.println("Longest unique substring length = " + longest);
        // Output: 3
    }
}
