package Algorithms.Generic;

public class Printer<T> {


    public void printArray(T[] element) {
        for (int count = 0; count < element.length; count++) {
            System.out.println(element[count]);
        }
    }
}
