package com.ti.lambdas;

//Sólo un método abstracto
@FunctionalInterface
//SAM -> Single Abstract Method
public interface Calculator {
    //void result();
    //void result(String nombre);
    void operation(double a, double b);
}
