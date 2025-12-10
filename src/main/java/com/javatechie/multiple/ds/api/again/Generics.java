package com.javatechie.multiple.ds.api.again;

import java.util.ArrayList;
import java.util.HashMap;

//Here T is a type parameter
class Box<T> {

    T data;

    @Override
    public String toString() {
        return "Box{" +
                "data=" + data +
                '}';
    }

    public void setValue(T data) {
        this.data = data;
    }

    public T getValue() {
        return data;
    }

    public void message(T msg) {

    }

    public static <T> void printArray(T[] arr) {
        System.out.println("Inside printArray() method : ");
        for (T element : arr) {
            System.out.println(element);
        }
    }

}

public class Generics {

    public static void main(String[] args) {

        Box<String> stringBox = new Box<>();
        stringBox.setValue("string data");
        System.out.println("Received data : " + stringBox.getValue());//string data

        Box<Integer> integerBox = new Box<>();
        integerBox.setValue(100);
        //integerBox.setValue("fff");
        //above gives CT error
        //Required type:
        //Integer
        //Provided:
        //String

        System.out.println("Received data : " + integerBox.getValue());//100

        Box doubleBox = new Box();
        //doubleBox.setValue(88d);
        doubleBox.setValue("string..");
        System.out.println("Received data : " + doubleBox.getValue());

        String[] names = {"India", "Japan", "USA"};
        Integer[] nums = {1, 2, 3};

        Box.printArray(names);
        Box.printArray(nums);

        ArrayList<Box> list = new ArrayList<>();
        //ArrayList<Box<Integer>> list = new ArrayList<>();
        list.add(stringBox);
        //will throw error if ArrayList<Box<Integer>> list = new ArrayList<>();
        //but no error for ArrayList<Box> list = new ArrayList<>();

        list.add(integerBox);
        System.out.println("list = " + list);

//        ArrayList<String> g = new ArrayList<>();
//        HashMap<String, Integer> map = new HashMap<String, Integer>();

    }
}
