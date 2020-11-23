package algorithms;

import java.util.Scanner;

/**
 *Objective
In this challenge, we're getting started with conditional statements. 

Task
Given an integer, , perform the following conditional actions:

If  is odd, print Weird
If  is even and in the inclusive range of  to , print Not Weird
If  is even and in the inclusive range of  to , print Weird
If  is even and greater than , print Not Weird
Complete the stub code provided in your editor to print whether or not  is weird.
 
 **/
public class OddAndEven {
	
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Enter the Number to test: ");
	
	    int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        if( (N%2 == 0) && N>=2 && N<=5 ){
            
            System.out.println("Not Weird");
            
        }else if( (N%2 == 0) && N>=6 && N<=20 ){
            
            System.out.println("Weird");

        }else if( (N%2 == 0) && N>20 ){
            
            System.out.println("Not Weird");

        }else{
            
            System.out.println("Weird");

        }

        scanner.close();
	}

}
