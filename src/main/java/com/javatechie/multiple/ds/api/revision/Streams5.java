package com.javatechie.multiple.ds.api.revision;

import java.util.*;
import java.util.stream.Collectors;

class Engineer {
    String name;
    int age;
    double salary;
    String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Engineer(String name, int age, double salary, String gender) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    Engineer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Engineer(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                '}';
    }
}

public class Streams5 {

    public static void main(String[] args) {

        //thenComparing
        List<Engineer> engineers = List.of(
                new Engineer("Zara", 25, 1000, "female"),
                new Engineer("Alicen", 22, 5000, "female"),
                new Engineer("Bob", 23, 3000, "male"),
                new Engineer("Tom", 25, 2500, "male"),
                new Engineer("Alsane", 22, 4000, "female"),
                new Engineer("Arun", 25, 6000, "male")
        );

        //compare by age then name lexicographically
        Comparator<Engineer> engineerComparator = Comparator.comparing(Engineer::getAge)
                .thenComparing(Engineer::getName);
        List<Engineer> list = engineers.stream()
                .sorted(engineerComparator)
                .toList();
        System.out.println("list = " + list);

        //compare by age then name length wise
        Comparator<Engineer> engineerComparator1 = Comparator.comparing(Engineer::getAge)
                .thenComparing(Engineer::getName, Comparator.comparing(String::length));
        List<Engineer> list1 = engineers.stream()
                .sorted(engineerComparator1)
                .toList();
        System.out.println("list1 = " + list1);

        //reversed way
        Comparator<Engineer> engineerComparator2 = Comparator.comparing(Engineer::getAge, Comparator.reverseOrder())
                .thenComparing(Engineer::getSalary, Comparator.reverseOrder())
                .thenComparing(Engineer::getName, String::compareTo);
        List<Engineer> list2 = engineers.stream()
                .sorted(engineerComparator2)
                .toList();
        System.out.println("list2 = " + list2);

        //nullsFirst
        List<String> names = Arrays.asList("Bob", null, "Alice", null, "vasco", "tom");
        List<String> list3 = names.stream()
                .sorted(Comparator.nullsFirst(String::compareTo))
                .toList();
        System.out.println("list3 = " + list3);

        //nullsLast
        List<String> list4 = names.stream()
                .sorted(Comparator.nullsLast(String::compareTo))
                .toList();
        System.out.println("list4 = " + list4);

        //name to salary
        Map<String, Double> collect = engineers.stream()
                .collect(
                        Collectors.toMap(
                                Engineer::getName,
                                Engineer::getSalary,
                                (oldVal, newVal) -> newVal
                        )
                );
        System.out.println("collect = " + collect);

        //summarizingInt(), summarizingDouble(), summarizingLong()
        IntSummaryStatistics collect1 = engineers.stream()
                .collect(
                        Collectors.summarizingInt(Engineer::getAge)
                );
        System.out.println("age stuff = " + collect1);

        DoubleSummaryStatistics collect2 = engineers.stream()
                .collect(
                        Collectors.summarizingDouble(
                                Engineer::getSalary
                        )
                );
        System.out.println("salary stuff = " + collect2);

        //converting age to long
        LongSummaryStatistics collect3 = engineers.stream()
                .map(Engineer::getAge)
                .map(Integer::longValue)
                .collect(
                        Collectors.summarizingLong(x -> x)
                );
        System.out.println("age in long terms = " + collect3);

        //groupingBy
        System.out.println("engineers object by gender : ");
        Map<String, List<Engineer>> collect4 = engineers.stream()
                .collect(
                        Collectors.groupingBy(
                                Engineer::getGender
                        )
                );
        collect4.entrySet()
                .stream()
                .forEach(entry -> {
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                });

        //count gender wise
        Map<String, Long> collect5 = engineers.stream()
                .collect(
                        Collectors.groupingBy(
                                Engineer::getGender,
                                Collectors.counting()
                        )
                );
        System.out.println("count by gender = " + collect5);

        //map gender to name
        Map<String, List<String>> collect6 = engineers.stream()
                .collect(
                        Collectors.groupingBy(
                                Engineer::getGender,
                                Collectors.mapping(
                                        Engineer::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("gender wise names = " + collect6);

        //summingInt
        //age sum
        int ageSum = engineers.stream()
                .collect(
                        Collectors.summingInt(Engineer::getAge)
                ).intValue();
        System.out.println("ageSum = " + ageSum);

        int sumAge = engineers.stream()
                //.map(Engineer::getAge)
                .mapToInt(Engineer::getAge)
                .sum();
        System.out.println("sumAge = " + sumAge);

        //map names to salary
        System.out.println("name to salary : ");
        engineers.stream()
                .collect(
                        Collectors.toMap(
                                Engineer::getName,
                                Engineer::getSalary
                        )
                ).entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        System.out.println("engineers by gender mapped to count and keys/gender in sorted order/ascending : ");
        TreeMap<String, Long> collect7 = engineers.stream()
                .collect(
                        Collectors.groupingBy(
                                Engineer::getGender,
                                TreeMap::new,
                                Collectors.counting()
                        )
                );
        System.out.println(collect7);

        //partitioningBy
        //gender wise engineers
        System.out.println("gender wise engineers using partitioningBy : ");
        engineers.stream()
                .collect(
                        Collectors.partitioningBy(
                                e -> e.getGender().equalsIgnoreCase("male")
                        )
                )
                .entrySet()
                .stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        //gender wise mapped to names only
        System.out.println("gender wise engineers names using partitioningBy : ");
        Map<Boolean, List<String>> male = engineers.stream()
                .collect(
                        Collectors.partitioningBy(
                                e -> e.getGender().equalsIgnoreCase("male"),
                                Collectors.mapping(
                                        Engineer::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("male/female names = " + male);
        //converting above map to custom meaningful keys;
        Map<String, List<String>> customkeyMap = new LinkedHashMap<>();
        customkeyMap.put("Male", male.get(true));
        customkeyMap.put("Female", male.get(false));
        customkeyMap.forEach((gender, name) -> System.out.println(gender + "-> " + name));

        System.out.println("male/female mapped to age sum = ");
        Map<Boolean, Integer> male1 = engineers.stream()
                .collect(
                        Collectors.partitioningBy(
                                e -> e.getGender().equalsIgnoreCase("male"),
                                Collectors.summingInt(Engineer::getAge)
                        )
                );
        System.out.println("male/female to age sum = " + male1);


    }
}
