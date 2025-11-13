package com.javatechie.multiple.ds.api.code;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

//Comparator<String> desc = Comparator.comparing(Function.<String>identity(), Comparator.reverseOrder());
// gives String as return type of function and that resolves U to String instead of object?
//ChatGPT said:
//
//Exactly 👍 — you’ve nailed the key point. Let’s walk through it step by step so it’s crystal clear:
//
//1. The generic signature again
//static <T, U> Comparator<T> comparing(
//    Function<? super T, ? extends U> keyExtractor,
//    Comparator<? super U> keyComparator
//)
//
//
//T = the type of the elements being compared (your String list elements).
//
//U = the type of the key returned by the function (something we compare on).
//
//So in your case:
//
//T should be String.
//
//U should also be String (since you’re just using the element itself as the key).
//
//2. The problem without the type witness
//
//Function.identity() is defined as:
//
//static <T> Function<T, T> identity()
//
//
//If the compiler can’t infer T from context, it defaults to Object.
//So Function.identity() bare often becomes Function<Object,Object>.
//
//That leads to:
//
//U → inferred as Object
//
//but then you try to use Comparator.reverseOrder() (which requires U extends Comparable<? super U>).
//
//Object is not Comparable<Object>, so inference fails.
//
//3. Why Function.<String>identity() fixes it
//
//When you write:
//
//Function.<String>identity()
//
//
//you’re giving the compiler a type witness.
//That forces T = String inside the identity function, i.e.:
//
//Function<String, String>
//
//
//Now inference becomes:
//
//T = String
//
//U = String
//
//keyExtractor = Function<String, String>
//
//keyComparator = Comparator<String> (from Comparator.reverseOrder())
//
//✅ All the pieces match.
//
//4. Intuition
//
//Yes — by locking Function.identity() to Function<String,String>, you tell the compiler:
//
//“The key extractor returns a String, not Object.”
//
//That’s what makes the compiler resolve U = String instead of U = Object.
//And since String implements Comparable<String>, Comparator.reverseOrder() works perfectly.
//
//✅ So your understanding is correct: with the explicit <String>, the function’s return type is String,
// which makes U resolve to String instead of Object.
public class ComparatorStuff {

    public static void main(String[] args) {

        List<String> list = List.of("cat", "lion", "tiger");

        //1
        Comparator<String> asc = Comparator.comparing(Function.identity());
        List<String> list1 = list.stream()
                .sorted(asc)
                .toList();
        System.out.println("list1 = " + list1);

        //2
        Comparator<String> desc = Comparator.comparing(Function.<String>identity(), Comparator.reverseOrder());
        List<String> list2 = list.stream()
                .sorted(desc)
                .toList();
        System.out.println("list2 = " + list2);

        //3
        Comparator<String> desc1 = Comparator.comparing(Function.<String>identity()).reversed();
        List<String> list3 = list.stream()
                .sorted(desc1)
                .toList();
        System.out.println("list3 = " + list3);

        //4 typed function
        Comparator<String> desc3 = Comparator.comparing(x -> x, Comparator.reverseOrder());
        List<String> list4 = list.stream()
                .sorted(desc3)
                .toList();
        System.out.println("list4 = " + list4);

        Comparator<String> desc4 = Comparator.comparing((String x) -> x).reversed();
        List<String> list5 = list.stream()
                .sorted(desc4)
                .toList();
        System.out.println("list5 = " + list5);

    }
}
