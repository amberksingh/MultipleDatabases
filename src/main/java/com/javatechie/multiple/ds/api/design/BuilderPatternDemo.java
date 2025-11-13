package com.javatechie.multiple.ds.api.design;

class User {

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
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

    private final String name;//mandatory, so final
    private final Long id;//mandatory, so final
    private String gender;
    private Integer age;
    private Double salary;

    public User(UserBuilder userBuilder) {
        this.age = userBuilder.age;
        this.id = userBuilder.id;
        this.gender = userBuilder.gender;
        this.salary = userBuilder.salary;
        this.name = userBuilder.name;
    }

    public static class UserBuilder {

        //Not necessary to make name and id final — the builder is a temporary mutable object
        // used to collect values before creating a User.
        private  String name;// okay to keep final here, since mandatory
        private  Long id;// okay to keep final here too
        private String gender;
        private Integer age;
        private Double salary;

        UserBuilder(String name, Long id) {
            this.name = name;
            this.id = id;
        }

        User build() {
            return new User(this);
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

        UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        UserBuilder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        UserBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
    }

}

public class BuilderPatternDemo {

    public static void main(String[] args) {

        User rahul = new User.UserBuilder("rahul", 10L)
                .age(20)
                .salary(25000d)
                .gender("male")
                .build();
        //rahul.resetId();
        System.out.println("rahul = " + rahul);

    }

}
