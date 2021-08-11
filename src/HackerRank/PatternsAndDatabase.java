package HackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Consider a database table, Emails, which has the attributes First Name and Email ID.
 * Given  rows of data simulating the Emails table, print an alphabetically-ordered list
 * of people whose email address ends in .
 *
 * Constraints:
 * Each of the first names consists of lower case letters  only.
 * Each of the email IDs consists of lower case letters ,  and  only.
 * The length of the first name is no longer than 20.
 * The length of the email ID is no longer than 50.
 */
public class PatternsAndDatabase {


    private static final Scanner scanner = new Scanner(System.in);

    private static List<String> myList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("Enter the size of the data: ");
        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int NItr = 0; NItr < N; NItr++) {
            System.out.print("Enter the name and email seperate by space: ");
            String[] firstNameEmailID = scanner.nextLine().split(" ");

            String firstName = firstNameEmailID[0];

            String emailID = firstNameEmailID[1];

            checkMail(firstName, emailID);

        }
        java.util.Collections.sort(myList);

        for(String names: myList){
            System.out.println(names);
        }

        scanner.close();
    }

    private static void checkMail(String firstName, String emailID) {
        if(emailID.contains("@gmail.com")){
            myList.add(firstName);
        }

    }
}
