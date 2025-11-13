package com.javatechie.multiple.ds.api.programs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.*;
import java.util.Objects;

class Subject implements Serializable {

    private static Subject subject;

    private Subject() {
        System.out.println("Inside constructor..");
    }

    public static Subject getInstance() {
        if (Objects.isNull(subject)) {
            System.out.println("Inside getInstance()..");
            subject = new Subject();
        }
        return subject;
    }

    //Deserialization creates a new instance → new reference → new hashCode().
    //
    //readResolve() returns the already existing instance, which has the same identity and hashcode.
    //
    //So readResolve() ensures the singleton instance remains consistent across serialization.
    protected Object readResolve() {

        return subject;
    }
}

public class SingletonSerializationDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Subject subject1 = Subject.getInstance();
        System.out.println("subject1 = " + subject1.hashCode());
//        Subject subject2 = Subject.getInstance();
//        System.out.println("subject2 = " + subject2.hashCode());

        //SERIALIZATION
        FileOutputStream outputStream = new FileOutputStream("files.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(subject1);
        objectOutputStream.flush();
        objectOutputStream.close();

        //DESERIALIZATION
        FileInputStream inputStream = new FileInputStream("files.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Subject subject2 = (Subject) objectInputStream.readObject();//new object after deserialization
        System.out.println("subject2 = " + subject2.hashCode());


    }
}
