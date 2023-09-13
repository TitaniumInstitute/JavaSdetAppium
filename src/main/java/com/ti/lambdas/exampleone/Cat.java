package com.ti.lambdas.exampleone;

public class Cat implements Printable{
    public String name;
    public int age;

    public Cat(){}


    @Override
    public void print() {
        System.out.println("Meow");
    }
}
