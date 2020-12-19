package Algorithms.LeetCode;

public class GenerateTheString {

    public static String generateTheString(int n) {

        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        if (n <= 26) {
            return alphabets.substring(0, n);
        }

        String res = "";
        if (n % 2 == 0) {
            int index = 0;
            while (index < n - 1) {
                res += alphabets.substring(0, 1);
                index++;
            }
            res += alphabets.substring(1, 2);
        } else {
            int index = 0;
            while (index < n) {
                res += alphabets.substring(0, 1);
                index++;
            }
        }

        return res;

    }

    public static void main(String[] args) {

        System.out.println(generateTheString(0));
    }
}
