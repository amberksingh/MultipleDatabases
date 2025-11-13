package com.javatechie.multiple.ds.api.again;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapDemo {

    //🔹 1. Thread Safety
    //
    //Normal HashMap:
    //
    //Not thread-safe.
    //
    //If multiple threads access a HashMap concurrently, and at least one of the threads modifies it,
    // it must be synchronized externally.
    //
    //Without proper synchronization, operations like put(), get(), or remove() may cause unpredictable behavior
    // (like data corruption or ConcurrentModificationException).
    //
    //Collections.synchronizedMap(map):
    //
    //Returns a thread-safe wrapper around the given map.
    //
    //Synchronizes each method (put(), get(), remove(), etc.) internally using the map object as the lock.
    //
    //Multiple threads can use it safely for individual operations.

    //Two puts at the same time → ❌ not possible.
    //
    //Thread A must release the lock before Thread B can enter put().
    //
    //One get and one put at the same time → ❌ not possible.
    //
    //Both use the same lock, so they execute one after the other.
    //
    //One remove and one put/get → also ❌, since remove is also synchronized on the same lock.

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        //Map<Integer, String> map = new HashMap<>();

        Runnable write = () -> {
            //synchronized (map) {
                for (int i = 0; i < 100; i++) {
                    map.put(i, "value" + i);
                    System.out.println("added : " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            //}
        };
        Runnable remove = () -> {
            //synchronized (map) {
                for (int i = 0; i < 100; i++) {
                    map.remove(i);
                    System.out.println("removed : " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            //}
        };

        Thread put = new Thread(write);
        Thread get = new Thread(remove);

        put.start();
        get.start();

        put.join();
        get.join();

        System.out.println("map size at the end : " + map.size());

    }
}
