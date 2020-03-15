package algorthims.LeetCode;

public class ReverseInteger {

    public static int reverse(int x) {
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x *= -1;
        }
        long reversed = 0;

        while ( x > 0){
            reversed = reversed*10 + (x%10);
            x /= 10;
        }

        if (reversed > Integer.MAX_VALUE) {
            return 0;
        }
        if (negative) {
            reversed *= -1;
        }
        return (int) reversed;


    }

    public static void main(String[] args) {

        System.out.println(reverse(-1563847412));
    }
}
