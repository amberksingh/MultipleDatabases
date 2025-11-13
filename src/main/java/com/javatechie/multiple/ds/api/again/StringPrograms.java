package com.javatechie.multiple.ds.api.again;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;

public class StringPrograms {

    public static void main(String[] args) {

        String str = "hello world";
        System.out.println("original string : " + str);

        //reverse
        //reduce
        //o/p: dlrow olleh
        String reverse = Arrays.stream(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .orElse("");
        System.out.println("reverse = " + reverse);

        //IntStream
        String rev = str.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1)
                .orElse("");
        System.out.println("rev = " + rev);

        System.out.println((char) Character.toUpperCase(97));//gives 'A'
        System.out.println(Character.toUpperCase(104));//gives 65
        System.out.println(Character.toUpperCase('a'));//gives 'A'

        //Reverse the order of the words in the string
        //str = "hello world"
        //op : world hello
        String reduce = Stream.of(str.split(" "))
                .reduce((w1, w2) -> w2 + " " + w1)
                .orElseThrow(() -> new RuntimeException("error"));
        System.out.println("reduce = " + reduce);

        //String str = "hello world";
        //Reverse the characters of each word in a given string while keeping the order of words intact
        //op: olleh dlrow
        String reverseIndWords = Stream.of(str.split(" "))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining(" "));
        System.out.println("reverseIndWords = " + reverseIndWords);


        //reversedWordsUsingCollectingAndThen
        String reversedWordsUsingCollectingAndThen = Stream.of(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    list.sort(Comparator.reverseOrder());
                                    return String.join(" ", list);
                                }
                        )
                );
        System.out.println("reversedWordsUsingCollectingAndThen = " + reversedWordsUsingCollectingAndThen);

        String replaceWord = "Banana is tasty, but some people prefer Banana pie.";
        String replaceUsingReplaceAll = replaceWord.replaceAll("Banana", "Apple");
        System.out.println("replaceUsingReplaceAll = " + replaceUsingReplaceAll);

        //above with streams
        String collect = Arrays.stream(replaceWord.split(" "))
                .map(word -> {
                    if (word.equalsIgnoreCase("Banana"))
                        word = "Apple";
                    return word;
                })
                .collect(Collectors.joining(" "));
        System.out.println("collect = " + collect);


        //Capitalize the first character of each word in the string
        String capFirstLetter = Arrays.stream(replaceWord.split(" "))
                .map(word -> {
                    char c = Character.toUpperCase(word.charAt(0));
                    return c + word.substring(1);
                })
                .collect(Collectors.joining(" "));
        System.out.println("capFirstLetter = " + capFirstLetter);

        //Remove all non-alphabetic characters from a string
        String alpha = "good$@1 +234 hgh_";
        String alphaNum = alpha.chars()
                .filter(c ->
                        ((Character.isAlphabetic(c)) || Character.isDigit(c))
                )
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
        System.out.println("alphaNum = " + alphaNum);

        //Extract all the unique characters from the string
        //String str = "hello world";
        List<String> list = Arrays.stream(str.split(""))
                .distinct()
                .toList();
        System.out.println("unique char list = " + list);

        //Filter out all the vowels from the string
        String vowelString = "hello world king eat hunt";
        String vowels = "aeiou";
        //string excluding vowels
        String collect1 = Stream.of(vowelString.split(""))
                .filter(s -> !vowels.toUpperCase().contains(s.toUpperCase()))
                .collect(Collectors.joining());
        System.out.println("string excluding vowels = " + collect1);

        //Find the first non-repeating character in the string
        //1st way
        String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        System.out.println("First non-repeating character using groupingBy : ");
        Stream.of(repeatingCharString.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,//maintains traversal order
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(System.out::println);

        //2nd way
        System.out.println("First non-repeating character using Collections.frequency : ");
        List<String> freqWordList = Stream.of(repeatingCharString.split("")).toList();
        freqWordList.stream()
                .filter(c -> Collections.frequency(freqWordList, c) == 1)
                .findFirst()
                .ifPresent(System.out::println);

        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order

        //🔑 Rule of thumb
        //
        //Type arguments for constructors: new HashMap<String, Long>()
        //
        //Type arguments for static methods: ClassName.<String, Long>methodName()
        Comparator<Map.Entry<String, Long>> entryComparator = comparingByValue();
//        Comparator<Map.Entry<String, Long>> entryComparator1 =
//                Map.Entry.<String, Long>comparingByValue().thenComparing(Map.Entry::getKey, Comparator.reverseOrder());

        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        Map<String, Long> collect2 = Stream.of(repeatingCharString.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                //LinkedHashMap::new,//maintains traversal order
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(entryComparator)
                //.sorted(entryComparator1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new//LinkedHashMap::new is needed even if you already sorted
                        // because a normal HashMap does not keep insertion order.
                ));
        System.out.println("collect2 = " + collect2);

        //Sort the words in the string alphabetically (ascending or descending)
        //Since .chars() gives an IntStream,
        //.sorted() simply sorts the integers numerically —
        //which corresponds to sorting characters by their Unicode code values.
        //✅ So effectively, it sorts characters based on their Unicode / ASCII numeric order.
        //String str = "aAbB1#";
        //gives #1ABab
        //# → 35
        //1 → 49
        //A → 65
        //B → 66
        //a → 97
        //b → 98
        List<String> list1 = repeatingCharString.chars()
                .sorted()
                .mapToObj(Character::toString)
                .toList();
        System.out.println("list1 = " + list1);

        //reverse
        String collect3 = repeatingCharString.chars()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(c -> String.valueOf((char) c.intValue()))
                //first convert to primitive from Integer/wrapper then to char
                .collect(Collectors.joining());
        System.out.println("collect3 = " + collect3);

        String toCollectionStuff = "the quick lazy fox jumps jumps the lazy dog";
        //find unique words/non-repeating
        TreeSet<String> collect4 = Arrays.stream(toCollectionStuff.split(" "))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("unique words = " + collect4);

        //Find the longest and shortest word in the string
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        Comparator<String> comp1 = Comparator.comparing(String::length);
        Comparator<String> comp2 = Comparator.comparing(String::length, Comparator.reverseOrder());
        String minLengthStr = Arrays.stream(repeatingCharString.split(" "))
                .min(comp1)
                .orElse("");
        System.out.println("minLengthStr = " + minLengthStr);
        String maxLengthStr = Arrays.stream(repeatingCharString.split(" "))
                .max(comp1)
                .orElse("");
        System.out.println("maxLengthStr = " + maxLengthStr);

        System.out.println("Print only the even-indexed characters in uppercase 1st way : ");
        IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                .map(repeatingCharString::charAt)
                .mapToObj(Character::toString)
                .map(String::toUpperCase)
                .forEach(System.out::print);

        //Check if a string is a palindrome
        System.out.println();
        String palindrome = "malayalam";
        boolean equals = Stream.of(palindrome.split(""))
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null)
                .equals(palindrome);
        if (equals)
            System.out.println("palindrome");
        else
            System.out.println("NOT palindrome");

        //2nd way
        boolean allMatch = IntStream.range(0, palindrome.length() / 2)
                .allMatch(i -> palindrome.charAt(i) == palindrome.charAt(palindrome.length() - 1 - i));
        if (allMatch)
            System.out.println("palindrome");
        else
            System.out.println("NOT palindrome");

        //Calculate the average length of each word in the string
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        double avg = Arrays.stream(repeatingCharString.split(" "))
                .mapToInt(String::length)
                .average()
                .getAsDouble();
        System.out.println("avg = " + avg);

        Double avgWord = Arrays.stream(repeatingCharString.split(" "))
                .collect(
                        Collectors.averagingInt(String::length)
                );
        System.out.println("avgWord = " + avgWord);

        Double avgWord1 = Arrays.stream(repeatingCharString.split(" "))
                .collect(
                        Collectors.averagingDouble(String::length)
                );
        System.out.println("avgWord = " + avgWord1);

        OptionalDouble avgWordLength = Stream.of(repeatingCharString.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                wLList -> wLList.stream().mapToInt(Integer::intValue).average()
                        )
                );
        System.out.println("avgWordLength = " + avgWordLength.getAsDouble());

        //Find all the substrings of a specific length (e.g., all 3-letter substrings)
        int k = 3;//substring length
        String subString = "abcdef";//length = 6
        List<String> list2 = IntStream.range(0, subString.length() - k + 1)//0,1,2,3
                .mapToObj(i -> subString.substring(i, k + i))
                .toList();
        System.out.println("list2 = " + list2);


    }
}
