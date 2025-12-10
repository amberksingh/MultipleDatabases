package com.javatechie.multiple.ds.api.programs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class PerfumeClone implements Cloneable {

    private static PerfumeClone perfumeClone;

    private PerfumeClone() {
        System.out.println("perfumeClone constructor called.. ");
        if (perfumeClone != null) {
            throw new RuntimeException("Use getInstance() method to create Singleton!");
        }
    }

    public static PerfumeClone getInstance() {
        System.out.println("perfumeClone getInstance() called ");
        if (perfumeClone == null) {
            perfumeClone = new PerfumeClone();
        }
        return perfumeClone;
    }

    //PREVENT CLONING
    //“Object.clone() is protected, so subclasses can call it only by overriding it.
    //Even though every class extends Object, protected methods of a superclass are not accessible on subclass
    //instances unless the subclass exposes them.
    //Therefore, you cannot call clone() unless the class overrides it and makes it accessible.”
    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("perfumeClone clone() called ");
        //return super.clone();  // ❌ creates a new instance. //gives diff obj/hashcode
        throw new CloneNotSupportedException("Cannot clone Singleton");
        //return perfumeClone;
    }
}

public class SingletonClone {

    public static void main(String[] args) throws CloneNotSupportedException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

//        Constructor<PerfumeClone> constructor = PerfumeClone.class.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        PerfumeClone reflectionPerfumeClone = constructor.newInstance();
//        System.out.println("reflectionPerfumeClone hashCode = " + reflectionPerfumeClone.hashCode());

        PerfumeClone perfumeClone = PerfumeClone.getInstance();
        System.out.println("perfumeClone hashCode using getInstance() = " + perfumeClone.hashCode());

        PerfumeClone perfumeClone1 = (PerfumeClone) perfumeClone.clone();
        System.out.println("perfumeClone1 hashCode using clone() = " + perfumeClone1.hashCode());
    }
}
