package com.javatechie.multiple.ds.api.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Streams1 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(
                List.of("arun", "varun", "rahul", "lamba", "punter", "arun", "samarth", "faizal", "samarth"));
        //convert to uppercase and filter on condition
        List<String> r = list.stream()
                .distinct()
                .filter(name -> name.contains("r"))
                .map(String::toUpperCase)
                .toList();
        System.out.println("names after filtering and uppercase = " + r);

        //list sorting diff ways
        list.sort(Comparator.naturalOrder());
        System.out.println("sorting using list.sort() asc : " + list);

        list.sort(String::compareTo);
        System.out.println("sorting using list.sort() asc : " + list);

        List<String> list1 = list.stream()
                .sorted()
                .toList();
        System.out.println("sorting using list.stream().sorted() asc : " + list1);

        List<String> list2 = list.stream()
                .sorted(Comparator.comparing(x -> x))
                .toList();
        System.out.println("sorting using list.stream().sorted(Comparator.comparing(x -> x)) asc : " + list2);

        Collections.sort(list);
        System.out.println("sorting using Collections.sort(list) asc : " + list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("sorting using Collections.sort(list) desc : " + list);

        Comparator<String> comp1 = Comparator.comparing(Function.<String>identity(), Comparator.reverseOrder());
        list.sort(comp1);
        System.out.println("sorting using Comparator.comparing(Function.identity(), Comparator.reverseOrder()) desc : " + list);
        //Function.identity() by default is of type Function<T, T> where T erases to Object.
        //
        //So the compiler thinks U = Object.
        //
        //But Comparator.reverseOrder() requires Comparator<? super U> where U must be comparable.
        //
        //Object is not comparable → compiler error.
        //
        //✅ Fix: Force U = String with Function.<String>identity() so the compiler knows the key type is String.

        Comparator<String> comp2 = Comparator.comparing(x -> x, Comparator.reverseOrder());
        list.sort(comp2);
        System.out.println("sorting using Comparator.comparing(x -> x, Comparator.reverseOrder()) desc : " + list);

        Comparator<String> comp3 = Comparator.comparing((String x) -> x).reversed();
        list.sort(comp3);
        System.out.println("sorting using Comparator.comparing((String x) -> x).reversed() desc : " + list);
        //Here, (String x) -> x makes the lambda’s parameter explicitly typed (String).
        //
        //That tells the compiler that T = String and U = String.
        //
        //Since String implements Comparable<String>, the first overload works.
        //
        //Then .reversed() is fine.
        //
        //👉 Without (String x) -> x, if you wrote x -> x, the compiler sometimes infers Object for U, and Object is
        // not Comparable<Object>. That’s why explicit typing avoids the error.

        //1. Case A:
        //Comparator<String> comp2 =
        //    Comparator.comparing(x -> x, Comparator.reverseOrder());
        //
        //
        //Here x -> x is a lambda expression.
        //
        //Because the target type is Comparator<String>, the compiler knows T = String.
        //
        //That makes the lambda Function<String, String>.
        //
        //For the second parameter: Comparator.reverseOrder() is a Comparator<Comparable>.
        //Since String implements Comparable<String>, this matches Comparator<String>.
        //
        //So the two-argument overload is picked, with U = String.
        //
        //✅ No cast needed — type inference works because both the lambda and the comparator give enough hints.
        //
        //2. Case B:
        //Comparator<String> asc = Comparator.comparing(Function.identity());
        //
        //
        //Here you’re calling the one-argument overload.
        //
        //Function.identity() returns a Function<T, T>, but erased form is Function<Object, Object>.
        //
        //The compiler sees you want a Comparator<String>.
        //
        //It infers T = String, U = String.
        //
        //And since String is Comparable<String>, the constraint U extends Comparable<? super U> is satisfied.
        //
        //✅ Works fine.
        //
        //3. Why sometimes you need a cast (Function.<String>identity())
        //
        //If you chain with .reversed(), the compiler loses the link between the Function.identity() generic and the required Comparable type.
        //
        //That’s why this fails:
        //
        //Comparator<String> c = Comparator.comparing(Function.identity()).reversed();
        //
        //
        //→ compiler infers U = Object (not comparable).
        //
        //Fix is to force it:
        //
        //Comparator<String> c = Comparator.comparing(Function.<String>identity()).reversed();
        //
        //✅ Summary
        //
        //x -> x works in 2-arg overload because lambda target type + Comparator.reverseOrder() give the compiler enough info (U = String).
        //
        //Function.identity() works in 1-arg overload because the target type (Comparator<String>) drives inference (U = String).
        //
        //It fails in some chained cases (.reversed()) because inference loses track → need Function.<String>identity() to lock the type.

    }
}
