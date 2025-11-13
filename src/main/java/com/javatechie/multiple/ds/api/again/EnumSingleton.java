package com.javatechie.multiple.ds.api.again;

enum Month {
    JANUARY("jan"),
    FEBRUARY("feb"),
    MARCH("mar"),
    APRIL("apr"),
    MAY("may"),
    JUNE("jun");

    String monthName;

    int value;

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    Month(String monthName) {
        this.monthName = monthName;
    }
}

public class EnumSingleton {

    public static void main(String[] args) {

//        Month april = Month.APRIL;
//        System.out.println("april.hashCode() = " + april.hashCode());

        Month january1 = Month.JANUARY;
        january1.setValue(10);
        System.out.println("january1 = " + january1.hashCode());
        System.out.println("january1.value = " + january1.getValue());//10

        Month january2 = Month.JANUARY;
        System.out.println("january2 = " + january2.hashCode());//
        System.out.println("january2.value = " + january2.getValue());//10

        Month feb = Month.valueOf("FEBRUARY");//searches for caps JANUARY
        feb.setValue(20);
        System.out.println("feb.hashCode() = " + feb.hashCode());
        System.out.println("feb.vaue = " + feb.getValue());

        for (Month month : Month.values()) {
            System.out.println(month);
            System.out.println(month.monthName);
            System.out.println(month.ordinal());
        }

    }
}
