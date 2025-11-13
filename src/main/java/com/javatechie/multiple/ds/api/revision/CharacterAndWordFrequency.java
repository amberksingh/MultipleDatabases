package com.javatechie.multiple.ds.api.revision;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterAndWordFrequency {

    public static void main(String[] args) {

        String str = "hello guys world right west hello good country world west";
        //word frequency using old method of map
        String[] words = str.split(" ");
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        System.out.println("Word frequency : " + wordCount);

        //streams
        Map<String, Long> collect = Arrays.stream(words)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );
        System.out.println("Word frequency using streams: " + collect);

        //using toMap
        LinkedHashMap<String, Integer> collect1 = Arrays.stream(words)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                val -> 1,
                                Integer::sum,
                                LinkedHashMap::new
                        )
                );
        System.out.println("Word frequency using streams toMap: " + collect1);

        //Collections.frequency
        List<String> list = Arrays.stream(words).toList();
        Map<String, Integer> collect2 = Arrays.stream(words)
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                s -> Collections.frequency(list, s),
                                (oldVal, newVal) -> oldVal
                        )
                );
        System.out.println("Word frequency using streams Collections.frequency : " + collect2);
    }

}
