package Others;

import java.util.Scanner;

public class StringToInteger {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number: ");
        String S = in.next();
        try {
            System.out.println(Integer.parseInt(S));
        }catch (Exception e){
            System.out.println("Bad String");
        }


    }
}
