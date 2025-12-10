package com.javatechie.multiple.ds.api.programs;

import java.util.Objects;

class SingletonStuff {

    private static SingletonStuff singletonStuff;

    private SingletonStuff() {
        System.out.println("singletonStuff constructor called.." + Thread.currentThread().getName());
    }

    public static SingletonStuff getInstance() {
        if (Objects.isNull(singletonStuff)) {
            System.out.println("Inside 1st if condition. SingletonStuff object null." + Thread.currentThread().getName());
            synchronized (SingletonStuff.class) {
                System.out.println("Inside synchronized block :" + Thread.currentThread().getName());
                if (Objects.isNull(singletonStuff)) {
                    System.out.println("Inside 2nd if condition. SingletonStuff object null." + Thread.currentThread().getName());
                    singletonStuff = new SingletonStuff();
                }
            }
            return singletonStuff;
        }
        return singletonStuff;
    }
}

public class SingletonMultiThread {

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () -> {
            SingletonStuff instance = SingletonStuff.getInstance();
            System.out.println("instance hashcode = " + instance.hashCode() + " " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Main thread :" + Thread.currentThread().getName());
    }
}
