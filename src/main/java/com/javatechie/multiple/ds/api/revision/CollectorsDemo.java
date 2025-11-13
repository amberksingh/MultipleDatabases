package com.javatechie.multiple.ds.api.revision;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

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

public class CollectorsDemo {

    public static void main(String[] args) {

        List<Country> countries = List.of(
                new Country(2, "China"),
                new Country(3, "India"),
                new Country(1, "Usa")
        );

        //make a map : countryName -> rank
        Map<String, Integer> collect = countries.stream()
                .collect(
                        Collectors.toMap(
                                Country::getName,
                                Country::getRank
                        )
                );
        System.out.println("collect = " + collect);

        //---joining()
        String collect1 = countries.stream()
                .map(Country::getName)
                .collect(
                        Collectors.joining("-")
                );
        System.out.println("countryNames = " + collect1);
        
        //join rank
        String rankjoin = countries.stream()
                .map(Country::getRank)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "@", "$"));
        System.out.println("rankjoin = " + rankjoin);

        //summarizingInt(), summarizingDouble(), summarizingLong()
        IntSummaryStatistics intSummaryStatistics = countries.stream()
                .collect(
                        Collectors.summarizingInt(Country::getRank)
                );
        System.out.println("intSummaryStatistics avg = " + intSummaryStatistics.getAverage());
        System.out.println("intSummaryStatistics sum = " + intSummaryStatistics.getSum());
        System.out.println("intSummaryStatistics min = " + intSummaryStatistics.getMin());
        System.out.println("intSummaryStatistics max = " + intSummaryStatistics.getMax());
        System.out.println("intSummaryStatistics count = " + intSummaryStatistics.getCount());

        IntSummaryStatistics intSummaryStatistics1 = countries.stream()
                .mapToInt(Country::getRank)
                .summaryStatistics();
        System.out.println("intSummaryStatistics1 avg = " + intSummaryStatistics1.getAverage());
        System.out.println("intSummaryStatistics1 sum = " + intSummaryStatistics1.getSum());
        System.out.println("intSummaryStatistics1 min = " + intSummaryStatistics1.getMin());
        System.out.println("intSummaryStatistics1 max = " + intSummaryStatistics1.getMax());
        System.out.println("intSummaryStatistics1 count = " + intSummaryStatistics1.getCount());

        DoubleSummaryStatistics doubleSummaryStatistics = countries.stream()
                .collect(
                        Collectors.summarizingDouble(Country::getRank)
                );
        System.out.println("doubleSummaryStatistics avg = " + doubleSummaryStatistics.getAverage());
        System.out.println("doubleSummaryStatistics count = " + doubleSummaryStatistics.getCount());
        System.out.println("doubleSummaryStatistics max = " + doubleSummaryStatistics.getMax());
        System.out.println("doubleSummaryStatistics min = " + doubleSummaryStatistics.getMin());
        System.out.println("doubleSummaryStatistics sum = " + doubleSummaryStatistics.getSum());

        DoubleSummaryStatistics doubleSummaryStatistics1 = countries.stream()
                .map(Country::getRank)
                .collect(
                        Collectors.summarizingDouble(x -> x)
                );
        System.out.println("doubleSummaryStatistics1 avg = " + doubleSummaryStatistics1.getAverage());
        System.out.println("doubleSummaryStatistics1 count = " + doubleSummaryStatistics1.getCount());
        System.out.println("doubleSummaryStatistics1 max = " + doubleSummaryStatistics1.getMax());
        System.out.println("doubleSummaryStatistics1 min = " + doubleSummaryStatistics1.getMin());
        System.out.println("doubleSummaryStatistics1 sum = " + doubleSummaryStatistics1.getSum());

        DoubleSummaryStatistics doubleSummaryStatistics2 = countries.stream()
                .map(Country::getRank)
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();
        System.out.println("doubleSummaryStatistics2 avg = " + doubleSummaryStatistics2.getAverage());
        System.out.println("doubleSummaryStatistics2 count = " + doubleSummaryStatistics2.getCount());
        System.out.println("doubleSummaryStatistics2 max = " + doubleSummaryStatistics2.getMax());
        System.out.println("doubleSummaryStatistics2 min = " + doubleSummaryStatistics2.getMin());
        System.out.println("doubleSummaryStatistics2 sum = " + doubleSummaryStatistics2.getSum());

        DoubleSummaryStatistics doubleSummaryStatistics3 = countries.stream()
                .map(Country::getRank)
                .map(Double::valueOf)
                .collect(
                        Collectors.summarizingDouble(x -> x)
                );
        System.out.println("doubleSummaryStatistics3 avg = " + doubleSummaryStatistics3.getAverage());
        System.out.println("doubleSummaryStatistics3 count = " + doubleSummaryStatistics3.getCount());
        System.out.println("doubleSummaryStatistics3 max = " + doubleSummaryStatistics3.getMax());
        System.out.println("doubleSummaryStatistics3 min = " + doubleSummaryStatistics3.getMin());
        System.out.println("doubleSummaryStatistics3 sum = " + doubleSummaryStatistics3.getSum());

        LongSummaryStatistics longSummaryStatistics = countries.stream()
                .map(Country::getRank)
                .mapToLong(Integer::longValue)
                .summaryStatistics();
        System.out.println("longSummaryStatistics avg = " + longSummaryStatistics.getAverage());
        System.out.println("longSummaryStatistics count = " + longSummaryStatistics.getCount());
        System.out.println("longSummaryStatistics max = " + longSummaryStatistics.getMax());
        System.out.println("longSummaryStatistics min = " + longSummaryStatistics.getMin());
        System.out.println("longSummaryStatistics sum = " + longSummaryStatistics.getSum());

        List<Country> countriesWC = List.of(
                new Country(2, "China", "Asia"),
                new Country(3, "India", "Asia"),
                new Country(1, "Usa", "America"),
                new Country(8, "Mexico", "America"),
                new Country(4, "Italy", "Europe")
        );

        //count of countries in each continent
        Map<String, Long> collect2 = countriesWC.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.counting()
                        )
                );
        System.out.println("count of countries in each continent = " + collect2);

        //countriesByContinentLengthSum
        ToIntFunction<Country> toIntFunction = (c) -> c.getName().length();
        Map<String, Integer> countriesByContinentLengthSum = countriesWC.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.summingInt(toIntFunction)
                        )
                );
        System.out.println("countriesByContinentLengthSum = " + countriesByContinentLengthSum);
        
        //mappingContinentWiseToNames
        Map<String, List<String>> collect3 = countriesWC.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.mapping(
                                        Country::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("collect3 = " + collect3);

    }
}
