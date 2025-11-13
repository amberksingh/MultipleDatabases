package com.javatechie.multiple.ds.api.again;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class FlatMap {

    public static void main(String[] args) {

        List<List<String>> list = List.of(
                List.of("A", "B"),
                List.of("C", "D")
        );
        System.out.println("original list = " + list);

        List<String> list1 = list.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println("flattened list = " + list1);

        List<String> words = List.of("hi", "go");
        System.out.println("original words list = " + words);
        int[] array = words.stream()
                .flatMapToInt(String::chars)
                .toArray();
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));

        List<int[]> listOfIntArrays = List.of(
                new int[]{5, 3},
                new int[]{9, 7}
        );
        System.out.println("int[] arrays after flattening : ");
        int[] array1 = listOfIntArrays.stream()
                .flatMapToInt(Arrays::stream)
                .toArray();
        System.out.println("Arrays.toString(array1) = " + Arrays.toString(array1));

        List<long[]> listOfLongArray = List.of(
                new long[]{10, 20},
                new long[]{30, 40}
        );
        long[] array2 = listOfLongArray.stream()
                .flatMapToLong(Arrays::stream)
                .toArray();
        System.out.println("Arrays.toString(array2) = " + Arrays.toString(array2));

        //flatMapToDouble
        List<Double[]> listOfDoubleArray = List.of(
                new Double[]{14d, 18d},
                new Double[]{90d, 100d}
        );
        Double[] array3 = listOfDoubleArray.stream()
                .flatMap(Arrays::stream)
                .toArray(Double[]::new);
        System.out.println("Arrays.toString(array3) = " + Arrays.toString(array3));

        List<double[]> listOfdoubleArray = List.of(
                new double[]{14d, 18d},
                new double[]{90d, 100d}
        );
        //IntFunction<Double[]> intFunction = (i) -> new Double[i];
        IntFunction<Double[]> intFunction = Double[]::new;
        Double[] array4 = listOfdoubleArray.stream()
                .flatMap(x -> Arrays.stream(x).boxed())
                .toArray(intFunction);
        System.out.println("Arrays.toString(array4) = " + Arrays.toString(array4));

    }
}
