package com.javatechie.multiple.ds.api.again;

import java.io.*;

class Cache implements Serializable {

    private static Cache instance;

    private Cache() {
        System.out.println("Inside constructor");
    }

    public static Cache getInstance() {

        if (null == instance) {
            instance = new Cache();
        }
        return instance;
    }

    //Deserialization creates a new instance → new reference → new hashCode().
    //
    //readResolve() returns the already existing instance, which has the same identity and hashcode.
    //
    //So readResolve() ensures the singleton instance remains consistent across serialization.
    protected Object readResolve() {

        return instance;
    }

}

public class SingletonSerialization {

    public static void main(String[] args) {

        Cache instance1 = Cache.getInstance();
        System.out.println("instance1.hashCode() = " + instance1.hashCode());

        //Serialization
        try (FileOutputStream outputStream = new FileOutputStream("files.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(instance1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Deserialization
        try (FileInputStream inputStream = new FileInputStream("files.txt");
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            Cache cache = (Cache) objectInputStream.readObject();
            System.out.println("cache.hashCode() = " + cache.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
