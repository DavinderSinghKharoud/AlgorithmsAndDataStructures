package LeetCode;

public class AddDigit {

    public static void main(String[] args) {
        System.out.println( addDigits(234));

    }
    public static int addDigits(int num) {


        while (num>9) {
            int sum = 0;

            while (num>0) {
                sum += num % 10;
                num = num / 10;

            }

            num = sum;
        }
        return num;

    }
}
