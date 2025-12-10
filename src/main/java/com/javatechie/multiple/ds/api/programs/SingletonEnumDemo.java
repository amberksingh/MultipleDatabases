package com.javatechie.multiple.ds.api.programs;

//1. What is an enum?
//
//enum = enumeration, a special Java type for defining a fixed set of constants.
//
//Example: days of the week, directions, traffic lights.
//
//Enums are more powerful than simple constants because they are full-fledged classes (with fields, methods, constructors).
//
//2. Basic example
//enum Day {
//    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
//}
//
//public class EnumDemo {
//    public static void main(String[] args) {
//        Day today = Day.MONDAY;
//        System.out.println(today);  // prints "MONDAY"
//
//        // loop over all enum values
//        for (Day d : Day.values()) {
//            System.out.println(d);
//        }
//    }
//}
//
//Output
//MONDAY
//MONDAY
//TUESDAY
//WEDNESDAY
//THURSDAY
//FRIDAY
//SATURDAY
//SUNDAY
//
//3. Enum is more than constants
//
//EACH ENUM CONSTANT IS ACTUALLY A PUBLIC STATIC FINAL OBJECT OF THE ENUM TYPE.
//SO DAY.MONDAY IS AN INSTANCE OF DAY.
//
//4. Enums with fields and methods
//enum Color {
//    RED("#FF0000"),
//    GREEN("#00FF00"),
//    BLUE("#0000FF");
//
//    private final String hex;
//
//    // constructor (must be private)
//    Color(String hex) {
//        this.hex = hex;
//    }
//
//    public String getHex() {
//        return hex;
//    }
//}
//
//public class EnumDemo2 {
//    public static void main(String[] args) {
//        System.out.println(Color.RED.getHex());   // #FF0000
//    }
//}
//
//5. Useful built-in methods
//
//Every enum has:
//
//values() → returns an array of all constants.
//
//valueOf(String) → get enum constant by name.
//
//ordinal() → position (0-based index).
//
//Example:
//
//Day d = Day.valueOf("FRIDAY");
//System.out.println(d);          // FRIDAY
//System.out.println(d.ordinal()); // 4
//
//6. Enums in switch statements
//switch (today) {
//    case MONDAY -> System.out.println("Start of week!");
//    case FRIDAY -> System.out.println("Almost weekend!");
//    case SUNDAY -> System.out.println("Rest day!");
//    default -> System.out.println("Midweek day");
//}
//
//
//✅ Summary:
//
//enum = type-safe way to define fixed constants.
//
//Enums can have fields, methods, and constructors.
//
//Each enum constant is a singleton instance.
//
//Use values(), valueOf(), ordinal() for utility.
//
//Useful in switch cases, collections, and modeling fixed sets.
enum Singleton {

    //Constructor argument strings
    //
    //"monday", "tuesday", … "friday"
    //
    //These are just string values you passed into the enum constructor.
    //
    //They are not used for valueOf. They are simply data you chose to associate with the constant.
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");
    int value;
    String dayName;


    //Runs once per constant, at class-load time.
    //
    //So when the enum class is first referenced, you’ll see:
    //Singleton constructor = monday
    //Singleton constructor = tuesday
    //...
    Singleton(String dayName) {
        this.dayName = dayName;
        System.out.println("Singleton constructor = " + dayName);
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

}

public class SingletonEnumDemo {

    public static void main(String[] args) {

        Singleton monday1 = Singleton.MONDAY;
        Singleton monday2 = Singleton.MONDAY;
        System.out.println("MONDAY1 = " + monday1.hashCode());//same as below
        System.out.println("MONDAY2 = " + monday2.hashCode());//same as above

        monday1.setValue(10);
        System.out.println("monday1.getValue() = " + monday1.getValue());//10
        System.out.println("monday2.getValue() = " + monday2.getValue());//10


        monday2.setValue(20);
        System.out.println("monday1.getValue() = " + monday1.getValue());//20
        System.out.println("monday2.getValue() = " + monday2.getValue());//20

        //
        Singleton tuesday = Singleton.TUESDAY;
        Singleton wednesday = Singleton.WEDNESDAY;
        System.out.println("tuesday = " + tuesday.hashCode());//diff
        System.out.println("wednesday = " + wednesday.hashCode());//diff

        tuesday.setValue(40);
        System.out.println("tuesday.getValue() = " + tuesday.getValue());//40
        System.out.println("wednesday.getValue() = " + wednesday.getValue());//0

        wednesday.setValue(50);
        System.out.println("tuesday.getValue() = " + tuesday.getValue());//40
        System.out.println("wednesday.getValue() = " + wednesday.getValue());//50

        for (Singleton day : Singleton.values()) {
            System.out.println("day.constant enum name = " + day);
            System.out.println("day.ordinal = " + day.ordinal());
            System.out.println("day.dayName = " + day.dayName);
        }

        Singleton friday = Singleton.valueOf("FRIDAY");
        //searches for caps "FRIDAY" not constructor args small case string
        //FRIDAY should be in caps here to match enum COnstant not constructor values
        //If you write "friday" (lowercase), you’ll get:
        //
        //Exception in thread "main" java.lang.IllegalArgumentException: No enum constant Singleton.friday
        System.out.println("friday = " + friday);
        System.out.println("friday hashcode = " + friday.hashCode());
        System.out.println("friday.value = " + friday.value);
        System.out.println("friday.ordinal = " + friday.ordinal());
        System.out.println("friday.dayName = " + friday.dayName);


    }
}
