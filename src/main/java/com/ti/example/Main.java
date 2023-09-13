package com.ti.example;

public class Main {
    public static void main(String[] args) {
        int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
        // Declaring array literal

        for (int i = 0; i < intArray.length; i++)
            System.out.println("Element at index " + i +
                    " : "+ intArray[i]);
    }
}