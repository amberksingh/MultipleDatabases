package com.javatechie.multiple.ds.api.again;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        ////CopyOnWriteArrayList
        //    //Every iterator works on a snapshot (copy) of the list that existed at the time the iteration started.
        //    //
        //    //The iterator is not live (it doesn’t reflect changes made later).
        //    //
        //    //Any changes like .add() or .remove() happen on a new copy, and the iterator still uses the old copy

        //ArrayList<Integer> list = new ArrayList<>();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer val = iterator.next();//modCount check is done here
            System.out.println("val = " + val);
            if (val == 40) {
                list.add(60);
                //
                list.remove(Integer.valueOf(10));
                //exception doesn't come here as the iterator has already reached the last element
                //The cursor has moved to the end as 10 is removed meaning technically, the cursor count has reached end of list
                //And it leads to no exception because cursor thinks list has ended.and .next() is not reached
                //so it looks like it will lead to exception but no exception occurs
            }
        }
        System.out.println("list = " + list);

    }
}
