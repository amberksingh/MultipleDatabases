package com.javatechie.multiple.ds.api.again;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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

public class ComparatorInt {

    public static void main(String[] args) {

        List<Engineer> engineers = List.of(
                new Engineer("Zara", 25, 1200),
                new Engineer("Alice", 22, 5000),
                new Engineer("Bob", 30, 3000),
                new Engineer("Tom", 25, 2500),
                new Engineer("Alex", 30, 5000),
                new Engineer("Alex", 20, 3200),
                new Engineer("Zara", 22, 1200)
        );

        System.out.println("\nOriginal Engineer list : " + engineers);

        //sort by name then age
        Comparator<Engineer> nameThenAge = Comparator.comparing(Engineer::getName)
                .thenComparingInt(Engineer::getAge);
        System.out.println("name then age wise comparison : ");
        engineers.stream()
                .sorted(nameThenAge)
                .forEach(System.out::println);

        //sort by age then name
        Comparator<Engineer> ageThenName = Comparator.comparing(Engineer::getAge)
                .thenComparing(Engineer::getName);
        System.out.println("age then name wise comparison : ");
        engineers.stream()
                .sorted(ageThenName)
                .forEach(System.out::println);

        //sort by age then name
        Comparator<Engineer> ageReverseNameReverse = Comparator.comparing(Engineer::getAge, Comparator.reverseOrder())
                .thenComparing(Engineer::getName, Comparator.reverseOrder());
        List<Engineer> list = engineers.stream()
                .sorted(ageReverseNameReverse)
                .toList();
        System.out.println("ageReverseNameReverse = " + list);

        //salary based
        //salary desc then age asc
        Comparator<Engineer> salaryDescAgeAsc = Comparator.comparing(Engineer::getSalary, Comparator.reverseOrder())
                .thenComparingInt(Engineer::getAge);
        System.out.println("salary descending then age ascending : ");
        engineers.stream()
                .sorted(salaryDescAgeAsc)
                .forEach(System.out::println);

        //NOTE -> Both nullsFirst and nullsLast accept a comparator as parameter
        //nullsFirst
        List<String> names = Arrays.asList("Bob", null, "Alice");
        String[] array = Stream.of(names)
                .flatMap(List::stream)//extra if using Stream.of(list)
                .sorted(Comparator.nullsFirst(Comparator.naturalOrder()))
                .toArray(String[]::new);
        System.out.println("nullsFirst = " + Arrays.toString(array));

        //nullsLast
        List<String> list1 = names.stream()
                .sorted(Comparator.nullsLast(Comparator.reverseOrder()))
                .toList();
        System.out.println("nullsLast = " + list1);

        //string length basis
        //name length reverse then salary asc
        Comparator<Engineer> nameLengthRevSalAsc = Comparator
                .comparing((Engineer eng) -> eng.getName().length(), Comparator.reverseOrder())
                .thenComparingDouble(Engineer::getSalary);
        List<Engineer> list2 = engineers.stream()
                .sorted(nameLengthRevSalAsc)
                .toList();
        System.out.println("nameLengthRevSalAsc = " + list2);

        //Step 2: What happens in each case
        //✅ Case A — Engineer::getSalary
        //
        //Engineer::getSalary is a method reference whose declared type is:
        //
        //ToDoubleFunction<Engineer>  OR  Function<Engineer, Double>
        //
        //
        //(based on overload chosen)
        //
        //The compiler sees:
        //
        //T = Engineer
        //
        //U = Double
        //
        //Comparator.reverseOrder() → returns Comparator<Double>
        //
        //✅ Type inference succeeds easily.
        //All types align cleanly, no explicit type hint needed.
        //
        //⚠️ Case B — eng -> eng.getName().length()
        //
        //Now your key extractor is a lambda returning a primitive int — not a Function<Engineer, Integer> directly.
        //
        //Java auto-boxes that int to an Integer, but here’s the catch:
        //
        //The compiler cannot infer T (Engineer) and U (Integer) at the same time when a primitive and a lambda are both involved — there’s no concrete type anchor to start from.*
        //
        //So it first tries to infer:
        //
        //Comparator.comparing(Function<? super T, ? extends U>, Comparator<? super U>)
        //
        //
        //but since the left-hand side hasn’t given a type hint yet,
        //it defaults the lambda parameter type to Object.
        //
        //That’s why IntelliJ complains if you omit the type:
        //
        //Cannot resolve method 'getName' in 'Object'
        //
        //
        //When you write:
        //
        //(Engineer eng) -> eng.getName().length()
        //
        //
        //you explicitly fix the lambda’s parameter type → the compiler can now deduce:
        //
        //T = Engineer
        //
        //U = Integer
        //and it matches perfectly with Comparator.reverseOrder().
        //
        //🔹 Step 3: Why Engineer::getSalary doesn’t have the same problem
        //
        //Because method references already carry their full generic type info,
        //while lambdas don’t until inference resolves them.
        //
        //Engineer::getSalary → compiler knows it’s Function<Engineer, Double>
        //
        //eng -> eng.getName().length() → compiler must guess T and U from context
        //
        //Without the explicit (Engineer eng) type, there’s no “anchor” for the compiler to infer that eng is an Engineer.
        //
        //🔹 Step 4: You can also fix it another way
        //
        //If you help the compiler by anchoring the generic type on Comparator.comparing,
        //you can drop the (Engineer eng) too.
        //
        //✅ Example:
        //
        //Comparator<Engineer> nameLengthRevSalAsc =
        //        Comparator.<Engineer, Integer>comparing(eng -> eng.getName().length(), Comparator.reverseOrder())
        //                  .thenComparingDouble(Engineer::getSalary);
        //
        //
        //Now we explicitly told Java:
        //
        //T = Engineer
        //
        //U = Integer
        //
        //So the lambda can remain untyped (eng -> ...).
        //
        //🔹 Step 5: Quick summary table
        //Comparator	Key Extractor Type	Auto Inference Works?	Why / Why Not
        //Engineer::getSalary	Method reference (typed)	✅ Yes	Method ref carries Function<Engineer, Double> info
        //eng -> eng.getName().length()	Lambda returning int (autoboxed to Integer)	❌ No	Compiler can’t infer T/U types together without explicit hint
        //Comparator.<Engineer, Integer>comparing(...)	Lambda (generic anchored)	✅ Yes	You gave explicit generic types
        //(Engineer eng) -> eng.getName().length()	Lambda (typed param)	✅ Yes	Explicit param type fixes inference
        //✅ TL;DR
        //
        //You needed (Engineer eng) in the first one
        //because the compiler couldn’t infer the lambda parameter type from context —
        //it treated eng as Object.
        //
        //You didn’t need it in the second one
        //because method references like Engineer::getSalary already supply full type info.

    }
}
