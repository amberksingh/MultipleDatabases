package com.javatechie.multiple.ds.api.programs;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

//1. Outer stream
//list.stream()
//
//
//gives you a Stream<List<String>> with two elements:
//
//First element → [A, B]
//
//Second element → [C, D]
//
//2. What happens in flatMap
//.flatMap(x -> x.stream())
//
//
//For each outer element ([A,B], [C,D]), the lambda returns a stream:
//
//First time → a Stream over A,B
//
//Second time → a Stream over C,D
//
//So at this point you conceptually have:
//
//Stream( Stream(A,B), Stream(C,D) )
//
//3. Flattening step
//
//The magic of flatMap is: as soon as an inner stream is produced, its elements are immediately fed into the downstream pipeline.
//
//It does not first collect both inner streams and then flatten at the end.
//Instead:
//
//Take outer element [A,B].
//
//Produce inner stream (A,B).
//
//Immediately push A, then B downstream (into your next peek/collector).
//
//Take outer element [C,D].
//
//Produce inner stream (C,D).
//
//Immediately push C, then D downstream.
//
//So it becomes a single “flattened” stream:
//
//Stream(A,B,C,D)
//
//4. Answering your specific point
//
//we get 2 streams for a,b and one for c,d. then they are flattened into one stream i.e stream.of(a,b,c,d).?
// when does flattening happen, after both iteration for each outer list?
//
//✅ You get two inner streams:
//
//Stream1: A,B
//
//Stream2: C,D
//
//✅ They are flattened on-the-fly, not after the whole outer iteration finishes.
//
//Flattening happens as each inner stream is produced.
//
//So elements from [A,B] go downstream immediately, before the outer loop even moves to [C,D].
//
//5. Quick analogy
//
//Think of flatMap like nested loops:
//
//for (List<String> inner : list) {       // outer stream
//    for (String s : inner) {            // inner stream
//        // push downstream
//    }
//}
//
//
//Flattening = the inner for loop.
//It’s executed one outer element at a time, not collected then merged later.
//
//✅ Summary:
//
//x.stream() is called once per inner list.
//
//Flattening happens immediately for each inner stream, not after all outer elements are processed.
//
//The final effect is a single continuous stream: Stream.of("A","B","C","D").

public class FlatMap {

    public static void main(String[] args) {

        List<List<String>> list = List.of(
                List.of("A", "B"),
                List.of("C", "D")
        );
        System.out.println("original list = " + list);

        //map()
        List<Stream<String>> map = list.stream()//Stream<List<String>> with two elements:
                .peek(l -> {
                    System.out.println("before peek l = " + l);
                })
                .map(x -> {
                    System.out.println("x = " + x);
                    Stream<String> stream = x.stream();
                    System.out.println("map stream = " + stream);
                    return stream;
                })
                .peek(j -> {
                    System.out.println("after peek j = " + j);//still Stream in case of map()
                })
                .toList();
        map.stream().forEach(e ->
                e.forEach(s -> System.out.print(s+" "))
        );
        map.forEach(e -> e.forEach(b -> System.out.println("b = " + b)));

        //flatMap()
        List<String> flatMap = list.stream()
                .peek(l -> {
                    System.out.println("\nflatMap before peek l = " + l);
                })
                .flatMap(x -> {
                    System.out.println("x = " + x);
                    Stream<String> stream = x.stream();
                    //we get flattened data after this. first A,B is collected after flattening before
                    // even outer list(C, D) has been touched. Flattening happens immediately.
                    //That's why we get valid data in last peek i.e "j" value in case of flatmap.
                    System.out.println("flatMap stream = " + stream);
                    return stream;
                })
                .peek(j -> {
                    System.out.println("flatMap after peek j = " + j);//Flattened values and NOT stream as in the case of map()
                })
                .toList();
        System.out.println("flatMap = " + flatMap);

        //flatMapToInt
        List<String> words = List.of("hi", "go");
        System.out.println("original words list = " + words);
        words.stream()
                .flatMapToInt(String::chars)
                .forEach(s -> System.out.println(s));

        List<int[]> listOfIntArrays = List.of(
                new int[]{5, 3},
                new int[]{9, 7}
        );
        System.out.println("int[] arrays after flattening : ");
        List<Integer> list1 = listOfIntArrays.stream()
                .flatMapToInt(Arrays::stream)
                .boxed()
                .toList();
        System.out.println("list1 = " + list1);

        //flatMapToLong
        List<long[]> listOfLongArray = List.of(
                new long[]{10, 20},
                new long[]{30, 40}
        );
        System.out.println("\nlong[] arrays after flattening : ");
        List<Long> list2 = listOfLongArray.stream()
                .flatMapToLong(Arrays::stream)//preferred approach in such scenarios as compared to below
                .boxed()
                .toList();
        System.out.println("list2 = " + list2);

        //IntFunction<Long[]> intFunction = (i) -> new Long[i];
        IntFunction<Long[]> intFunction = Long[]::new;
        Long[] array = listOfLongArray.stream()
                .flatMap(s -> Arrays.stream(s).boxed())//hacky fix if we want to use flatMap() instead of flatMapToLong
                .toArray(Long[]::new);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
        //Long[] → doesn’t override toString(), so printing it shows a memory-like string.
        // Use Arrays.toString(Long[]) instead.
        //Long (the wrapper) → does override toString(), so printing gives the actual number.

        //flatMapToDouble
        List<Double[]> listOfDoubleArray = List.of(
                new Double[]{14d, 18d},
                new Double[]{90d, 100d}
        );
        List<Double> list3 = listOfDoubleArray.stream()
                .flatMap(Arrays::stream)
                .toList();
        System.out.println("list3 = " + list3);

        double[] array1 = listOfDoubleArray.stream()
                .flatMapToDouble(s -> Arrays.stream(s).mapToDouble(Double::doubleValue))
                .toArray();
        System.out.println("Arrays.toString(array1) = " + Arrays.toString(array1));

        List<double[]> listOfdoubleArray = List.of(
                new double[]{14d, 18d},
                new double[]{90d, 100d}
        );
        double[] array2 = listOfdoubleArray.stream()
                .flatMapToDouble(Arrays::stream)
                .toArray();
        System.out.println("Arrays.toString(array2) = " + Arrays.toString(array2));

        Double[] array3 = listOfdoubleArray.stream()
                .flatMap(s -> Arrays.stream(s).boxed())
                .toArray(Double[]::new);
        System.out.println("Arrays.toString(array3) = " + Arrays.toString(array3));
    }
}
