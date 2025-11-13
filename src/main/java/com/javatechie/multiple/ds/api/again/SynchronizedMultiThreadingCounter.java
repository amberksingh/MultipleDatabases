package com.javatechie.multiple.ds.api.again;

class SynchronizedCounterIncrement {

    int counter = 0;

     /*synchronized*/ void increment() {
         synchronized (this) {
             counter++;
             System.out.println("counter : " + counter + " " + Thread.currentThread().getName());
         }
     }
}

public class SynchronizedMultiThreadingCounter {

    public static void main(String[] args) throws InterruptedException {

        SynchronizedCounterIncrement counterIncrement = new SynchronizedCounterIncrement();

        Runnable r1 = () -> {
            for (int i = 0; i < 20; i++) {
                counterIncrement.increment();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable r2 = () -> {
            for (int i = 0; i < 20; i++) {
                counterIncrement.increment();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable r3 = () -> {
            for (int i = 0; i < 20; i++) {
                counterIncrement.increment();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final counter value : " + counterIncrement.counter);

    }
}
