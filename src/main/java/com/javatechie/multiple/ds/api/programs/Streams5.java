package com.javatechie.multiple.ds.api.programs;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Streams5 {

    public static void main(String[] args) {

        //groupingBy(Function<? super T, ? extends K> classifier)
        //Groups elements by a classifier function.
        List<Country> countries = List.of(
                new Country(2, "China", "Asia"),
                new Country(3, "India", "Asia"),
                new Country(1, "Usa", "America"),
                new Country(8, "Mexico", "America"),
                new Country(4, "Italy", "Europe")
        );

        //groupingBy accepts Function
        //continent wise country(full object) mapping
        Map<String, List<Country>> continentWiseCountry = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent
                        )
                );
        System.out.println("continent wise countries object : ");
        continentWiseCountry
                .entrySet()
                .stream()
                .forEach((k) -> System.out.println(k.getKey() + " -> " + k.getValue()));

        //count of countries in each continent
        Map<String, Long> continentWiseCount = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.counting()
                        )
                );
        System.out.println("continentWiseCount = " + continentWiseCount);

        //rank sum continent wise
        Map<String, Integer> rankSumContinentWise = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.summingInt(Country::getRank)
                        )
                );
        System.out.println("rankSumContinentWise = " + rankSumContinentWise);

        //countryName length continent wise
        Map<String, Integer> countryNameLengthContinentWise = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.summingInt(c -> c.getName().length())
                        )
                );
        System.out.println("countryNameLengthContinentWise = " + countryNameLengthContinentWise);

        //sorting by continent name
        System.out.println("countriesNameLengthSum continent wise sorted continent wise in natural order : ");
        countryNameLengthContinentWise.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //countryName list continent wise
        System.out.println("countries names continent wise sorted continent wise in reverse order : ");
        countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.mapping(
                                        Country::getName,
                                        Collectors.toList()
                                )
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //sorted key using Treemap
        TreeMap<String, Long> countryCountContinentWiseTreemap = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                TreeMap::new,
                                Collectors.counting()
                        )
                );
        System.out.println("countryCountContinentWiseTreemap = " + countryCountContinentWiseTreemap);

        //partitioningBy
        Map<Boolean, List<String>> asia = countries.stream()
                .collect(
                        Collectors.partitioningBy(
                                country -> country.getContinent().equalsIgnoreCase("asia"),
                                Collectors.mapping(
                                        Country::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("countries in asia : " + asia.get(true));
        System.out.println("countries in non asia : " + asia.get(false));

        //rank sum asia/non-asia
        Map<Boolean, Integer> asia1 = countries.stream()
                .collect(
                        Collectors.partitioningBy(
                                country -> country.getContinent().equalsIgnoreCase("asia"),
                                Collectors.summingInt(Country::getRank)
                        )
                );
        System.out.println("countries rank sum in asia : " + asia1.get(true));
        System.out.println("countries rank sum in non asia : " + asia1.get(false));

    }
}
