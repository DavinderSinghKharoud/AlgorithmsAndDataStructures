package Algorithms.LeetCode;

public class MysteryAlgo {

    private static long mystery(int a, int b) {
        long x = a;
        long y = b;

        while (x != y) {
            if (x > y) {
                x = x - y;
            } else if (x < y) {
                y = y - x;
            }
        }

        return x;
    }
    public static int divide(int a, int b) {
        int c = -1;

        try {
            c = a / b;
        }
        catch (Exception e) {
            System.err.print("Exception ");
        }
        finally {
            System.err.println("Finally ");
        }

        return c;
    }
    public static void main(String[] args) {
        System.out.println( mystery(2437, 875));

        System.out.println( divide(4, 0 ));
    }
}
