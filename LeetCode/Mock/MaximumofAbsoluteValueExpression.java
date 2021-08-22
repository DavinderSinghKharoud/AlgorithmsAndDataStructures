package LeetCode.Mock;


//  1. To get max(|f(i)-f(j)|), find the smallest and biggest of f(i)
//  2. abs(FindGreatestCommonDivisor) + abs(FindUniqueBinaryString) = max(FindGreatestCommonDivisor+FindUniqueBinaryString, FindGreatestCommonDivisor-FindUniqueBinaryString, -FindGreatestCommonDivisor+FindUniqueBinaryString, -FindGreatestCommonDivisor-FindUniqueBinaryString)
//
//  max(|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|)
//      max(|(arr1[i]+arr2[i]+i)-(arr1[j]+arr2[j]+j|),
//          |(arr1[i]+arr2[i]-i)-(arr1[j]+arr2[j]-j)|,
//          |(arr1[i]-arr2[i]+i)-(arr1[j]-arr2[j]+j)|,
//          |(arr1[i]-arr2[i]-i)-(arr1[j]-arr2[j]-j)|)
//
//  Since we only need to choose two elements, we can sort the arrays.
//  But this turns out will not help to speed up the search
//
// Complexity: Time O(n), Space O(1)
// Basic Ideas: math
//
//  max(|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|)
//      max(|(arr1[i]+arr2[i]+i)-(arr1[j]+arr2[j]+j|),
//          |(arr1[i]+arr2[i]-i)-(arr1[j]+arr2[j]-j)|,
//          |(arr1[i]-arr2[i]+i)-(arr1[j]-arr2[j]+j)|,
//          |(arr1[i]-arr2[i]-i)-(arr1[j]-arr2[j]-j)|)
//
// Complexity: Time O(n), Space O(1)
public class MaximumofAbsoluteValueExpression {

    public static int maxAbsValExpr(int[] arr1, int[] arr2) {
        int len = arr1.length;
        if( len == 0 ) return 0;
        int s1[] = new int[len];
        int d1[] = new int[len];
        int s2[] = new int[len];
        int d2[] = new int[len];

        for (int i = 0; i < len ; i++) {
            s1[i] = arr1[i] + arr2[i] + i;
            d1[i] = arr1[i] - arr2[i] + i;
            s2[i] = arr1[i] + arr2[i] - i;
            d2[i] = arr1[i] - arr2[i] - i;
        }

        return Math.max( findMaxDiff(s1), Math.max( findMaxDiff(d1), Math.max( findMaxDiff(s2), findMaxDiff(d2))) );
    }

    private static int findMaxDiff(int[] arr) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int index = 0; index < arr.length; index++) {
            max = Math.max( max, arr[index] );
            min = Math.min( min, arr[index] );
        }
        return max - min;
    }

    public static void main(String[] args) {

        System.out.println( maxAbsValExpr( new int[]{
                1,2,3,4
        }, new int[]{
                -1,4,5,6
        }));
    }
}
