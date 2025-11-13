package com.javatechie.multiple.ds.api.revision;

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
        String rev = Arrays.stream(str.split(""))
                .reduce((s1, s2) -> s2 + s1)
                .orElse(null);
        System.out.println("rev = " + rev);

        //rev using chars
        String revUsingChars = str.chars()
                .mapToObj(Character::toString)
                .reduce((s1, s2) -> s2 + s1)
                .orElse(null);
        System.out.println("revUsingChars = " + revUsingChars);

        //Count the number of occurrences of a specific character (e.g., 'l')
        List<String> strList = Arrays.stream(str.split("")).toList();
        int frequency = Collections.frequency(strList, "l");
        System.out.println("frequency of l = " + frequency);

        long l = Arrays.stream(str.split(""))
                .filter(s -> s.equalsIgnoreCase("l"))
                .count();
        System.out.println("frequency of l = " + l);

        //Reverse the order of the words in the string
        //str = "hello world"
        //op : world hello
        String revWords = Arrays.stream(str.split(" "))
                .reduce((s1, s2) -> s2 + " " + s1)
                .orElse(null);
        System.out.println("revWords = " + revWords);

        //2nd way for above
        String wordRev = Arrays.stream(str.split(" "))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    list.sort(Comparator.reverseOrder());
                                    //Collections.sort(list, Collections.reverseOrder());
                                    return String.join(" ", list);
                                }
                        )
                );
        System.out.println("wordRev = " + wordRev);

        //String str = "hello world";
        //Reverse the characters of each word in a given string while keeping the order of words intact
        //op: olleh dlrow
        String indWordsRev = Arrays.stream(str.split(" "))
                .map(StringBuffer::new)
                .map(StringBuffer::reverse)
                .map(StringBuffer::toString)
                .collect(Collectors.joining(" "));
        System.out.println("indWordsRev = " + indWordsRev);

        //Replace all occurrences of a specific word with another word "Banana" -> "Apple"
        String replaceWord = "Banana is tasty, but some people prefer Banana pie.";
        String replaceAll = replaceWord.replaceAll("Banana", "Apple");
        System.out.println("replaceAll = " + replaceAll);
        String replace = replaceWord.replace("Banana", "Apple");
        System.out.println("replace = " + replace);

        String[] words = replaceWord.split(" ");
        String revWordsMapWay = Arrays.stream(words)
                .map(s -> {
                    if (s.equalsIgnoreCase("Banana")) {
                        s = "Apple";
                    }
                    return s;
                })
                .collect(Collectors.joining(" "));
        System.out.println("revWordsMapWay = " + revWordsMapWay);

        //Capitalize the first character of each word in the string
        String capitalFirstChar = Arrays.stream(words)
                .map(s -> {
                    char c = s.charAt(0);
                    char upperCase = Character.toUpperCase(c);
                    return upperCase + s.substring(1);
                })
                .collect(Collectors.joining(" "));
        System.out.println("capitalFirstChar = " + capitalFirstChar);

        //Remove all non-alphabetic characters from a string
        //alpha and digit
        String alpha = "good1234 hgh_";
        String alphaDigit = Arrays.stream(alpha.split(""))
                .filter(c -> {
                    if (Character.isDigit(c.charAt(0)) || Character.isAlphabetic(c.charAt(0))) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.joining());
        System.out.println("alphaDigit = " + alphaDigit);

        String alphaDigit1 = alpha.chars()
                .filter(c -> {
                    if (Character.isDigit(c) || Character.isAlphabetic(c)) {
                        return true;
                    }
                    return false;
                })
                //.mapToObj(Character::toString)//String.valueOf((char) c) int->code-> string,
                //if we call String.valueOf(int), then it behaves like toString, no conversion of codes)
                .mapToObj(s -> String.valueOf((char) s))
                .collect(Collectors.joining());
        System.out.println("alphaDigit1 = " + alphaDigit1);

        //Extract all the unique characters from the string
        System.out.println(" unique characters from the string");
        str.chars()
                .distinct()
                .mapToObj(Character::toString)
                .forEach(s -> System.out.print(s + " "));

        //Filter out all the vowels from the string
        //String str = "hello world";
        //op : eo
        String vowels = "aeiou";
        String vowelStuff = Arrays.stream(str.split(""))
                .filter(vowels::contains)
                .collect(Collectors.joining());
        System.out.println("\nvowelStuff = " + vowelStuff);

        //Find the first non-repeating character in the string
        //1st way
        String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        String[] repeatSplit = repeatingCharString.split("");
        List<String> repeatSplitList = Arrays.stream(repeatSplit).toList();
        String nonRepeatChar = Arrays.stream(repeatSplit)
                .filter(c -> Collections.frequency(repeatSplitList, c) == 1)
                .findFirst()
                .orElse(null);
        System.out.println("nonRepeatChar = " + nonRepeatChar);

        //2nd way
        LinkedHashSet<String> collect = Arrays.stream(repeatSplit)
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
                .collect(Collectors.toCollection(LinkedHashSet::new));
        String ored = collect.stream().findFirst().orElse(null);
        System.out.println("nonRepeatChar = " + ored);

        //count the occurrences of each character and then sort these characters based on their counts
        // in ascending order

        //🔑 Rule of thumb
        //
        //Type arguments for constructors: new HashMap<String, Long>()
        //
        //Type arguments for static methods: ClassName.<String, Long>methodName()
        Comparator<Map.Entry<String, Long>> comparator = Map.Entry.<String, Long>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey());

        LinkedHashMap<String, Long> collect1 = Arrays.stream(repeatSplit)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(comparator)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> newVal,
                                LinkedHashMap::new
                        )
                );
        System.out.println("count the occurrences of each character = " + collect1);

        //Sort the words in the string alphabetically (ascending or descending)
        // String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        System.out.println("Words sorted in ascending order : ");
        String collect2 = Stream.of(repeatingCharString.split(" "))
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println("ascending order of words = " + collect2);

        String collect3 = Stream.of(repeatingCharString.split(" "))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(" "));
        System.out.println("descending order of words = " + collect3);

        //Find the longest and shortest word in the string
        Comparator<String> comparator1 = Comparator.comparingInt(String::length);
        Comparator<String> comparator2 = Comparator.comparing(String::length, Comparator.reverseOrder());

        String ored1 = Stream.of(repeatingCharString.split(" "))
                .min(comparator1)
                .orElse(null);
        String ored2 = Stream.of(repeatingCharString.split(" "))
                .max(comparator1)
                .orElse(null);

        String ored3 = Stream.of(repeatingCharString.split(" "))
                .max(comparator2)
                .orElse(null);
        String ored4 = Stream.of(repeatingCharString.split(" "))
                .min(comparator2)
                .orElse(null);
        System.out.println("shortest string = " + ored1);
        System.out.println("longest string = " + ored2);
        System.out.println("shortest string = " + ored3);
        System.out.println("longest string = " + ored4);

        //Print only the even-indexed characters in uppercase
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        String collect4 = IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(repeatingCharString::charAt)
                .map(Character::toUpperCase)
                .map(s -> Character.toString(s))
                .collect(Collectors.joining(","));
        System.out.println("collect4 = " + collect4);

        String collect5 = IntStream.range(0, repeatingCharString.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(c -> Character.toString(repeatingCharString.charAt(c)))
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));
        System.out.println("collect5 = " + collect5);

        //Check if a string is a palindrome
        String palindrome = "malayalbam";
        boolean flag = Arrays.stream(palindrome.split(""))
                .reduce((c1, c2) -> c2 + c1)
                .orElse(null)
                .equals(palindrome);
        System.out.println("is palindrome ? " + flag);

        //2nd way
        boolean allMatch = IntStream.range(0, palindrome.length() / 2)
                .allMatch(i -> palindrome.charAt(i) == palindrome.charAt(palindrome.length() - 1 - i));
        System.out.println("allMatch palindrome ? = " + allMatch);

        //Find all the substrings of a specific length (e.g., all 3-letter substrings)
        int k = 3;//substring length
        String subString = "abcdef";//length = 6
        List<String> substringList = IntStream.range(0, subString.length() - k + 1)
                .mapToObj(i -> subString.substring(i, k + i))//0,3  1,4  2,5  3,6
                .toList();
        System.out.println("substringList = " + substringList);
        
        //Map each character of "hello world" to its uppercase version
        //String str = "hello world";
        Map<String, String> collect6 = str.chars()
                .mapToObj(Character::toString)
                .filter(s -> !s.equalsIgnoreCase(" "))
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                String::toUpperCase,
                                (oldVal, newVal) -> oldVal
                        )
                );
        System.out.println("collect6 = " + collect6);

        List<String> list = Stream.of(str.split(""))
                .map(String::toUpperCase)
                .toList();
        System.out.println("list = " + list);

        //Calculate the average length of each word in the string
        //String repeatingCharString = "the quick brown fox jumps over the lazy dog";
        double v = Arrays.stream(repeatingCharString.split(" "))
                .map(String::length)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0D);
        System.out.println("avg word length = " + v);

        Double collect7 = Arrays.stream(repeatingCharString.split(" "))
                .map(String::length)
                .collect(
                        Collectors.averagingInt(x -> x)
                );
        System.out.println("avg word length = " + collect7);

        double v1 = Arrays.stream(repeatingCharString.split(" "))
                .map(String::length)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                bisht -> bisht.stream().mapToInt(Integer::intValue).average()
                        )
                ).orElse(0d);
        System.out.println("avg word length = " + v1);

    }
}
