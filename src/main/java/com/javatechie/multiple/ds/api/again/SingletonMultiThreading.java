package com.javatechie.multiple.ds.api.again;

class College {

    private static College instance;

    private College() {
        System.out.println("Inside constructor");
    }

    public static College getInstance() {

        if (instance == null) {
            System.out.println("Inside 1st if block " + Thread.currentThread().getName());
            synchronized (SingletonMultiThreading.class) {
                if (instance == null) {
                    System.out.println("Inside 2nd if block " + Thread.currentThread().getName());
                    instance = new College();
                }
            }
        }
        return instance;
    }

}

public class SingletonMultiThreading {

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () -> {
            College college1 = College.getInstance();
            System.out.println("college1.hashCode() = " + college1.hashCode());
        };
        Runnable r2 = () -> {
            College college2 = College.getInstance();
            System.out.println("college2.hashCode() = " + college2.hashCode());
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
