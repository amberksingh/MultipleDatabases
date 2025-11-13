package com.javatechie.multiple.ds.api.programs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String gender;
    private int age;
    private String department;

    private double salary;

    // ✅ Default constructor
    public Employee() {
    }

    // ✅ Parameterized constructor (with department)
    public Employee(String name, String gender, int age, String department, double salary) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // ✅ Getters and setters
    public String getName() {
        return name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // ✅ Updated toString()
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}

public class EmployeeStuff {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("virat", "male", 47, "math", 5000));
        employees.add(new Employee("raghu", "male", 20, "science", 6000));
        employees.add(new Employee("priya", "female", 33, "science", 3000));
        employees.add(new Employee("ashok", "male", 28, "math", 8000));
        employees.add(new Employee("shobha", "female", 40, "history", 9000));
        employees.add(new Employee("sachin", "male", 18, "english", 1000));
        employees.add(new Employee("rao", "male", 35, "english", 999));
        employees.add(new Employee("kalpana", "female", 50, "sanskrit", 1500));

        //Find list of employees whose name starts with alphabet A
        employees.stream()
                .filter(employee -> employee.getName().toLowerCase().startsWith("a"))
                .forEach(e -> System.out.println("employe with names start with A = " + e));

        //Group The employees By gender
        Map<String, List<Employee>> employeesByGender = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender
                        )
                );
        System.out.println("employeesByGender = " + employeesByGender);

        //Group The employees By gender mapped to only names
        Map<String, List<String>> collect = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.mapping(
                                        Employee::getName,
                                        Collectors.toList()
                                )
                        )
                );
        System.out.println("collect = " + collect);
        
        //Find the max age of employees
        int maxAgeEmployee = employees.stream()
                .map(Employee::getAge)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        System.out.println("maxAgeEmployee = " + maxAgeEmployee);

        //2nd way
        Employee employee = employees.stream()
                .max(Comparator.comparing(Employee::getAge))
                .orElse(new Employee());
        System.out.println("max age of employee = " + employee.getAge());
        
        //3rd way
        Integer maxAgeEmp = employees.stream()
                .max(Comparator.comparingInt(Employee::getAge))
                .map(Employee::getAge)
                .orElse(0);
        System.out.println("maxAgeEmp = " + maxAgeEmp);
        
        //4th way
        Integer maxAgeUsingSort = employees.stream()
                .sorted(Comparator.comparing(Employee::getAge, Comparator.reverseOrder()))
                .findFirst()
                .map(Employee::getAge)
                .orElse(0);
        System.out.println("maxAgeUsingSort = " + maxAgeUsingSort);

        //5th way
        Employee employee1 = employees.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparing(Employee::getAge)))
                .orElse(new Employee());
        System.out.println("max age using BinaryOperator.maxBy(Comparator.comparing(Employee::getAge)) = " + employee1.getAge());

        //Find the average age of male and female employee
        Map<String, Double> avgAgeByGender = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getGender,
                                Collectors.averagingInt(Employee::getAge)
                        )
                );
        System.out.println("avgAgeByGender = " + avgAgeByGender);

        //with partitioningBy
        Map<Boolean, Double> male = employees.stream()
                .collect(
                        Collectors.partitioningBy(
                                e -> e.getGender().equalsIgnoreCase("male"),
                                Collectors.averagingInt(Employee::getAge)
                        )
                );
        System.out.println("male avg age = " + male.get(true));
        System.out.println("female avg age = " + male.get(false));

        //Find the employee who has second lowest salary
        Employee employee2 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .skip(1)
                .findFirst()
                .orElse(new Employee());
        System.out.println("employee with second lowest salary = " + employee2.getSalary());

        //Find the department who is having maximum number of employee
        System.out.println("Department with highest employees : ");
        employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                //.max(Comparator.comparing(Map.Entry::getValue))
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.println(entry.getKey()));

        //Find the Employees who are from math department and sort them by their names
        List<Employee> math = employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("math"))
                .sorted(Comparator.comparing(Employee::getName))
                .toList();
        System.out.println("math department employees sorted by name ASC order = " + math);

        //Find the highest salary in each department
        Map<String, Double> collect1 = employees.stream()
                .collect(
                        Collectors.toMap(
                                Employee::getDepartment,
                                Employee::getSalary,
                                //BinaryOperator.maxBy(Comparator.comparingDouble(e -> e))
                                Double::max//IMPORTANT
                        )
                );
        System.out.println("highest salary dept wise : "+collect1);


    }
}
