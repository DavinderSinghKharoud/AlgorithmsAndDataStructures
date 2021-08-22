package InterviewBit;

import java.util.*;

public class RepeatAndMissingNumberArray {

    /**
     * SumN = 1 + 2 + 3 + … + N = (N * (N + 1)) / 2
     * And, let the sum of all the array elements be Sum. Now,
     * SumN = Sum – FindGreatestCommonDivisor + FindUniqueBinaryString
     * FindGreatestCommonDivisor – FindUniqueBinaryString = Sum – SumN …(equation 1)
     * And from the sum of the squares of first N natural numbers,
     * <p>
     * SumSqN = 12 + 22 + 32 + … + N2 = (N * (N + 1) * (2 * n + 1)) / 6
     * And, let the sum of the squares of all the array elements be SumSq. Now,
     * SumSq = SumSqN + A2 – B2
     * SumSq – SumSqN = (FindGreatestCommonDivisor + FindUniqueBinaryString) * (FindGreatestCommonDivisor – FindUniqueBinaryString) …(equation 2)
     * Put value of (FindGreatestCommonDivisor – FindUniqueBinaryString) from equation 1 in equation 2,
     * <p>
     * SumSq – SumSqN = (FindGreatestCommonDivisor + FindUniqueBinaryString) * (Sum – SumN)
     * FindGreatestCommonDivisor + FindUniqueBinaryString = (SumSq – SumSqN) / (Sum – SumN) …(equation 3)
     * <p>
     * Solving equation 1 and equation 3 will give,
     * <p>
     * FindUniqueBinaryString = (((SumSq – SumSqN) / (Sum – SumN)) + SumN – Sum) / 2
     * And, FindGreatestCommonDivisor = Sum – SumN + FindUniqueBinaryString
     *
     * @param lst
     * @return
     */
    //Time complexity O(n) and Space complexity O(1)
    public static ArrayList<Integer> repeatedNumber(final List<Integer> lst) {

        int len = lst.size();

        long sumN = (len * (len + 1)) / 2;
        long sumSquareN = (len * (len + 1) * (2 * len + 1)) / 6;

        long sum = 0;
        long sumSq = 0;
        for (int num : lst) {
            sum += num;
            sumSq += Math.pow(num, 2);
        }

        long missingNumber = ((sumSq - sumSquareN) / (sum - sumN) + sumN - sum) / 2;
        long repeatedNumber = sum - sumN + missingNumber;

        ArrayList<Integer> res = new ArrayList<>();

        res.add((int) repeatedNumber);
        res.add((int) missingNumber);

        return res;
    }

    public static void main(String[] args) {

        System.out.println(repeatedNumber3(Arrays.asList(3, 1, 2, 5, 3)));
    }

    //Time and Space complexity O(n)
    public static ArrayList<Integer> repeatedNumber2(final List<Integer> lst) {
        int len = lst.size();
        ArrayList<Integer> temp = new ArrayList<>(lst);

        ArrayList<Integer> res = new ArrayList<>();
        for (int index = 0; index < len; index++) {
            int val = Math.abs(temp.get(index)) - 1; //because array is 0 based
            if (temp.get(val) < 0) {
                res.add(Math.abs(lst.get(index)));
            } else {
                temp.set(val, Math.abs(lst.get(val)) * -1);
            }

        }

        for (int index = 0; index < len; index++) {
            if (temp.get(index) > 0) {
                res.add(index + 1);
            }
        }

        return res;
    }

    //Time and Space complexity O(n)
    public static ArrayList<Integer> repeatedNumber3(final List<Integer> lst) {
        int len = lst.size();
        HashSet<Integer> set = new HashSet<>();
        double sumN = (len * (len + 1)) / 2;
        long sum = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for (int index = 0; index < len; index++) {
            if (set.contains(lst.get(index))) {
                res.add(lst.get(index));
            }
            set.add(lst.get(index));
            sum += lst.get(index);

        }

        double diff = sum - sumN;
        res.add( (res.get(0) - (int)diff));
        return res;
    }
}

