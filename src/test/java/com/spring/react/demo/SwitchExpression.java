package com.spring.react.demo;

import java.util.Calendar;

import static java.util.Calendar.*;

public class SwitchExpression {

    public static void main(String[] args) {

        var month = APRIL;

        var result = switch(month) {
            case JANUARY, JUNE, JULY -> 3;
            case FEBRUARY, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER -> 1;
            case MARCH, MAY, APRIL, AUGUST -> 2;
            default -> 0;
        };
        System.out.println(result);


        record Name(String fName, String lName, int age) {};
        Name host = new Name("William","Korando", 37);

        if(host instanceof Name(String fName, String lName, int age)) {
            String printName = lName + ", " + fName + " age: " + age;
            System.out.println(printName);
        }


    }
}
