package com.javatechie.multiple.ds.api.again;

import java.util.Scanner;
import java.util.stream.Stream;

//Number	Calculation	Armstrong?
//370	3³ + 7³ + 0³ = 27 + 343 + 0 = 370	✅ Yes
//371	3³ + 7³ + 1³ = 27 + 343 + 1 = 371	✅ Yes
//9474	9⁴ + 4⁴ + 7⁴ + 4⁴ = 9474	✅ Yes
//123	1³ + 2³ + 3³ = 36 ≠ 123	❌ No

public class ArmstrongCheck {

    public static void main(String[] args) {

        System.out.println("Enter number : ");//153 -> 1^3 + 5^3 + 3^3 = 153
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        //String input = sc.next();


        //1st way
        String num = String.valueOf(number);
        int length = num.length();
        double sum = Stream.of(num.split(""))
                .map(Integer::parseInt)
                .mapToDouble(s -> Math.pow(s, length))
                .sum();
        System.out.println("sum = " + sum);
        if (sum == number)
            System.out.println("Armstrong number using IntStream");
        else
            System.out.println("NOT Armstrong number using IntStream");

        //chars
        Double sum1 = num.chars()
                .map(Character::getNumericValue)
                .mapToObj(x -> Math.pow(x, length))
                .reduce(0d, Double::sum);

        if (sum1 == number)
            System.out.println("Armstrong number using getNumericValue");
        else
            System.out.println("NOT Armstrong number using getNumericValue");

        int sumVal = 0;
        int copyNumber = number;
        while (number > 0) {
            int r = number % 10;//3
            sumVal += Math.pow(r, length);
            number /= 10;
        }
        if (sumVal == copyNumber)
            System.out.println("Armstrong number using old skool");
        else
            System.out.println("NOT Armstrong number using old skool");

    }
}
