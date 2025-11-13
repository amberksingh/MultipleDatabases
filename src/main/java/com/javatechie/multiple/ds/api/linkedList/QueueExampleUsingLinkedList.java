package com.javatechie.multiple.ds.api.linkedList;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class QueueExampleUsingLinkedList {

    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();
        queue.add("varun");
        queue.add("arun");
        queue.add("kumar");
        queue.add("ramesh");
        queue.add("elizabeth");
        queue.add("grover");
        queue.add("salman");

        //ADDFIRST, ADDLAST NOT AVAILABLE IN QUEUE. ONLY PRESENT IN DEQUE
        System.out.println("queue = " + queue);

        //RETURNS BOOLEAN SIMILAR TO ADD/ADDFIRST/ADDLAST
        queue.offer("tarun");//adds to last
        //OFFERFIRST/OFFERLAST/PUSH/POP NOT AVAILABLE IN QUEUE
        System.out.println("after offer : " + queue);

        //REMOVEFIRST/REMOVELAST NOT PRESENT
        System.out.println("removed element : " + queue.remove());//varun-removes from front

        //POLLFIRST/POLLLAST NOT PRESENT
        System.out.println("polled element : " + queue.poll());//arun-removes from front

        //PEEKFIRST/PEEKLAST NOT PRESENT
        System.out.println("peek : " + queue.peek());

        System.out.println("queue after above operations : " + queue);

        queue.add(null);//null allowed
        System.out.println("queue = " + queue);

        //duplicate
        queue.add("grover");//allowed

        //queue.sort(Comparator) not allowed as queue doesn't have sort() method. it's present in List interface not queue
        //use stream.sorted() for sorting
        List<String> sortedQueue = queue.stream()
                .sorted(Comparator.nullsLast(Comparator.reverseOrder()))
                .toList();
        System.out.println("queue after sorting desc order = " + sortedQueue);

        System.out.println("removed element using(object): " + queue.remove("elizabeth"));//boolean
        System.out.println("queue after above operations : " + queue);
    }
}
