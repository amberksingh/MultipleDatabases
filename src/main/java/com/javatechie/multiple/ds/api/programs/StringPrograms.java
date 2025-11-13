package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringPrograms {

    public static void main(String[] args) {

        String str = "hello world";
        System.out.println("original string : " + str);
        //reverse
        String reversedStr = Stream.of(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .orElse("null");
        System.out.println("reversedStr = " + reversedStr);//dlrow olleh

        //
        String revStrUsingIntStream = str.chars()//gives IntStream unicode values
                .mapToObj(Character::toString)//So, 104 → 'h' → "h", and so on.
                .reduce((s1, s2) -> s2 + s1)
                .orElse("null");
        System.out.println("revStrUsingIntStream = " + revStrUsingIntStream);//dlrow olleh

        //Count the number of occurrences of a specific character (e.g., 'l')
        long l = Arrays.stream(str.split(""))
                .filter(c -> c.equalsIgnoreCase("l"))
                .count();
        System.out.println("count of \"l\" = " + l);

        //Reverse the order of the words in the string
        //str = "hello world"
        //op : world hello
        String reversedWords = Arrays.stream(str.split(" "))
                .reduce((s1, s2) -> s2 + " " + s1)
                .orElse("null");
        System.out.println("reversedWords = " + reversedWords);

        String reversedWordsUsingCollectingAndThen = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    //Collections.reverse(list);
                                    list.sort(Comparator.reverseOrder());
                                    return String.join(" ", list);
                                }
                        )
                );

        System.out.println("reversedWordsUsingCollectingAndThen = " + reversedWordsUsingCollectingAndThen);

        //String str = "hello world";
        //Reverse the characters of each word in a given string while keeping the order of words intact
        //op: olleh dlrow
        String reversedEachWordNotFullString = Stream.of(str.split(" "))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining(" "));
        System.out.println("reversedEachWordNotFullString = " + reversedEachWordNotFullString);

        //Replace all occurrences of a specific word with another word "Banana" -> "Apple"
        String replaceWord = "Banana is tasty, but some people prefer Banana pie.";
        String replaceUsingReplaceAll = replaceWord.replaceAll("Banana", "Apple");
        System.out.println("replaceUsingReplaceAll = " + replaceUsingReplaceAll);

        String replaceStream = Stream.of(replaceWord.split(" "))
                .map(s -> {
                            if (s.equalsIgnoreCase("Banana"))
                                s = "Apple";
                            return s;
                        }
                ).collect(Collectors.joining(" "));
        System.out.println("replaceStream = " + replaceStream);

        //Capitalize the first character of each word in the string
        String firstLetterCapital = Stream.of(replaceWord.split(" "))
                .map(s -> {
                    char c = s.charAt(0);
                    char upperCase = Character.toUpperCase(c);
                    return upperCase + s.substring(1);
                })
                .collect(Collectors.joining(" "));
        System.out.println("firstLetterCapital = " + firstLetterCapital);

        //Remove all non-alphabetic characters from a string
        //alpha and digit
        String alpha = "good1234 hgh_";
        String removeNonAlphabetic = alpha.chars()
                .filter(
                        c -> Character.isDigit(c) || Character.isAlphabetic(c)
                )
                .mapToObj(Character::toString)
                //.mapToObj(c -> String.valueOf((char) c))//String.valueOf((char) c) int->code-> string,
                // if we call String.valueOf(int), then it behaves like toString, no conversion of codes)
                .collect(Collectors.joining());
        System.out.println("removeNonAlphabetic = " + removeNonAlphabetic);

        //Extract all the unique characters from the string
        List<String> uniqueCharacters = str.chars()
                .distinct()
                .mapToObj(Character::toString)
                //.mapToObj(s -> String.valueOf((char) s))
                .toList();
        System.out.println("unique characters list = " + uniqueCharacters);

        //Filter out all the vowels from the string
        //String str = "hello world";
        //op : eo
        String vowels = "aeiou";
        String vowelsContaining = Stream.of(str.toLowerCase().split(""))
                .filter(c -> !vowels.contains(c))
                .collect(Collectors.joining());
        System.out.println("vowelsContaining = " + vowelsContaining);

        //Find the first non-repeating character in the string
        //1st way
        String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        List<String> repeatingCharList = Stream.of(repeatingCharString.split("")).toList();
        LinkedHashSet<String> nonRepeatingChars = Stream.of(repeatingCharString.split(""))
                .filter(s -> Collections.frequency(repeatingCharList, s) == 1)
                .collect(
                        Collectors.toCollection(LinkedHashSet::new)//supplier
                );//IMPORTANT
        String firstNonRepeatingChar = nonRepeatingChars.stream()
                .findFirst()
                .orElse("null");
        System.out.println("firstNonRepeatingChar = " + firstNonRepeatingChar);

        //2nd way
        String firstNonRepeatingChar1 = Stream.of(repeatingCharString.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse("null");
        System.out.println("firstNonRepeatingChar1 = " + firstNonRepeatingChar1);

        //Find all the non-repeating characters in the string
        String nonRepeatingCharacters = Stream.of(repeatingCharString.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        System.out.println("nonRepeatingCharacters = " + nonRepeatingCharacters);

        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order

        //🔑 Rule of thumb
        //
        //Type arguments for constructors: new HashMap<String, Long>()
        //
        //Type arguments for static methods: ClassName.<String, Long>methodName()

        Comparator<Map.Entry<String, Long>> comparator = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey());

        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        LinkedHashMap<String, Long> charFrequencyAscOrder = Stream.of(repeatingCharString.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,//to preserve traversal order
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                //.sorted(Map.Entry.comparingByValue())
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new//LinkedHashMap::new is needed even if you already sorted
                        // because a normal HashMap does not keep insertion order.

                ));
        System.out.println("charFrequencyAscOrder = " + charFrequencyAscOrder);

        IntFunction<String[]> intFunction = (i) -> new String[i];
        String[] wordArray = Arrays.stream(repeatingCharString.split("\\s"))
                .toArray(String[]::new);
        System.out.println("wordArray = " + Arrays.toString(wordArray));

        //Sort the words in the string alphabetically (ascending or descending)
        System.out.println("Words sorted in ascending order : ");
        Arrays.stream(repeatingCharString.split("\\s"))
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println("\nWords sorted in descending order : ");
        Arrays.stream(repeatingCharString.split("\\s"))
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.print(s + " "));

        //Find the longest and shortest word in the string
        Comparator<String> wordLengthComp = Comparator.comparing(String::length);
        Comparator<String> wordLengthCompDesc = Comparator.comparing(String::length, Comparator.reverseOrder());
        String shortestWord = Arrays.stream(repeatingCharString.split("\\s"))
                .min(wordLengthComp)
                .orElse("null");
        System.out.println("\nshortestWord = " + shortestWord);

        String longestWord = Arrays.stream(repeatingCharString.split("\\s"))
                .min(wordLengthCompDesc)
                .orElse("null");
        System.out.println("longestWord = " + longestWord);

        //Print only the even-indexed characters in uppercase
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        System.out.println("Print only the even-indexed characters in uppercase 1st way : ");
        String evenCharUpperCase = IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(j -> Character.toString(repeatingCharString.charAt(j)))
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        System.out.println("evenCharUpperCase = " + evenCharUpperCase);

        System.out.println("Print only the even-indexed characters in uppercase 2nd way : ");
        String evenCharUpperCase1 = IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                //.map(i -> repeatingCharString.charAt(i))
                .map(repeatingCharString::charAt)// returns char, but promoted to int because map() gives IntStream still
                .map(Character::toUpperCase)
                .mapToObj(Character::toString)//convert to Stream<String> that's why we need mapToObj here
                .collect(Collectors.joining(","));
        System.out.println("evenCharUpperCase = " + evenCharUpperCase1);

        //Check if a string is a palindrome
        String palindrome = "malayalam";
        boolean isPalindrome = Stream.of(palindrome.split(""))
                .reduce(
                        (s1, s2) -> s2 + s1
                ).orElse("null")
                .equalsIgnoreCase(palindrome);
        System.out.println("isPalindrome reduce approach = " + isPalindrome);

        //2nd way
        boolean allMatch = IntStream.of(0, palindrome.length() / 2)
                .allMatch(i -> palindrome.charAt(i) == palindrome.charAt(palindrome.length() - 1 - i));
        System.out.println("isPalindrome allMatch approach = " + allMatch);

        //Find all the substrings of a specific length (e.g., all 3-letter substrings)
        int k = 3;//substring length
        String subString = "abcdef";//length = 6
        List<String> subStringsList = IntStream.range(0, subString.length() - k + 1)
                .mapToObj(i -> subString.substring(i, k + i))
                .toList();
        System.out.println("subStringsList = " + subStringsList);

        //Map each character of "hello world" to its uppercase version
        //String str = "hello world";
        Map<String, String> mappedToUpperCase = str.chars()
                .mapToObj(Character::toString)
                .filter(s -> !s.equalsIgnoreCase(" "))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                String::toUpperCase,
                                (oldVal, newVal) -> oldVal
                        )
                );
        System.out.println("mappedToUpperCase = " + mappedToUpperCase);

        //Calculate the average length of each word in the string
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        double avgWordLength = Arrays.stream(repeatingCharString.split("\\s"))
                //.map(String::length)
                .mapToInt(String::length)
                .average()
                .orElse(0d);
        System.out.println("avgWordLength = " + avgWordLength);

        Double avgWordLengthAveragingInt = Stream.of(repeatingCharString.split(" "))
                .collect(
                        Collectors.averagingInt(String::length)
                );
        System.out.println("average word length = " + avgWordLengthAveragingInt);

        Double collect8 = Stream.of(repeatingCharString.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                primitive -> primitive.stream().mapToInt(Integer::intValue).average()
                                //mapToInt convert Stream<Integer> to IntStream, similar for double and long
                        )
                ).getAsDouble();
        System.out.println("average word length = " + collect8);


    }
}
