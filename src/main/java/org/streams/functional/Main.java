package org.streams.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static boolean esPar(Integer n){
        return n % 2 == 0;
    }
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
        //Encontrar los números pares y guardarlos en un arraylist

        List<Integer> results = new ArrayList<>();

        //programación imperativa, tradicional, ¿Cómo se hace?
        /*for (int i=0;i<numbers.size();i++)
            if (numbers.get(i)%2 ==0)//es par
                results.add(numbers.get(i));*/

        //programación funcional, declarativo, ¿Qué se hace?
        //Steam organiza la lista para recorrela y aplicar funciones
        /*results = numbers.stream()
                .filter(Main::esPar)
                .collect(Collectors.toList());*/

        results = numbers.stream()
                //.filter((Integer n)->{return n % 2 == 0;}) //lambda -> función anónima
                .filter(n-> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(results);



    }
}
