package com.javatechie.multiple.ds.api.design;

import java.util.ArrayList;
import java.util.List;

interface Channel {
    void update(String news);
}

class NewsAgency {

    List<Channel> channels = new ArrayList<>();

    void addObservers(Channel channel) {
        channels.add(channel);
    }

    void updateNews(String news) {
        for (Channel channel : channels) {
            channel.update(news);
        }
    }

}

class Ani implements Channel {

    String news;

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public void update(String news) {
        this.news = news;
    }
}
class Bbc implements Channel {

    String news;

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
    @Override
    public void update(String news) {
        this.news = news;
    }
}

public class ObserverPatternDemo {

    public static void main(String[] args) {

        Channel ani = new Ani();
        Channel bbc = new Bbc();

        NewsAgency newsAgency = new NewsAgency();
        newsAgency.addObservers(ani);
        newsAgency.addObservers(bbc);

        newsAgency.updateNews("Operation Sindoor starts...");
        System.out.println("Bbc new :"+((Bbc)bbc).getNews());
        System.out.println("Ani new :"+((Ani)ani).getNews());
    }
}
