package Others;

import java.util.Scanner;

//Get a string from user, and seperate even and odd characters
public class SeperatedString {

    static int len;
    static String evenChar="";
    static String oddChar="";

    public static void main(String[] args) {
        System.out.println("Enter a String to cut: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();



        len = input.length();

        seperateEvenOddChar(input);

        System.out.print(evenChar+" ");
        System.out.println(oddChar);



    }

    private static void seperateEvenOddChar(String input) {

        for(int index=0; index<len; index++){
            if(index%2 == 0){
                evenChar+= input.charAt(index);
            }else{
                oddChar+= input.charAt(index);
            }
        }

    }

}
