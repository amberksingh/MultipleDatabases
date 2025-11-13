package com.javatechie.multiple.ds.api.again;

class RunnableCounterIncrement implements Runnable {

    int counter = 0;

    @Override
    public void run() {
        counter++;
        System.out.println("Runnable counter : " + counter + " " + Thread.currentThread().getName());
    }
}

class ThreadExtension extends Thread {

    int counter = 0;

    @Override
    public void run() {
        counter++;
        System.out.println("Thread counter : " + counter + " " + Thread.currentThread().getName());
    }
}

public class MultiThreadingCounter {

    public static void main(String[] args) throws InterruptedException {

        //Runnable
        Runnable r1 = new RunnableCounterIncrement();
        //Runnable r2 = new RunnableCounterIncrement();
        //Runnable r3 = new RunnableCounterIncrement();
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        Thread t3 = new Thread(r1);

        t1.start();
        t2.start();
        t3.start();


        //Thread
        ThreadExtension te1 = new ThreadExtension();
        ThreadExtension te2 = new ThreadExtension();
        ThreadExtension te3 = new ThreadExtension();
        te1.start();
        te2.start();
        te3.start();

        t1.join();
        t2.join();
        t3.join();

        te1.join();
        te2.join();
        te3.join();

    }
}
