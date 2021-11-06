package Preparation;

import java.util.Stack;

/**
 * Given an array of integers ScoreSmallestIndexWithEqualValue .
 *
 * ScoreSmallestIndexWithEqualValue represents a histogram i.e ScoreSmallestIndexWithEqualValue[i] denotes height of the ith histogram's bar. Width of each bar is 1.
 *
 * Find the area of the largest rectangle formed by the histogram.
 *
 *
 *
 * Problem Constraints
 * 1 <= |ScoreSmallestIndexWithEqualValue| <= 100000
 *
 * 1 <= ScoreSmallestIndexWithEqualValue[i] <= 1000000000
 *
 *
 *
 * Input Format
 * The only argument given is the integer array ScoreSmallestIndexWithEqualValue.
 *
 *
 *
 * Output Format
 * Return the area of largest rectangle in the histogram.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  ScoreSmallestIndexWithEqualValue = [2, 1, 5, 6, 2, 3]
 * Input 2:
 *
 *  ScoreSmallestIndexWithEqualValue = [2]
 *
 *
 * Example Output
 * Output 1:
 *
 *  10
 * Output 2:
 *
 *  2
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 * The largest rectangle has area = 10 unit. Formed by ScoreSmallestIndexWithEqualValue[3] to ScoreSmallestIndexWithEqualValue[4].
 * Explanation 2:
 *
 * Largest rectangle has area 2.
 */
public class Histogram {

    public static void main(String[] args) {
        System.out.println(new Histogram().largestRectangleArea(new int[]{1}));
    }
    public int largestRectangleArea(int[] arr) {
        int len = arr.length;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < len; i++){
            if(i == 0) stack.add(i);
            else{
                while( !stack.isEmpty() && arr[stack.peek()] > arr[i]){
                    //I can pop and get the anwer
                    int index = stack.pop();
                    int left = (stack.isEmpty())? -1: stack.peek();
                    int right = i;
                    ans = Math.max(ans, (right - left - 1) * arr[index] );
                }
                stack.add(i);
            }
        }

        while( !stack.isEmpty()){
            //I can pop and get the anwer
            int index = stack.pop();
            int left = (stack.isEmpty())? -1: stack.peek();
            ans = Math.max(ans,(len - left - 1) * arr[index] );
        }
        return ans;
    }
}
