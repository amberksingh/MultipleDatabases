package com.javatechie.multiple.ds.api.again;

class DBInstance {

    //private static DBInstance instance = new DBInstance();//eager
    private static DBInstance instance;//lazy

    private DBInstance() {
        System.out.println("Inside constructor");
    }

    public static DBInstance getInstanceEager() {
        return instance;
    }

    public static DBInstance getInstanceLazy() {
        if (null == instance) {
            instance = new DBInstance();
        }
        return instance;
    }
}

public class SingletonLazyEager {

    public static void main(String[] args) {

//        DBInstance instanceEager = DBInstance.getInstanceEager();
//        System.out.println("instance.hashCode = " + instanceEager.hashCode());
        DBInstance instanceLazy = DBInstance.getInstanceLazy();
        System.out.println("instanceLazy.hashCode = " + instanceLazy.hashCode());
        DBInstance instanceLazy1 = DBInstance.getInstanceLazy();
        System.out.println("instanceLazy1.hashCode = " + instanceLazy1.hashCode());
    }
}
