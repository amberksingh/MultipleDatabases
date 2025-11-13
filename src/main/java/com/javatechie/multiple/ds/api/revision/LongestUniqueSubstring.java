package com.javatechie.multiple.ds.api.revision;

import java.util.HashSet;

public class LongestUniqueSubstring {

    static int lengthOfLongestSubstring(String input) {

        HashSet<String> set = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;
        for (String str : input.split("")) {
            if (!set.contains(str)) {
                set.add(str);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                set.remove(str);
                left++;
            }

        }
        return maxLength;
    }

    public static void main(String[] args) {

        String input = "zabczdfz";
        //String input = "abcabcbb";
        int i = lengthOfLongestSubstring(input);
        System.out.println("largest unique substring length = " + i);

    }
}
