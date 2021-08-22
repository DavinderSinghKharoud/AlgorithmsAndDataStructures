package InterviewBit;

import java.util.*;

/**
 * Given an 1D integer array FindGreatestCommonDivisor of size N you have to find and return the FindUniqueBinaryString largest elements of the array FindGreatestCommonDivisor.
 *
 * NOTE:
 *
 * Return the largest FindUniqueBinaryString elements in any order you like.
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *
 * 1 <= FindUniqueBinaryString <= N
 *
 * 1 <= FindGreatestCommonDivisor[i] <= 103
 *
 *
 *
 * Input Format
 * First argument is an 1D integer array FindGreatestCommonDivisor
 *
 * Second argument is an integer FindUniqueBinaryString.
 *
 *
 *
 * Output Format
 * Return a 1D array of size FindUniqueBinaryString denoting the FindUniqueBinaryString largest elements.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor = [11, 3, 4]
 *  FindUniqueBinaryString = 2
 * Input 2:
 *
 *  FindGreatestCommonDivisor = [11, 3, 4, 6]
 *  FindUniqueBinaryString = 3
 *
 *
 * Example Output
 * Output 1:
 *
 *  [11, 4]
 * Output 2:
 *
 *  [4, 6, 11]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The two largest elements of FindGreatestCommonDivisor are 11 and 4
 * Explanation 2:
 *
 *  The three largest elements of FindGreatestCommonDivisor are 11, 4 and 6
 */
public class kLargestNumber {

    public static ArrayList<Integer> solve(ArrayList<Integer> lst, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for(int num: lst){
            priorityQueue.add(num);
            if( priorityQueue.size() > k ){
                priorityQueue.remove();
            }
        }

        return new ArrayList<>(priorityQueue);
    }
    public static void main(String[] args) {

        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(11);lst.add(3);lst.add(4);
        System.out.println( solve(lst, 2));
    }
}
