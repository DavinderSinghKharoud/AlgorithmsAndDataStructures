package Algorithms;

import java.util.Scanner;

/**
 * Write a Calculator class with a single method: int power(int,int).
 * The power method takes two integers,  and , as parameters and returns the integer result of n^p.
 * If either n or p is negative, then the method must throw an exception with the message:
 * n and p should be non-negative.
 */
public class Exponent {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter how many calculations you want: ");
        int t = in.nextInt();
        while (t-- > 0) {

            System.out.print("Enter Number: ");
            int n = in.nextInt();
            System.out.print("Enter exponent: ");
            int p = in.nextInt();
            Calculator myCalculator = new Calculator();
            try {
                int ans = myCalculator.power(n, p);
                System.out.println("result: "+ans);

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }
}

class Calculator{

    int power(int n, int p){

        if(n<0 || p<0){
            throw new IllegalArgumentException("n and p should be non-negative");
        }else{
            return (int) Math.pow(n,p);
        }
    }
        }
