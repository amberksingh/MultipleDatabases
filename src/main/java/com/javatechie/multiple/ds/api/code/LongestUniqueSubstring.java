package com.javatechie.multiple.ds.api.code;

import java.util.HashSet;

public class LongestUniqueSubstring {

    static int lengthOfLongestSubstring(String input) {

        HashSet<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;
        while (right < input.length()) {
            char c = input.charAt(right);
            if (!set.contains(c)) {
                set.add(c);//1
                maxLength = Math.max(maxLength, right - left + 1);//2
                right++;
            } else {
                set.remove(input.charAt(left));
                left++;//
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String input1 = "abcabcbb";
        String input = "zabczdfz";
        int longest = lengthOfLongestSubstring(input1);
        System.out.println("Longest unique substring length = " + longest);
    }
}
