package com.javatechie.multiple.ds.api.linkedList;

import java.util.*;
import java.util.stream.Stream;

public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(7);
        list1.add(Integer.valueOf(2));
        list1.remove(1);//index basis removal - removes 7
        list1.remove(Integer.valueOf(5));//value basis removal - removes value 5
        System.out.println("list1 = " + list1);//contains only 2

        ArrayList<String> list = new ArrayList<>();
        list.add("arun");
        list.add(null);
        list.add("sachin");
        list.add("rohit");
        list.add("rohit");
        list.add("shekhar");
        list.add(null);
        list.add("varun");
        list.add("bunny");
        list.add("bunny");

        System.out.println("Is list empty : " + list.isEmpty());

        Object[] array = list.toArray();
        System.out.println("array : ");
        Stream.of(array).forEach(x -> {
            System.out.print(x + " ");
        });

        int[] intArray = {1,2,3};
        //this prints hashcode...since primitive value array. Use Arrays.stream(intArray) for primitive values
        Stream.of(intArray).forEach(x -> {
            System.out.print(x + "-");
        });

        //
        System.out.println("\nList size : " + list.size());
        System.out.println("\nElements of list using listIterator from end : ");
        ListIterator<String> stringListIterator = list.listIterator(list.size());
        while (stringListIterator.hasPrevious()) {
            System.out.print(stringListIterator.previous() + " ");
        }

        //
        System.out.println("\n\nElements of list using iterator : ");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        //
        System.out.println("\n\nElements of list using forEach lambda : ");
        list.forEach(x -> {
            System.out.print(x + " ");
        });

        //
        System.out.println("\n\nElements of list using forEach : ");
        for (String s : list) {
            System.out.print(s + " ");
        }

        //
        System.out.println("\n\nElement at index 7 : " + list.get(7));
        System.out.println("\nElement at index 7 after set() : " + list.set(7, "aditya"));//returns previous value associated with this index

        //
        ArrayList<String> collection = new ArrayList<>();
        collection.add("msdhoni");

        //
        list.addAll(collection);
        System.out.println("\nList after adding collection : ");
        list.forEach(x -> {
            System.out.print(x + " ");
        });

        System.out.println("\nDoes list contain msdhoni : " + list.contains("msdhoni"));
        System.out.println("\nIndex of sachin: " + list.indexOf("sachin"));

        //sorting
        System.out.println("\nElements before sorting : ");
        list.forEach(x -> System.out.print(x + " "));

        //
        System.out.println("\n\nElements after sorting ascending order. Null values at the end: ");
        list.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        list.forEach(x -> System.out.print(x + " "));

        //
        System.out.println("\n\nElements after sorting ascending order. Null values in the beginning: ");
        list.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        list.forEach(x -> System.out.print(x + " "));

        //
        list.removeIf(Objects::isNull);
        System.out.println("\n\nElements after removing null values : ");
        list.forEach(x -> System.out.print(x + " "));

        //
        Collections.sort(list);
        System.out.println("\n\nElements after sorting in ascending order Collections.sort(list): ");
        list.forEach(x -> System.out.print(x + " "));

        //
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("\n\nElements after sorting in reverse order Collections.sort(list, Collections.reverseOrder()) : ");
        list.forEach(x -> System.out.print(x + " "));
        Collections.sort(list, Comparator.reverseOrder());
        System.out.println("\n\nElements after sorting in reverse order Collections.sort(list, Comparator.reverseOrder()) : ");
        list.forEach(x -> System.out.print(x + " "));

        System.out.println("\n\nElements after sorting in ascending list.stream().sorted() : ");
        list.stream()
                .sorted()
                .forEach(x -> System.out.print(x + " "));

        System.out.println("\n\nElements after sorting in reverse order list.stream().sorted(Comparator.reverseOrder()) : ");
        list.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(x -> System.out.print(x + " "));


    }
}
