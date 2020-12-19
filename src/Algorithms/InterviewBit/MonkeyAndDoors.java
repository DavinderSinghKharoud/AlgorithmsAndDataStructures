package Algorithms.InterviewBit;

public class MonkeyAndDoors {


    /**
     * There are 100 doors, all closed.
     * In a nearby cage are 100 monkeys.
     *
     * The first monkey is let out, and runs along the doors opening every one.
     * The second monkey is then let out, and runs along the doors closing the 2nd, 4th, 6th,…  - all the even-numbered doors.
     * The third monkey is let out. He attends only to the 3rd, 6th, 9th,… doors (every third door, in other words), closing any that is open and opening any that is closed, and so on.
     * After all 100 monkeys have done their work in this way, what state are the doors in after the last pass?
     */

    /**
     * If the number has perfect square then it alwasys has odd number of factors, for instance:
     * Number = f1 f2 f3 f4 f5 as we can see the factors will be f1 * f5 , f2 * f4, f3 * f3(perfect square), and vice versa
     * while if a number has even factors it will not be a perfect square
     */
    public static void main(String[] args) {

        int res = 0;
        for (int door = 1; door <= 100; door++) {

            double perfectSquare = Math.sqrt(door);

            if (isPerfectNumber(perfectSquare)) { // If it is odd then after 100 it will be open because starting was open
                res++;
            }
        }

        System.out.println(res);
    }

    private static boolean isPerfectNumber(double perfectSquare) {

        return (int) perfectSquare == perfectSquare;
    }
}
