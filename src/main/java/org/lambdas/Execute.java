package org.lambdas;

import org.example.Oop;
import org.lambdas.exampleone.Cat;
import org.lambdas.exampleone.Printable;

public class Execute {
    public static void main(String[] args) {

        Oop oop = new Oop();
        oop.getTest();



        //Calculator calculator =()-> System.out.println("Welcome to LAmbda Expressions");
        //Calculator calculator =(n)-> System.out.println(n + " Welcome to LAmbda Expressions");

        //calculator.result();
        //calculator.result("Gilberto");

        /*Calculator calculatorAdd = ((a,b)->{
            double result = a+b;
            System.out.println(result);
        });

        calculatorAdd.operation(2,3);

        Calculator calculatorSub =((a,b)->{
                    double result = a-b;
                    System.out.println(result);
                });

        calculatorSub.operation(10,3);*/

        /*Cat cat = new Cat();
        //cat.print();
        printThing(cat);*/

        /*printThing(
                public void print() {
            System.out.println("Meow");
        });*/
    }

    static void printThing(Printable printable){
        printable.print();
    }
}
