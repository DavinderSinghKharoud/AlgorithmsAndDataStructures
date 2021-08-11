package Others;

import java.util.Scanner;

public class NestedLogic {


    public static void main(String[] args) {
        int[] returnedDate = new int[3];
        int[] expectedDate = new int[3];
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the returned day: ");
        int day = sc.nextInt();
        returnedDate[0] = day;
        System.out.print("Enter the returned month: ");
        int mon = sc.nextInt();
        returnedDate[1] = mon;
        System.out.print("Enter the returned year: ");
        int year = sc.nextInt();
        returnedDate[2] = year;

        System.out.print("Enter the expected day: ");
        int expDate = sc.nextInt();
        expectedDate[0] = expDate;
        System.out.print("Enter the expected month: ");
        int expMon = sc.nextInt();
        expectedDate[1] = expMon;
        System.out.print("Enter the expected year:");
        int expYear = sc.nextInt();
        expectedDate[2] = expYear;


        System.out.println(calculateFine(returnedDate, expectedDate));
    }

    private static int calculateFine(int[] returnedDate, int[] expectedDate) {
        int retDay = returnedDate[0];
        int retMon = returnedDate[1];
        int retYear = returnedDate[2];

        int expDay = expectedDate[0];
        int expMon = expectedDate[1];
        int expYear = expectedDate[2];



        if (retYear > expYear) {
            return 10000;
        }else if (retYear < expYear) {
            return 0;
        } else {

            if (retMon > expMon) {

                return 500 * (retMon - expMon);

            } else {

                if (retDay > expDay) {
                    return 15 * (retDay - expDay);
                }else {
                    return 0;
                }
            }
        }




    }
}
