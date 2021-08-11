package HackerRank;

import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the length: ");
        int size = sc.nextInt();
        int[] arr = new int[size];

        for(int index = 0; index<size; index++){
            System.out.print("Enter the number:");
            int num = sc.nextInt();
            arr[index] = num;

        }

        for(int Element: arr){
            if(checkPrimeSquareRoot(Element)){
                System.out.println("Prime");
            }else{
                System.out.println("Not prime");
            }
        }

    }

    //Time Complexity (n)
    private static boolean checkPrime(int num) {

        if(num<=1){
            return false;
        }

        for(int checkNum = 2; checkNum<num; checkNum++){

            if(num%checkNum == 0){
                return false;
            }
        }

        return true;

    }

    //Time Complexity (square root of n)
    private static boolean checkPrimeSquareRoot(int num) {

        if(num<=1){
            return true;
        }

        double sqr = Math.sqrt(num);
        if(sqr % 1 == 0){
            return false;
        }

        for(int checkNum = 2; checkNum<(int)sqr+1; checkNum++){

            if(num%checkNum == 0){
                return false;
            }
        }

        return true;

    }
}
