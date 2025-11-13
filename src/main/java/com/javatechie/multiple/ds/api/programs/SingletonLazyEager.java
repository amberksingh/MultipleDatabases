package com.javatechie.multiple.ds.api.programs;

import java.util.Objects;

class World {

    private static World world;// = new World();//eager if object always ready/created

    private World() {
        System.out.println("Inside constructor.");
    }

    public static World getInstance() {
        System.out.println("Inside getInstance()..");
        if (Objects.isNull(world)) {//lazy to check if object is null
            world = new World();
        }
        return world;
    }
}

public class SingletonLazyEager {

    public static void main(String[] args) {
        World world = World.getInstance();
        System.out.println("world hashcode = " + world.hashCode());
        World world1 = World.getInstance();
        System.out.println("world1 hashcode = " + world1.hashCode());
    }
}
