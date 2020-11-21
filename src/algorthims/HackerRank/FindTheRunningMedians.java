package algorthims.HackerRank;


import java.text.DecimalFormat;
import java.util.*;

/**
 * The median of a set of integers is the midpoint value of the data set for which an equal number of integers are less than and greater than the value. To find the median, you must first sort your set of integers in non-decreasing order, then:
 * If your set contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted set ,  is the median.
 * If your set contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted set ,  is the median.
 * Given an input stream of  integers, you must perform the following task for each  integer:
 * Add the  integer to a running list of integers.
 * Find the median of the updated list (i.e., for the first element through the  element).
 * Print the list's updated median on a new line. The printed value must be a double-precision number scaled to  decimal place (i.e.,  format).
 * Input Format
 * The first line contains a single integer, , denoting the number of integers in the data stream.
 * Each line  of the  subsequent lines contains an integer, , to be added to your list.
 * Constraints
 *
 *
 * Output Format
 * After each new integer is added to the list, print the list's updated median on a new line as a single double-precision number scaled to  decimal place (i.e.,  format).
 * Sample Input
 * 6
 * 12
 * 4
 * 5
 * 3
 * 8
 * 7
 * Sample Output
 * 12.0
 * 8.0
 * 5.0
 * 4.5
 * 5.0
 * 6.0
 */
public class FindTheRunningMedians {

    //Time complexity O( n Log(n) ) and Space complexity O(n)

    //Can improve the time complexity using two priority queues already did before
    static double[] runningMedian(int[] a) {

        List<Integer> lst = new ArrayList<>();
        double[] res = new double[a.length];
        int resIndex = 0;
        for (int num : a) {

            int index = Collections.binarySearch(lst, num);
            if (index < 0) {
                index = -1 - (index);
            }
            lst.add(index, num);

            double median = getMedian(lst);
            DecimalFormat df = new DecimalFormat("#.#");
            res[resIndex++] = Double.parseDouble(df.format(median));

        }
        return res;
    }

    static double getMedian(List<Integer> lst) {
        int len = lst.size();
        if (len % 2 == 0) { //If it is even
            return (double) (lst.get(len / 2) + lst.get((len - 1) / 2)) / 2;
        } else { //It is odd
            return lst.get(len / 2);
        }
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(runningMedian(new int[]{0, 0})));
    }


}
