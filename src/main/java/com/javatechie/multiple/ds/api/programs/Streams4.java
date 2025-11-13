package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//1. Integer.toString(int i)
//
//Signature
//
//public static String toString(int i)
//
//
//Return type → String.
//
//Behavior → Converts the primitive int to its decimal string form.
//
//Example
//
//String s1 = Integer.toString(123);
//System.out.println(s1); // "123"
//
//
//Specialty → Only works with int (or other overloads for different bases).
//
//2. String.valueOf(int i)
//
//Signature
//
//public static String valueOf(int i)
//
//
//Return type → String.
//
//Behavior → Also converts the primitive int into a string.
//
//Implementation → Internally calls Integer.toString(i).
//
//Example
//
//String s2 = String.valueOf(123);
//System.out.println(s2); // "123"
//
//3. Key differences
//Aspect	        Integer.toString(int)	                    String.valueOf(int)
//Input types	    Only int (or overloaded with radix)	    Works with many overloads: int, long, double, boolean, char, Object
//Null handling	    N/A (primitive only)	                For Object, returns "null" if argument is null
//Implementation	Direct int → string	                    Delegates to Integer.toString(int) for ints

//4. Example with null
//Integer x = null;
//
//// String.valueOf(Object o):
//String a = String.valueOf(x);
//System.out.println(a); // "null"
//
//// Integer.toString(int i):
//String b = Integer.toString(x); // ❌ NullPointerException (auto-unboxing null)
//
//5. Summary
//
//Both give the same result for int.
//
//Integer.toString(int) → specific to int → slightly more direct.
//
//String.valueOf(int) → more general, works for all primitive types and objects (safe for null objects).
//
//In real-world code, String.valueOf(...) is more common because it’s flexible and safe.

class Country {
    int rank;
    String name;

    String continent;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public Country(int rank, String name, String continent) {
        this.rank = rank;
        this.name = name;
        this.continent = continent;
    }
}

public class Streams4 {

    public static void main(String[] args) {


        Set<Integer> collect = IntStream.of(10, 20, 30, 11, 54, 23)
                .boxed()
                .collect(Collectors.toSet());
        System.out.println("collect = " + collect);

        List<String> fruits = List.of("apple", "banana", "cherry", "apple", "cherry", "mango");
        //Stream.of(fruits)//this gives Stream<List> not Stream<String>. use list.stream() for list
        //.map(x -> x.size())

        //map fruit name -> fruit name length
        //toMap
        //USE toMap() FOR THIS SITUATION IDEALLY
        Map<String, Integer> fruitNameToLength = fruits.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                String::length,
                                (oldVal, newVal) -> oldVal
                        )
                );

        System.out.println("Fruits mapped to fruit length using toMap() : ");
        fruitNameToLength.forEach(
                (k, v) -> System.out.println(k + " -> " + v)
        );

        //above with groupingBy
        Map<String, Integer> collect1 = fruits.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),//same string
                                Collectors.collectingAndThen(
                                        Collectors.mapping(
                                                String::length,
                                                Collectors.toList()
                                                //adding length of string to list inlcuding duplicates. removing below again
                                        ),
                                        list -> list.get(0)//pick old value if repeated
                                )
                        )
                );
        System.out.println("Fruits mapped to fruit length using groupingBy() : " + collect1);

        List<Country> countries = List.of(
                new Country(2, "China"),
                new Country(3, "India"),
                new Country(1, "Usa")
        );

        //make a map : countryName -> rank
        //toMap
        //USE toMap() FOR THIS SITUATION IDEALLY
        Map<String, Integer> countryNameToRankToMap = countries.stream()
                .collect(
                        Collectors.toMap(
                                Country::getName,
                                Country::getRank,
                                (oldVal, newVal) -> oldVal
                        )
                );
        System.out.println("countryNameToRank using toMap = " + countryNameToRankToMap);

        //using groupingBy
        Map<String, Integer> countryNameToRankGroupingBy = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getName,
                                Collectors.collectingAndThen(
                                        Collectors.mapping(
                                                Country::getRank,
                                                Collectors.toList()
                                        ),
                                        list -> list.get(0)
                                )
                        )
                );
        System.out.println("countryNameToRank using groupingBy = " + countryNameToRankGroupingBy);

        //Collectors.joining
        //country names concatenate with "-"
        String countryNamesConcatenate = countries.stream()
                .map(Country::getName)
                .collect(Collectors.joining());
        System.out.println("countryNamesConcatenate = " + countryNamesConcatenate);
        String countryNamesConcatenateDelim = countries.stream()
                .map(Country::getName)
                .collect(Collectors.joining("-"));
        System.out.println("countryNamesConcatenateDelim = " + countryNamesConcatenateDelim);
        String countryNamesConcatenatePrefixSuffix = countries.stream()
                .map(Country::getName)
                .collect(Collectors.joining("-", "@", "%"));
        System.out.println("countryNamesConcatenatePrefixSuffix = " + countryNamesConcatenatePrefixSuffix);
        
        //with Rank
        String rankConcatUpperCase = countries.stream()
                .map(Country::getRank)
                .map(String::valueOf)
                .map(String::toUpperCase)
                .collect(Collectors.joining("-"));
        System.out.println("rankConcatupperCase = " + rankConcatUpperCase);

        String rankConcatUpperCaseToString = countries.stream()
                .map(Country::getRank)
                .map(x -> Integer.toString(x))
                .map(String::toUpperCase)
                .collect(Collectors.joining("-"));
        System.out.println("rankConcatUpperCaseToString = " + rankConcatUpperCaseToString);

        //count
        long count = countries.stream()
                .count();
        System.out.println("country count = " + count);

        Long collect2 = countries.stream()
                .collect(
                        Collectors.counting()
                );
        System.out.println("country count = " + collect2);

        //summarizingInt(), summarizingDouble(), summarizingLong()
        IntSummaryStatistics intSummaryStatistics = countries.stream()
                .collect(
                        Collectors.summarizingInt(Country::getRank)
                );
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        //Use Collectors.summarizingDouble(...) on a generic Stream<T>.
        //
        //Use .summaryStatistics() on a primitive stream like DoubleStream, IntStream, or LongStream.
        DoubleSummaryStatistics summarizingDouble = countries.stream()
                .map(Country::getRank)
                .collect(
                        Collectors.summarizingDouble(x -> x)//works on Stream<T> i.e object not primitive DoubleStream
                );
        System.out.println("summarizingDouble = " + summarizingDouble);

        DoubleSummaryStatistics summarizingDouble1 = countries.stream()
                .map(Country::getRank)
                .map(Integer::doubleValue)
                //convert to double
                // Autoboxing then kicks in: the primitive double return is boxed into a Double object to fit the R in Stream<R>.
                //
                //👉 So the result is Stream<Double>, not a DoubleStream.
                .collect(Collectors.summarizingDouble(x -> x));
        System.out.println("summarizingDouble1 = " + summarizingDouble1);

        DoubleSummaryStatistics doubleSummaryStatistics = countries.stream()
                .map(Country::getRank)
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();//works with primitive DoubleStream. gives same data as above summarizingDouble()
        System.out.println("doubleSummaryStatistics = " + doubleSummaryStatistics);

        DoubleSummaryStatistics doubleSummaryStatistics1 = countries.stream()
                .map(Country::getRank)
                .map(Double::valueOf)
                .collect(
                        Collectors.summarizingDouble(x -> x)
                );
        System.out.println("doubleSummaryStatistics1 = " + doubleSummaryStatistics1);

        DoubleSummaryStatistics doubleSummaryStatistics2 = countries.stream()
                .map(Country::getRank)
                .mapToDouble(Double::valueOf)
                .boxed()
                .collect(
                        Collectors.summarizingDouble(x -> x)
                );
        System.out.println("doubleSummaryStatistics2 = " + doubleSummaryStatistics2);

        LongSummaryStatistics longSummaryStatistics1 = countries.stream()
                .map(Country::getRank)
                .collect(
                        Collectors.summarizingLong(x -> x)
                );
        System.out.println("longSummaryStatistics1 = " + longSummaryStatistics1);

        LongSummaryStatistics longSummaryStatistics = countries.stream()
                .map(Country::getRank)
                .mapToLong(Integer::longValue)//makes primitive LongStream
                .summaryStatistics();
        System.out.println("longSummaryStatistics = " + longSummaryStatistics);

        LongSummaryStatistics longSummaryStatistics3 = countries.stream()
                .map(Country::getRank)
                .map(Integer::longValue)
                .collect(
                        Collectors.summarizingLong(x -> x)
                );
        System.out.println("longSummaryStatistics3 = " + longSummaryStatistics3);

        LongSummaryStatistics longSummaryStatistics2 = countries.stream()
                .map(Country::getRank)
                .map(Long::valueOf)
                .collect(
                        Collectors.summarizingLong(x -> x)
                );
        System.out.println("longSummaryStatistics2 = " + longSummaryStatistics2);


    }
}
