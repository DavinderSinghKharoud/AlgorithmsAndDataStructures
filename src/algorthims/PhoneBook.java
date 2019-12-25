package algorthims;

import java.util.HashMap;
import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the size of phone book: ");
        int n = in.nextInt();

        HashMap<String,Integer> phoneBook = new HashMap<String, Integer>();

        //Adding contacts to the phoneBook
        for(int i = 0; i < n; i++){
            System.out.print("Enter the name: ");
            String name = in.next();
            System.out.print("Enter the phone number: ");
            int phone = in.nextInt();

            phoneBook.put(name,phone);

        }


        System.out.print("Enter the name to retreive the phone number: ");

        while(in.hasNext()){

            String s = in.next();

            if(phoneBook.get(s) != null){
                System.out.println(s + "=" + phoneBook.get(s));
            }else{
                System.out.println("Not found");
            }
        }
        in.close();
    }
}
