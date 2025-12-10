package com.javatechie.multiple.ds.api.design;

import java.util.ArrayList;
import java.util.List;

//class MutableUser extends User1 {
//    private String newName;
//
//    public MutableUser(String name) {
//        super(name);
//    }
//
//    @Override
//    public String getName() {
//        return newName == null ? super.getName() : newName;
//    }
//
//    public void setNewName(String newName) {
//        this.newName = newName;
//    }
//
//
//    //User u = new MutableUser("Rahul");((MutableUser)u).
//
//    //setNewName("Hacked!");
//    //System.out.println(u.getName()); // "Hacked!"
//}

/*final*/ class User1 {

    @Override
    public String toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", hobbies=" + hobbies +
                '}';
    }

    //this can change id if not final. so make id and name or mandatory field as final
//    public void resetId() {
//        this.id = 0L;  // ✅ allowed if not final
//    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }

    public List<String> getHobbies() {
        //return hobbies; // ⚠️ exposes internal list
        return new ArrayList<>(hobbies);//deep copy or copy of list. not changing the original object list
    }

    private final String name;//mandatory, so final
    private final Long id;//mandatory, so final
    private final String gender;
    private final Integer age;
    private final Double salary;
    private final List<String> hobbies;

    public User1(UserBuilder1 userBuilder1) {
        this.age = userBuilder1.age;
        this.id = userBuilder1.id;
        this.gender = userBuilder1.gender;
        this.salary = userBuilder1.salary;
        this.name = userBuilder1.name;
        //this.hobbies = userBuilder1.hobbies;//can be changed by reference, so deep copy
        this.hobbies = userBuilder1.hobbies != null
                ? new ArrayList<>(userBuilder1.hobbies)
                : List.of();
    }

    public static class UserBuilder1 {

        //Not necessary to make name and id final — the builder is a temporary mutable object
        // used to collect values before creating a User.
        private String name;// okay to keep final here, since mandatory
        private Long id;// okay to keep final here too
        private String gender;
        private Integer age;
        private Double salary;
        private List<String> hobbies;

        UserBuilder1(String name, Long id) {
            this.name = name;
            this.id = id;
        }

        User1 build() {
            return new User1(this);
        }

//        UserBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        UserBuilder id(Long id) {
//            this.id = id;
//            return this;
//        }

        UserBuilder1 age(Integer age) {
            this.age = age;
            return this;
        }

        UserBuilder1 salary(Double salary) {
            this.salary = salary;
            return this;
        }

        UserBuilder1 gender(String gender) {
            this.gender = gender;
            return this;
        }

        UserBuilder1 hobbies(List<String> hobbies) {
            this.hobbies = hobbies;
            return this;
        }
    }

}

public class ImmutableClassDemo {

    public static void main(String[] args) {

        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("reading");

        User1 rahul = new User1.UserBuilder1("rahul", 10L)
                .age(20)
                .salary(25000d)
                .gender("male")
                .hobbies(hobbies)
                .build();
        System.out.println("rahul before = " + rahul);//reading


        rahul.getHobbies().add("coding");
        System.out.println("rahul after = " + rahul);//[reading, coding]}. so made changes as below

        //public List<String> getHobbies() {
        //        //return hobbies; // ⚠️ exposes internal list
        //        return new ArrayList<>(hobbies);//deep copy or copy of list. not changing the original object list
        //    }


        hobbies.add("streaming");
        System.out.println("rahul after = " + rahul);//[reading, streaming]

        //this.hobbies = userBuilder1.hobbies;//can be changed by reference, so deep copy
        //hobbies.add("streaming"); this can change the list due to same reference
        //so, fixed it by :
        //this.hobbies = userBuilder1.hobbies != null
        //                ? new ArrayList<>(userBuilder1.hobbies)
        //                : List.of();

        //final class scenario
//        User1 u = new MutableUser("Rahul");
//        ((MutableUser) u).setNewName("Hacked!");
//        System.out.println(u.getName()); // "Hacked!"
    }
}
