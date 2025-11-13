package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JavaQuestions {

    public static void main(String[] args) {
        
        String str = "hello world";
        //o/p : dlrow olleh
        String ored = str.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1)
                .orElse("null");
        System.out.println("ored = " + ored);

        //reverse each word -> [olleh, dlrow]
        String collect = Arrays.stream(str.split("\\s"))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining(" "));
        System.out.println("collect = " + collect);

    }
}
