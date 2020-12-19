package Algorithms;

import java.util.Scanner;

/*Given a base-10 integer, n, convert it to binary (base-2). Then find and print the base- 10 integer
denoting the maximum number of consecutive 1's in n's binary representation.*/
public class BinaryNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        System.out.println(getConsicutiveOne(getBinary(num)));


    }

    //Converting in to binary numbers
    private static String getBinary(int num){
        String binary = "";

        while(num!=1){

            if( num%2 == 0 ){

                binary+="0";

            }else{

                binary+= "1";

            }

            num = num/2;
        }

        binary+="1";
        return binary;

    }

    private static int getConsicutiveOne(String binary){
        int finalCount=0;
        int temp=0;

        for(int i=0; i<binary.length(); i++){

            if(binary.charAt(i) == '1'){

                temp += 1;

            } else {

                temp = 0;
            }

            if(finalCount<temp){
                finalCount = temp;
            }


        }
        return finalCount;

    }
}
