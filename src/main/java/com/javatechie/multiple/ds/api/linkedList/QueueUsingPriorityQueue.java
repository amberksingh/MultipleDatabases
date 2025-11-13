package com.javatechie.multiple.ds.api.linkedList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueUsingPriorityQueue {

    //🧩 1️⃣ Common part

    //Both are queues, meaning —

    //You add items at one end and remove them from the other (FIFO — First In, First Out).

    //======BUT THE DIFFERENCE IS HOW THEY DECIDE WHICH ELEMENT COMES OUT FIRST.====

    //🧠 2️⃣ Key idea    difference
    //Implementation	How it decides who comes out first
    //LinkedList	    Order of insertion (FIFO – first added → first removed)
    //PriorityQueue	    Based on priority (natural order or custom comparator), not insertion order

    //🧱 3️⃣ Example 1 — LinkedList (Normal Queue)
    //Queue<String> queue = new LinkedList<>();
    //queue.add("ram");
    //queue.add("mohan");
    //queue.add("shyam");

    //System.out.println(queue);      // [ram, mohan, shyam]
    //System.out.println(queue.remove()); // removes "ram" (first added)
    //System.out.println(queue);      // [mohan, shyam]

    //✅ Behavior:

    //Works like a normal line at a shop.

    //First person who comes → served first.

    //Simple FIFO (first in, first out).

    //🧩 4️⃣ Example 2 — PriorityQueue (Priority-based Queue)
    //Queue<String> queue = new PriorityQueue<>();
    //queue.add("ram");
    //queue.add("mohan");
    //queue.add("shyam");

    //System.out.println(queue);        // ORDER *NOT GUARANTEED*
    //System.out.println(queue.remove()); // removes "mohan"
    //System.out.println(queue);        // REMAINING ORDER MAY DIFFER

    //❓Why “mohan” came first?

    //Because PriorityQueue uses natural order for strings — alphabetical.
    //
    //"MOHAN" < "RAM" < "SHYAM"

    //So the smallest (alphabetically) is removed first.

    //✅ Behavior:

    //Think of it like a hospital emergency room —
    //the patient with the highest priority (most serious case) is treated first,
    //even if they came later.

    //🧩 5️⃣ Summary in plain words
    //Feature	                        LinkedList (Normal Queue)	        PriorityQueue (Priority Queue)
    //Order rule	                    FIFO (first come, first served)	    Based on natural or custom priority
    //Example use	                    Waiting line, print jobs	Task    scheduling, job priority
    //Removal	                        Removes element added earliest	    Removes smallest (or highest-priority)
    //Under the hood	                Doubly linked list	                Binary heap
    //Order guaranteed when printing?	Yes (insertion order)	            NO (PRIORITY-BASED ORDER)

    //✅ 6️⃣ One-line analogy

    //LinkedList → 🏪 Normal queue — first person in line gets served first.

    //PriorityQueue → 🚑 Emergency queue — most important case goes first, not by arrival order.

    //so it is not fixed that alphabetical order will be followed while sout() without comparataor lets assume?
    //ChatGPT said:

    //Exactly ✅ — great observation.

    //When you do this:

    //Queue<String> queue = new PriorityQueue<>();
    //queue.add("ram");
    //queue.add("mohan");
    //queue.add("shyam");

    //System.out.println(queue);


    //👉 No, it is not fixed that the output (toString() order) will be strictly alphabetical (or sorted).

    //🧠 Why?

    //A PRIORITYQUEUE INTERNALLY USES A BINARY HEAP —
    //IT ONLY GUARANTEES THAT THE HEAD ELEMENT (THE NEXT ONE TO REMOVE) IS THE SMALLEST (OR HIGHEST-PRIORITY),
    //BUT THE REST OF THE INTERNAL ELEMENTS CAN BE IN ANY HEAP ORDER, NOT SORTED ORDER.

    //So you might see:

    //[mohan, ram, shyam]


    //or

    //[mohan, shyam, ram]


    //depending on the internal heap structure.

    //✅ However, when you keep removing elements:

    //WHILE (!QUEUE.ISEMPTY()) {
    //    SYSTEM.OUT.PRINTLN(QUEUE.REMOVE());
    //}


    //Output will be:

    //mohan
    //ram
    //shyam


    //👉 That follows the natural (alphabetical) order.
    
    public static void main(String[] args) {

        //Queue<String> priorityQueue = new PriorityQueue<>();//alphabetic by default but only guaranteed while removing
        Queue<String> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
        //PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
        //Queue<String> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.add("varun");
        priorityQueue.add("arun");
        priorityQueue.add("kumar");
        priorityQueue.add("ramesh");
        priorityQueue.add("elizabeth");
        priorityQueue.add("grover");
        priorityQueue.add("salman");
        //The internal order printed by System.out.println() on a PriorityQueue is not guaranteed to be alphabetical or sorted —
        //only the removal order follows the defined priority (natural order or custom comparator).
        System.out.println("priorityQueue = " + priorityQueue);

        //remove will depend upon comparator used
        //if Comparator.naturalOrder() - arun
        System.out.println("remove : "+priorityQueue.remove());//arun -order followed for removing element from front

        //Why? — The reasoning
        //
        //Because PriorityQueue always needs to compare elements to maintain their priority order.
        //
        //It uses natural ordering (like compareTo) or a custom Comparator that you provide.
        //
        //Example of what happens internally:
        //
        //if (a.compareTo(b) > 0) {
        //   // arrange them
        //}
        //
        //
        //👉 IF ONE OF THOSE ELEMENTS IS NULL,
        //THEN A.COMPARETO(B) WILL THROW A NULLPOINTEREXCEPTION — BECAUSE YOU CAN’T COMPARE NULL TO ANYTHING.
        //
        //SO THE DESIGNERS OF JAVA DECIDED:
        //
        //“LET’S NOT EVEN ALLOW NULL IN PRIORITYQUEUE — IT MAKES NO SENSE TO HAVE AN UN-COMPARABLE ITEM.”

        //========PRIORITYQUEUE.ADD(NULL);//NOT ALLOWED SIMILAR TO TREESET AND TREEMAP KEY======
        System.out.println("polled : "+priorityQueue.poll());//elizabeth if Comparator.naturalOrder()

        //PRIORITYQUEUE ALLOWS DUPLICATES BECAUSE ITS GOAL IS ORDERING BY PRIORITY, NOT UNIQUENESS.
        priorityQueue.offer("grover");//DUPLICATE-ALLOWED
        System.out.println("priorityQueue = " + priorityQueue);

        System.out.println("elements using iterator/order not guaranteed : ");
        Iterator<String> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("removing elements . order guaranteed here: ");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove() + " ");
        }

    }
}
