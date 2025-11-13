package com.javatechie.multiple.ds.api.linkedList;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class LinkedListDemo {

    //BY DEFAULT, LINKEDLIST BEHAVES LIKE A QUEUE:
    //
    //Add at end
    //
    //Remove from front
    //
    //But you can also use it as a stack or deque by explicitly choosing the methods.

    //So What Does It "Use"?
    //It doesn't use one behavior by default — rather:
    //
    //🧠 LinkedList supports all three behaviors: List, Queue, and Stack, depending on which methods you call.
    //
    //🧪 Examples:
    //🔹 Queue behavior (FIFO):

    //LinkedList<String> q = new LinkedList<>();
    //q.offer("A");
    //q.offer("B");
    //System.out.println(q.poll()); // A

    //🔹 Deque behavior:
    //"DOUBLE ENDED QUEUE"

    //LinkedList<String> dq = new LinkedList<>();
    //dq.addFirst("X");
    //dq.addLast("Y");
    //System.out.println(dq.removeLast()); // Y

    //🔹 Stack behavior (LIFO):

    //LinkedList<String> stack = new LinkedList<>();
    //stack.push("1");
    //stack.push("2");
    //System.out.println(stack.pop()); // 2

    //🔁 Conclusion:
    //Behavior	        Supported?	            Interface
    //List              (ordered, indexed)	    ✅	List
    //Queue             (FIFO)	                ✅	Queue (via Deque)
    //Stack             (LIFO)              	✅	Deque methods like push()/pop()

    //LinkedList is a Deque under the hood, and can act as a list, queue, or stack — based on how you use it.

    public static void main(String[] args) {

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("ramesh");//returns boolean
        linkedList.add("varun");
        linkedList.add("akash");
        linkedList.add("binod");
        linkedList.add("modi");
        linkedList.add("kumar");
        System.out.println("linkedList = " + linkedList);

        //element()-retrieves/doesn't remove first element
        System.out.println("first element using element() : " + linkedList.element());//ramesh

        linkedList.addFirst("sharma");//returns void
        linkedList.addLast("khanna");
        System.out.println("linkedList = " + linkedList);

        System.out.println("peek first element : " + linkedList.peek());//retrieves first element(from front) doesn't remove
        System.out.println("peekFirst : " + linkedList.peekFirst());//same as above
        System.out.println("peekLast : " + linkedList.peekLast());//retrieves last element(from end)

        //polling
        //linkedList.poll();
        System.out.println("poll first element : " + linkedList.poll());//retrieves and removes first element
        System.out.println("pollFirst() first element : " + linkedList.pollFirst());//retrieves and removes first element
        System.out.println("pollLast() last element : " + linkedList.pollLast());//retrieves and removes last element

        System.out.println("List after removing above elements : " + linkedList);

        //offer
        //returns boolean
        linkedList.offer("rao");//adds at the end
        linkedList.offerFirst("kiran");//adds in front
        linkedList.offerLast("chopra");//adds in last
        System.out.println("List after adding/offer above elements : " + linkedList);

        //push/pop
        //“Front” of the list == “top” of the stack.
        linkedList.push("namit");//returns void - adds at the end. similar to addFirst
        System.out.println("list after pushing namit : " + linkedList);
        linkedList.push("shyam");
        String poppedElement = linkedList.pop();//removes from the end
        System.out.println("poppedElement = " + poppedElement);//shyam

        System.out.println("list after push/pop operations : " + linkedList);

        //remove-gives boolean
        linkedList.remove("binod");
        //removeFirst-removes from front
        System.out.println(linkedList.removeFirst());//namit
        //removesLast-removes from last
        System.out.println(linkedList.removeLast());//chopra

        System.out.println("list after remove operations : " + linkedList);

        System.out.println("getFirst() : " + linkedList.getFirst());
        System.out.println("getLast() : " + linkedList.getLast());

        //null allowed
        linkedList.add(null);
        System.out.println("linkedList : " + linkedList);

        //duplicate
        linkedList.add("modi");//allowed

        //sort
        //linkedList.sort(Comparator.naturalOrder());
        linkedList.sort(Comparator.nullsLast(Comparator.naturalOrder()));//sorts with null
        System.out.println("after sorting linkedList.sort() = " + linkedList);
        Collections.sort(linkedList, Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("after sorting Collections.sort() = " + linkedList);


    }
}
