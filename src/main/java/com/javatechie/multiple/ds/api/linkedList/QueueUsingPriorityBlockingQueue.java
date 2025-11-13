package com.javatechie.multiple.ds.api.linkedList;

import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

//TAKE() IS DEFINED IN THE BLOCKINGQUEUE INTERFACE
//(which PriorityBlockingQueue implements).
//
//So, this method exists in classes like:
//
//ArrayBlockingQueue
//
//LinkedBlockingQueue
//
//PriorityBlockingQueue
//
//🧠 2️⃣ What does it do?
//
//TAKE() REMOVES AND RETURNS THE HEAD ELEMENT OF THE QUEUE,
//WAITING (BLOCKING) IF THE QUEUE IS EMPTY UNTIL AN ELEMENT BECOMES AVAILABLE.
//
//💡 In plain English:
//
//Imagine you’re a consumer thread waiting for tasks.
//
//If there is something in the queue → it immediately takes and returns it.
//
//If the queue is empty, the thread waits (doesn’t crash or spin) until a producer adds something.
//
//🧱 3️⃣ Example
//import java.util.concurrent.PriorityBlockingQueue;
//
//public class TakeExample {
//    public static void main(String[] args) {
//        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
//
//        // Consumer thread
//        Thread consumer = new Thread(() -> {
//            try {
//                System.out.println("Consumer waiting to take...");
//                String item = queue.take(); // will block here until item is available
//                System.out.println("Consumer got: " + item);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        // Producer thread
//        Thread producer = new Thread(() -> {
//            try {
//                Thread.sleep(2000); // simulate delay
//                queue.put("task-1");
//                System.out.println("Producer added task-1");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        consumer.start();
//        producer.start();
//    }
//}
//
//🧭 Output:
//Consumer waiting to take...
//Producer added task-1
//Consumer got: task-1
//
//Explanation:
//
//The consumer started first and called take() → queue empty → it waits.
//
//The producer added task-1 after 2 seconds → queue now non-empty → consumer wakes up → receives it immediately.
//
//✅ take() blocks the thread efficiently (no busy waiting).
//
//🧩 4️⃣ HOW IT’S DIFFERENT FROM OTHER QUEUE METHODS
//METHOD	WHAT IT DOES WHEN QUEUE IS EMPTY
//POLL()	RETURNS NULL IMMEDIATELY
//REMOVE()	THROWS NOSUCHELEMENTEXCEPTION
//TAKE()	WAITS (BLOCKS) UNTIL AN ELEMENT IS AVAILABLE
//
//So:
//
//String x = queue.take();  // waits
//String y = queue.poll();  // returns null if empty
//
//🧠 5️⃣ When do we use take()?
//
//You use take() when you have:
//
//A producer-consumer system
//
//Multiple threads producing and consuming tasks
//
//You want consumers to wait automatically until work is available
//
//Example use cases:
//
//Thread pool workers
//
//Job schedulers
//
//Background processing systems (logging, message queues, etc.)
//
//✅ 6️⃣ Summary
//Concept	Description
//Purpose	Removes and returns the head element
//Blocking behavior	Waits if queue empty
//Thread-safe	Yes
//Typical usage	Consumer thread waiting for tasks
//Throws	InterruptedException if the thread is interrupted while waiting
//
//✅ In short:
//
//take() = “Wait until something is available, then take it.”
//It’s the blocking, thread-safe way to get data from a BlockingQueue


//===========================================
//put(E element)
//→ Adds an element to the queue,
//→ Waits (blocks) if the queue is full.
//
//🧠 2️⃣ In simple English
//
//Think of a producer-consumer system:
//
//Producers add tasks into a queue.
//
//Consumers remove (take) them for processing.
//
//If the queue has no limit, put() works immediately.
//If the queue has a capacity limit (like ArrayBlockingQueue or LinkedBlockingQueue),
//then:
//
//If the queue is full, put() will pause and wait until space becomes free.
//
//It never throws an error — it just waits.
//
//🧱 3️⃣ Example 1 — Simple unbounded queue (PriorityBlockingQueue)
//import java.util.concurrent.PriorityBlockingQueue;
//
//public class PutExample {
//    public static void main(String[] args) throws InterruptedException {
//        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
//
//        queue.put("A");
//        queue.put("B");
//        queue.put("C");
//
//        System.out.println("Added 3 elements using put()");
//        System.out.println(queue);
//    }
//}
//
//
//✅ Output:
//
//Added 3 elements using put()
//[A, B, C]
//
//
//Here the queue is unbounded, so put() never waits — it behaves like add().
//
//🧱 4️⃣ Example 2 — With capacity limit (BlockingQueue)
//import java.util.concurrent.ArrayBlockingQueue;
//
//public class PutExampleBounded {
//    public static void main(String[] args) {
//        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2); // capacity = 2
//
//        Thread producer = new Thread(() -> {
//            try {
//                System.out.println("Adding 1");
//                queue.put(1);
//                System.out.println("Adding 2");
//                queue.put(2);
//                System.out.println("Adding 3 (will wait until space is free...)");
//                queue.put(3); // 🕒 waits here
//                System.out.println("Added 3 successfully!");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        Thread consumer = new Thread(() -> {
//            try {
//                Thread.sleep(2000); // simulate delay
//                System.out.println("Removing: " + queue.take()); // frees space
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        producer.start();
//        consumer.start();
//    }
//}
//
//🧭 Output (approximate)
//Adding 1
//Adding 2
//Adding 3 (will wait until space is free...)
//Removing: 1
//Added 3 successfully!
//
//✅ What happened
//
//Producer added 1 and 2 quickly.
//
//On the 3rd element, the queue was full → put(3) blocked (waited).
//
//After 2 seconds, consumer removed one → space freed → producer unblocked and added 3.
//
//🧩 5️⃣ Difference between put() and add() / offer()
//Method	Behavior when queue is full	Blocking?
//add(e)	Throws IllegalStateException	❌ No
//offer(e)	Returns false	❌ No
//put(e)	Waits until space is free	✅ Yes
//
//✅ So, in concurrent environments, put() is the safe choice — no exceptions or lost data.
//
//🧠 6️⃣ Typical usage
//
//Use put() when:
//
//You have a producer thread generating data.
//
//You want it to wait automatically when the queue is full.
//
//Example: a logging system, task scheduler, or message queue.
//
//🧾 7️⃣ Summary
//Concept	Description
//Purpose	Adds element, waits if queue is full
//Thread-safe	✅ Yes
//Throws	InterruptedException if interrupted while waiting
//Works with	All BlockingQueue types (ArrayBlockingQueue, LinkedBlockingQueue, PriorityBlockingQueue)
//Opposite method	take() — removes element, waits if empty
//
//✅ In short:
//
//put() = “Wait if necessary until there’s space, then add.”
//
//take() = “Wait if necessary until something is available, then remove.”
//
//Together, they make producer–consumer work smoothly without manual synchronization or wait()/notify().

public class QueueUsingPriorityBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>(3);
        //PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();
        //PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>(3, Comparator.reverseOrder());
        //The internal order printed by System.out.println() on a PriorityQueue is not guaranteed to be alphabetical or sorted —
        //only the removal order follows the defined priority (natural order or custom comparator).
        System.out.println("initial queue = " + queue);//order not fixed here

        Runnable producer = () -> {
            //try {
            System.out.println("Producer : waiting to add elements : " + Thread.currentThread().getName());
            //Thread.sleep(5000);
            //put returns void
            queue.put("varun");
            queue.put("golu");
            queue.put("sonu");//till here no issues as size is 3
            queue.put("modi");
            queue.put("pandey");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        };

        Runnable consumer = () -> {
            try {
                System.out.println("Consumer : waiting to take/poll elements : " + Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("value take 1 : " + queue.take());//remove golu
                System.out.println("value take 2 : " + queue.take());//remove modi
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread prod = new Thread(producer);
        Thread cons = new Thread(consumer);

        prod.start();
        cons.start();

        try {
            prod.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            cons.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("queue after both threads die : " + queue);
        System.out.println("Program ending : " + Thread.currentThread().getName());

    }
}
