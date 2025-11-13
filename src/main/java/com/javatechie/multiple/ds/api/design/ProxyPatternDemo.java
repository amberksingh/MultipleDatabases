package com.javatechie.multiple.ds.api.design;

import java.util.ArrayList;

interface Internet {
    void connectTo(String domain);
}

class RealInternet implements Internet {

    @Override
    public void connectTo(String domain) {
        System.out.println("Successfully Connected to : " + domain);
    }
}

class ProxyInternet implements Internet {

    static ArrayList<String> bannedSites = new ArrayList();

    Internet internet = new RealInternet();

    static {
        bannedSites.add("reddit.com");
        bannedSites.add("twitter.com");
        bannedSites.add("facebook.com");
        bannedSites.add("instagram.com");
        bannedSites.add("torrent.com");
    }

    @Override
    public void connectTo(String domain) {
        if (bannedSites.contains(domain)) {
            System.out.println("Can't connect to " + domain);
            throw new RuntimeException("Banned site connection attempted...");
        }
        internet.connectTo(domain);
    }
}

public class ProxyPatternDemo {

    public static void main(String[] args) {

        try {
            Internet internet = new ProxyInternet();
            //internet.connectTo("twitter.com");
            internet.connectTo("java.com");
            internet.connectTo("google.com");
            internet.connectTo("reddit.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
