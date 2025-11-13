package com.javatechie.multiple.ds.api.again;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Terrace {

    private static Terrace instance;

    private Terrace() {
        System.out.println("Inside constructor");
        if (instance != null)
            throw new RuntimeException("Singleton ...can't create new object");
    }

    public static Terrace getInstance() {

        if (instance == null) {
            instance = new Terrace();
        }
        return instance;
    }
}

public class SingletonReflection {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {

        Terrace instance = Terrace.getInstance();
        System.out.println("instance.hashCode() = " + instance.hashCode());

        //reflection
        Constructor<Terrace> declaredConstructors = Terrace.class.getDeclaredConstructor();
        declaredConstructors.setAccessible(true);

        Terrace instance1 = declaredConstructors.newInstance();
        System.out.println("instance1.hashCode() = " + instance1.hashCode());

    }
}
