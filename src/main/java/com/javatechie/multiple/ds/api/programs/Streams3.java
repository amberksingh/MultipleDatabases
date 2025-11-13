package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

class Engineer {
    String name;
    int age;
    double salary;

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
                '}';
    }

}

public class Streams3 {

    public static void main(String[] args) {

        List<Engineer> engineers = List.of(
                new Engineer("Zara", 25, 1000),
                new Engineer("Alice", 22, 5000),
                new Engineer("Bob", 30, 3000),
                new Engineer("Tom", 25, 2500),
                new Engineer("Alex", 22, 2200)
        );

        //sort by name in reverse order
        Comparator<Engineer> nameReverseComp = Comparator.comparing(Engineer::getName, Comparator.reverseOrder());
        List<Engineer> list = engineers.stream()
                .sorted(nameReverseComp)
                .toList();
        System.out.println("nameReverseComp = " + list);

        //age wise increasing and name wise decreasing
        Comparator<Engineer> ageIncNameDesc = Comparator.comparing(Engineer::getAge)
                .thenComparing(Engineer::getName, Comparator.reverseOrder());
        List<Engineer> list1 = engineers.stream()
                .sorted(ageIncNameDesc)
                .toList();
        System.out.println("ageIncNameDesc = " + list1);

        //name length desc and age decreasing
        //comparingInt has only one param. Use comparing for reverse order
        Function<Engineer, Integer> func = (e) -> e.getName().length();
        Comparator<Engineer> nameLengthDescAgeDesc = Comparator.comparing(func, Comparator.reverseOrder())
                .thenComparing(Engineer::getAge, Comparator.reverseOrder());
        List<Engineer> list2 = engineers.stream()
                .sorted(nameLengthDescAgeDesc)
                .toList();
        System.out.println("nameLengthDescAgeDesc = " + list2);


        Comparator<Engineer> ageIncNameLengthInc = Comparator.comparing(Engineer::getAge)
                .thenComparing(func, Comparator.comparingInt(e -> e));
        List<Engineer> list3 = engineers.stream()
                .sorted(ageIncNameLengthInc)
                .toList();
        System.out.println("ageIncNameLengthInc = " + list3);

        List<String> animal = List.of("cat", "lion", "tiger");
        //sort animals on length increasing order
        System.out.println("animal length wise inc order : ");
        Comparator<String> strLength = Comparator.comparing(String::length);
        animal.stream()
                .sorted(strLength)
                .forEach(n -> System.out.print(n + " "));

        //salary based double
        Comparator<Engineer> salIncAgeDesc = Comparator.comparingDouble(Engineer::getSalary)
                .thenComparing(Engineer::getAge, Comparator.reverseOrder());
        List<Engineer> list4 = engineers.stream()
                .sorted(salIncAgeDesc)
                .toList();
        System.out.println("\nsalIncAgeDesc = " + list4);

        //nullsLast and nullsFirst
        List<String> names = Arrays.asList("Bob", null, "Alice");
        System.out.println("Comparator.nullsFirst(Comparator.naturalOrder() : ");
        names.stream()
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\nComparator.nullsLast(Comparator.reverseOrder()) : ");
        names.stream()
                .sorted(Comparator.nullsLast(Comparator.reverseOrder()))
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        //long
        Comparator<Long> longComparator = Comparator.comparingLong(Long::longValue);
        System.out.println(longComparator.compare(21L, 45L));//-1

    }
}
