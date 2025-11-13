package com.javatechie.multiple.ds.api.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//===============if class is not final. then in future someone might change and add a constructor and extend
// and try to modify it. so final is better to avoid future mistakes.

//class MutableStudent extends Student {
//    public MutableStudent(int id, String name, List<String> subjects) {
//        super(id, name, subjects);
//    }
//
//    public void changeName(String newName) {
//        this.name = newName; // Works because name not final
//    }
//}
//MutableStudent s = new MutableStudent(1, "Amber", List.of("Math"));
//s.changeName("John");
//System.out.println(s.getName()); // Now changed to John

final class Student {

    private final int id;
    private final String name;
    private final int age;
    private final List<String> subjects;
    private final double marks;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getSubjects() {
        //return subjects;

        return new ArrayList<>(subjects);// ----1
        //below throws java.lang.UnsupportedOperationException
        //use 1 or 2
        //return Collections.unmodifiableList(subjects);//-----2
    }

    public double getMarks() {
        return marks;
    }

    //suppose added by new developer
    public void resetSubjects() {
        // OOPS - reassigning the reference entirely
        //this.subjects = new ArrayList<>();//gives compilation error here if subjects is final
        //this.id = 20;//gives compilation error here if id final
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", subjects=" + subjects +
                ", marks=" + marks +
                '}';
    }

    Student(StudentBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.marks = builder.marks;
        this.subjects = builder.subjects != null ?
                new ArrayList<>(builder.subjects) :
                new ArrayList<>();
    }

    static class StudentBuilder {

        //final not needed here
        private int id;//mandatory field
        private String name;//mandatory field
        private int age;
        private List<String> subjects;
        private double marks;

        Student build() {
            return new Student(this);
        }

        StudentBuilder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        //mandatory field in constructor
//        StudentBuilder id(int id) {
//            this.id = id;
//            return this;
//        }
//
//        StudentBuilder name(String name) {
//            this.name = name;
//            return this;
//        }

        StudentBuilder age(int age) {
            this.age = age;
            return this;
        }

        StudentBuilder subjects(List<String> subjects) {
            this.subjects = subjects;
            return this;
        }

        StudentBuilder marks(double marks) {
            this.marks = marks;
            return this;
        }

    }
}


public class BuilderPatternStudentDemo {

    public static void main(String[] args) {

        List<String> lambaSubs = new ArrayList<>();
        lambaSubs.add("physics");

        Student lamba = new Student.StudentBuilder(10, "lamba")
                .age(27)
                .marks(80d)
                .subjects(lambaSubs)
                .build();
        System.out.println("initial lamba = " + lamba);

        lambaSubs.add("chemistry");//has no impact after making change in constructor of Student

        System.out.println("simulating change in subjects list  : lamba = " + lamba);

        //can create issue similar to above const case
        //fixed after new instance similar to constructor
        lamba.getSubjects().add("maths");//no impact

        System.out.println("lamba = " + lamba);

        lamba.resetSubjects();

        System.out.println("lamba = " + lamba);
    }
}
