package com.javatechie.multiple.ds.api.programs;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Hero {
    String name;
    double salary;
    int rank;
    String gender;

    public Hero(String name, double salary, int rank, String gender) {
        this.name = name;
        this.salary = salary;
        this.rank = rank;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getRank() {
        return rank;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", rank=" + rank +
                ", gender='" + gender + '\'' +
                '}';
    }
}

public class CustomClassDemo {

    public static void main(String[] args) {
        List<Hero> heroes = List.of(
                new Hero("ajay", 45000d, 5, "male"),
                new Hero("raveena", 49000d, 1, "female"),
                new Hero("sharma", 35000d, 3, "male"),
                new Hero("preity", 65000d, 2, "female"),
                new Hero("lamba", 25000d, 4, "male"),
                new Hero("karan", 25000d, 8, "male"),
                new Hero("parul", 35000d, 7, "female")
        );

        //highest salary
        Double highestSalUsingMax = heroes.stream()
                .max(Comparator.comparing(Hero::getSalary))
                .map(Hero::getSalary)
                .orElse(0d);
        System.out.println("highestSalUsingMax = " + highestSalUsingMax);

        //using doubleStream
        Double highestSalDoubleStream = heroes.stream()
                .map(Hero::getSalary)
                .max(Comparator.comparingDouble(Double::doubleValue))
                .orElse(0d);
        System.out.println("highestSalDoubleStream = " + highestSalDoubleStream);

        //using reduce
        Double highesSalUsingReduce = heroes.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparing(Hero::getSalary)))
                .map(Hero::getSalary)
                .orElse(0d);
        System.out.println("highesSalUsingReduce = " + highesSalUsingReduce);

        //sum of salaries of all employees
        double salarySumAllEmployees = heroes.stream()
                .map(Hero::getSalary)
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println("salarySumAllEmployees = " + salarySumAllEmployees);

        //above using Stream<Double>
        Double salarySumReduce = heroes.stream()
                .map(Hero::getSalary)
                .reduce(Double::sum)
                .orElse(0d);
        System.out.println("salarySumReduce = " + salarySumReduce);

        //sort by rank increasing
        List<Hero> sortedByRankIncreasing = heroes.stream()
                .sorted(Comparator.comparingInt(Hero::getRank))
                .toList();
        System.out.println("sortedByRankIncreasing = " + sortedByRankIncreasing);

        //sort by rank decreasing
        List<Hero> sortedByRankDecreasing = heroes.stream()
                .sorted(Comparator.comparing(Hero::getRank, Comparator.reverseOrder()))
                .toList();
        System.out.println("sortedByRankDecreasing = " + sortedByRankDecreasing);

        //sort lexicographically comparison by name
        System.out.println("Names sorted in natural/lexicographical order : ");
        heroes.stream()
                .sorted(Comparator.comparing(Hero::getName))
                .map(Hero::getName)
                .forEach(name -> System.out.print(name + " "));

        //group by gender
        Map<String, List<Hero>> groupByGender = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender
                        )
                );
        System.out.println("\ngroupByGender = " + groupByGender);

        //count by gender
        Map<String, Long> countByGender = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.counting()
                        )
                );
        System.out.println("countByGender = " + countByGender);

        //sum of salaries by gender
        Map<String, Double> sumOfSalaiesByGender = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.summingDouble(Hero::getSalary)
                        )
                );
        System.out.println("sumOfSalaiesByGender = " + sumOfSalaiesByGender);

        //thenComparing-first by salary and then by rank
        System.out.println("thenComparing-first by salary and then by rank : ");
        Comparator<Hero> heroComparator = Comparator.comparing(Hero::getSalary).thenComparing(Hero::getRank);
        LinkedHashSet<Hero> heroesBySalThenRank = heroes.stream()
                .sorted(heroComparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));//Collectors.toCollection(LinkedHashSet::new) supplier
        System.out.println("heroesBySalThenRank = " + heroesBySalThenRank);

        //by gender ->  mapped to list of names
        Map<String, List<String>> genderMappedToNames = heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                Hero::getGender,
                                Collectors.mapping(
                                        Hero::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("genderMappedToNames = " + genderMappedToNames);

    }
}

