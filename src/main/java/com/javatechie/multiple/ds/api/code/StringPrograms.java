package com.javatechie.multiple.ds.api.code;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringPrograms {

    public static void main(String[] args) {

        String str = "hello world";
        System.out.println("original string : " + str);

        //reverse
        //o/p: dlrow olleh
        String reverse = Stream.of(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .orElse("null");
        System.out.println("reverse = " + reverse);

        //revStrUsingIntStream
        String revStrUsingIntStream = str.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1)
                .orElse("null");
        System.out.println("revStrUsingIntStream = " + revStrUsingIntStream);

        System.out.println((char) Character.toUpperCase(97));//gives 'A'
        System.out.println(Character.toUpperCase(104));//gives 65

        //Reverse the order of the words in the string
        //str = "hello world"
        //op : world hello
        String reversedWords = Arrays.stream(str.split(" "))
                .reduce((s1, s2) -> s2 + " " + s1)
                .orElse("null");
        System.out.println("reversedWords : " + reversedWords);

        //reversedWordsUsingCollectingAndThen
        String reversedWordsUsingCollectingAndThen = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    //list.sort(Comparator.reverseOrder());
                                    Collections.reverse(list);
                                    return String.join(" ", list);
                                }
                        )
                );
        System.out.println("reversedWordsUsingCollectingAndThen = " + reversedWordsUsingCollectingAndThen);

        String replaceWord = "Banana is tasty, but some people prefer Banana pie.";
        String replaceUsingReplaceAll = replaceWord.replaceAll("Banana", "Apple");
        System.out.println("replaceUsingReplaceAll = " + replaceUsingReplaceAll);
        //Capitalize the first character of each word in the string
        String firstCharCaps = Stream.of(replaceWord.split(" "))
                .map(s -> {
                    char c = s.charAt(0);
                    char upperCase = Character.toUpperCase(c);
                    return upperCase + s.substring(1);
                    //return s.replace(c, upperCase); //replaces all occurences not just first char
                })
                .collect(Collectors.joining(" "));
        System.out.println("firstCharCaps = " + firstCharCaps);

        //Remove all non-alphabetic characters from a string
        String alpha = "good1234 hgh_";
        //op : good1234hgh
        String alpha1 = alpha.chars()
//                .mapToObj(s -> {
//                    if (Character.isAlphabetic(s) || Character.isDigit(s))
//                        return Character.toString(s);
//                    return "";
//                })
                .mapToObj(s -> {
                    if (Character.isAlphabetic(s) || Character.isDigit(s))
                        return String.valueOf((char) s);//String.valueOf((char) c) int->code-> string,
                    // if we call String.valueOf(int), then it behaves like toString, no conversion of codes)
                    return "";
                })
                .collect(Collectors.joining());
        System.out.println("alpha1 = " + alpha1);

        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order
        Comparator<Map.Entry<String, Long>> entryComparator = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry::getKey);
        String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        LinkedHashMap<String, Long> collect = Stream.of(repeatingCharString.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()

                        )
                )
                .entrySet()
                .stream()
                .sorted(entryComparator)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> newVal,
                                LinkedHashMap::new
                        )
                );
        System.out.println("collect = " + collect);

        //Find the longest and shortest word in the string
        Comparator<String> longest = Comparator.comparing(String::length);
        Comparator<String> shortest = Comparator.comparing(String::length, Comparator.reverseOrder());

        String shortestStr = Stream.of(repeatingCharString.split(" "))
                .min(longest)
                .orElse("null");
        System.out.println("shortestStr = " + shortestStr);

        String longestStr = Stream.of(repeatingCharString.split(" "))
                .min(shortest)
                .orElse("null");
        System.out.println("longestStr = " + longestStr);

        //Print only the even-indexed characters in uppercase
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        String evenCharUpperCase = IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(j -> Character.toString(repeatingCharString.charAt(j)))
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        System.out.println("evenCharUpperCase = " + evenCharUpperCase);

        System.out.println("Print only the even-indexed characters in uppercase 2nd way : ");
        String evenCharUpperCase1 = IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                .map(repeatingCharString::charAt)// returns char, but promoted to int because map() gives IntStream still
                .map(Character::toUpperCase)
                .mapToObj(Character::toString)//convert to Stream<String> that's why we need mapToObj here
                .collect(Collectors.joining(","));
        System.out.println("evenCharUpperCase1 = " + evenCharUpperCase1);

        //Check if a string is a palindrome
        String palindrome = "level";
        boolean palindromeOrNot = palindrome.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1)
                .orElse("null")
                .equalsIgnoreCase(palindrome);
        System.out.println("palindromeOrNot = " + palindromeOrNot);

        //2nd way
        boolean allMatchPalindromeOrNot = IntStream.range(0, palindrome.length() / 2)
                .allMatch(i -> palindrome.charAt(i) == palindrome.charAt(palindrome.length() - 1 - i));
        System.out.println("allMatchPalindromeOrNot = " + allMatchPalindromeOrNot);

        //Find all the substrings of a specific length (e.g., all 3-letter substrings)
        int k = 3;//substring length
        String subString = "abcdef";//length = 6
        List<String> list = IntStream.range(0, subString.length() - k + 1)//(0,4) -> 0,1,2,3
                .mapToObj(i -> {
                    String substring = subString.substring(i, k + i);//0,2   1,3   2,4,  3,5
                    return substring;
                })
                .toList();
        System.out.println("subStrings list = " + list);

        //Calculate the average length of each word in the string
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        double avgWordLength = Arrays.stream(repeatingCharString.split(" "))
                .collect(
                        Collectors.averagingInt(String::length)
                );
        System.out.println("avgWordLength = " + avgWordLength);

        double asDouble = Arrays.stream(repeatingCharString.split(" "))
                .mapToInt(String::length)
                .average()
                .getAsDouble();
        System.out.println("avgWordLength = " + asDouble);

        OptionalDouble collect1 = Stream.of(repeatingCharString.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                wordList -> wordList.stream().mapToInt(Integer::intValue).average()
                        )
                );
        if (collect1.isPresent())
            System.out.println("Avg word length : "+collect1.getAsDouble());
        else
            System.out.println("Not found : ");

    }
}
