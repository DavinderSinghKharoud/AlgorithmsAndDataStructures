package HackerRank;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, find and print the maximum number of
 * integers you can select from the array such that the absolute difference between any two
 * of the chosen integers is less than or equal to 1. For example, if your array is [1,1,2,2,4,4,5,5,5] you can
 * create two subarrays meeting the criterion: [1,1,2,2] [4,4,5,5,5]  and
 * . The maximum length subarray has 5 elements.
 */
public class PickingNumbers {

    public static int pickingNumbers(List<Integer> lst) {
        int arr[]  = new int[100+1];
        int maxLength = 0;

        for (int index = 0; index < lst.size(); index++) {

            int num = lst.get(index);
            arr[num]+=1;

        }

        for (int index = 0; index < arr.length - 1; index++) {

            int result = Integer.MIN_VALUE;
            int value = Math.max(result, arr[index] + arr[index+1] );
            if( result <= value){
             result = value;
            }

            if(result> maxLength){
                maxLength = result;
            }

        }

        return maxLength;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);

        int result = pickingNumbers(list);

        System.out.println(result);
    }
}
