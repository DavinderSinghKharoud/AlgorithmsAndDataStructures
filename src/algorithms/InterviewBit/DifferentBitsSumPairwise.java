package algorithms.InterviewBit;


import java.util.*;

public class DifferentBitsSumPairwise {


    public static int cntBits(List<Integer> lst) {

        int len = lst.size();
        if (len == 0) return 0;

        int total = 0;

        for (int i = 0; i < len; i++) {

            for (int j = i; j < len; j++) {

                total += count( lst.get(i), lst.get(j) ) * 2;
            }
        }

        return total;
    }

    public static int count(int num1, int num2) {
        int total = 0;

        for (int index = 31; index >= 0; index--) {

            int first = num1 & 1;
            int second = num2 & 1;

            if (first != second) {
                total++;
            }

            num1 = num1 >> 1;
            num2 = num2 >> 1;
        }

        return total;
    }

    public static void main(String[] args) {

        System.out.println(cntBits(Arrays.asList(1, 3, 5)));
    }
}
