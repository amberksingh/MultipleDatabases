package com.javatechie.multiple.ds.api.again;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
class Country {

    int rank;
    String name;
    String continent;
    double gdp;
    float area;
    String primeMinister;
}

public class Streams7Stuff {

    public static void main(String[] args) {

        List<Country> countries = Arrays.asList(
                new Country(1, "United States", "North America", 27.0, 9834000f, "Joe Biden"),
                new Country(2, "China", "Asia", 17.8, 9597000f, "Li Qiang"),
                new Country(3, "Japan", "Asia", 4.3, 377975f, "Fumio Kishida"),
                new Country(4, "Germany", "Europe", 4.5, 357022f, "Olaf Scholz"),
                new Country(5, "India", "Asia", 3.9, 3287000f, "Narendra Modi"),
                new Country(6, "United Kingdom", "Europe", 3.5, 243610f, "Rishi Sunak"),
                new Country(7, "France", "Europe", 3.4, 551695f, "Gabriel Attal"),
                new Country(8, "Brazil", "South America", 2.7, 8516000f, "Luiz Inácio Lula da Silva")
        );

        //name to gdp mapping
        //toMap
        LinkedHashMap<String, Double> nameToGdp = countries.stream()
                .collect(
                        Collectors.toMap(
                                Country::getName,
                                Country::getGdp,
                                (gdpOld, gdpNew) -> gdpOld,//binary operator for merge scenario
                                LinkedHashMap::new//maintain order of traversing
                        )
                );
        BiConsumer<String, Double> biConsumer = (name, gdp) -> System.out.println(name + " -> " + gdp);
        System.out.println("Country names mapped to gdp : ");
        nameToGdp.forEach(biConsumer);

        //name to rank
        //toMap
        //TreeMap
        TreeMap<String, Integer> nameToRank = countries.stream()
                .collect(
                        Collectors.toMap(
                                Country::getName,
                                Country::getRank,
                                (rankOld, rankNew) -> rankOld,//binary operator for merge scenario
                                TreeMap::new//ascending order of keys
                        )
                );
        System.out.println("Country names mapped to rank : ");
        nameToRank.forEach((name, rank) -> System.out.println(name + " -> " + rank));

        //print country names separated by delimiter -, prefix # suffix @
        String names = countries.stream()
                .map(Country::getName)
                .collect(Collectors.joining("-", "#", "@"));
        System.out.println("names = " + names);

        //sum of gdps
        Double gdpSum = countries.stream()
                .collect(
                        Collectors.summingDouble(Country::getGdp)
                );
        System.out.println("gdpSum = " + gdpSum);

        //above using sum
        double sumGdp = countries.stream()
                .mapToDouble(Country::getGdp)
                .sum();
        System.out.println("sumGdp = " + sumGdp);

        //area sum
        //Country::getArea → returns a float (in your class)
        //
        //Java automatically promotes each float to a double (widening conversion)
        //
        //The resulting stream type is a DoubleStream
        //
        //sum() then returns a primitive double
        //
        //✅ Automatic float → double conversion happens here (no cast needed).
        double sumAreaFloat = countries.stream()
                .mapToDouble(Country::getArea)
                .sum();
        //3.2764302×107  = 3.2764302E7 = 32,764,302.0
        System.out.println("sumAreaFloat = " + sumAreaFloat);//sumAreaFloat = 3.2764302E7


        //Internally, summingDouble() calls the getter for each element.
        //
        //Whatever it returns (float, int, etc.) is converted to double automatically.
        //
        //The collector accumulates in double precision and returns a Double (wrapper).
        //
        //✅ Again — Java performs automatic widening if your getter returns a float.
        Double areaSum = countries.stream()
                .collect(
                        Collectors.summingDouble(Country::getArea)
                );
        System.out.println("areaSum = " + areaSum);

        //sort countries by prime minister ascending
        System.out.println("sorted countries by prime minister ascending : ");
        countries.stream()
                .sorted(Comparator.comparing(Country::getPrimeMinister))
                .forEach(System.out::println);

        //print countries with gdp > 4
        List<Country> countryList = countries.stream()
                .filter(country -> country.getGdp() > 4)
                .toList();
        System.out.println("countryList with gdp > 4 = " + countryList);

        //groupingBy
        System.out.println("countries name by continent : ");
        Map<String, List<String>> countriesByContinent = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.mapping(
                                        Country::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("countriesByContinent = " + countriesByContinent);

        //counting
        System.out.println("count of countries by continent : ");
        Map<String, Long> countriesCountByContinent = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.counting()
                        )
                );
        System.out.println("countriesCountByContinent = " + countriesCountByContinent);

        //summingInt
        System.out.println("countriesByContinentRankSum : ");
        ToIntFunction<Country> toIntFunction = Country::getRank;
        Map<String, Integer> countriesByContinentRankSum = countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                Collectors.summingInt(toIntFunction)
                        )
                );
        System.out.println("countriesByContinentRankSum = " + countriesByContinentRankSum);

        //partitioningBy area > 357021f
        Predicate<Country> countryPredicate = (country) -> country.getArea() > 357021f;
        Map<Boolean, List<Country>> collect = countries.stream()
                .collect(
                        Collectors.partitioningBy(
                                countryPredicate
                        )
                );
        System.out.println("partitioningBy area > 357021f = " + collect);


        //sort by country name length descending order
        IntFunction<String[]> intFunction = String[]::new;
        //IntFunction<String[]> intFunction1 = (i) -> new String[i];
        Function<Country, Integer> countryFunction = (c) -> c.getName().length();
        Comparator<Country> countryNameLengthDesc = Comparator.comparing(countryFunction, Comparator.reverseOrder());
        Comparator<Country> countryNameLengthDesc1 = Comparator.comparingInt((Country c) -> c.getName().length()).reversed();
        String[] array = countries.stream()
                .sorted(countryNameLengthDesc)
                //.sorted(countryNameLengthDesc1)
                .map(Country::getName)
                .toArray(intFunction);
        System.out.println("sort by country name length descending order = " + Arrays.toString(array));

        Supplier<TreeMap<String, Long>> treeMapSupplier = () -> new TreeMap<>();
        System.out.println("countries by continent mapped to count and keys/continent in sorted order/ascending : ");
        countries.stream()
                .collect(
                        Collectors.groupingBy(
                                Country::getContinent,
                                //treeMapSupplier,
                                TreeMap::new,
                                Collectors.counting()
                        )
                )
                .forEach((k, v) -> System.out.println(k + " -> " + v));

        Map<Boolean, List<String>> asiaNonAsiaNames = countries.stream()
                .collect(
                        Collectors.partitioningBy(
                                c -> c.getContinent().equalsIgnoreCase("asia"),
                                Collectors.mapping(
                                        Country::getName,
                                        Collectors.toList()
                                )
                        )
                );

        Map<String, List<String>> asiaNonAsiaNamesMap = new HashMap<>();
        asiaNonAsiaNamesMap.put("asia", asiaNonAsiaNames.get(true));
        asiaNonAsiaNamesMap.put("non-asia", asiaNonAsiaNames.get(false));
        System.out.println("asia/non-asia mapped to names = " + asiaNonAsiaNamesMap);


    }
}
