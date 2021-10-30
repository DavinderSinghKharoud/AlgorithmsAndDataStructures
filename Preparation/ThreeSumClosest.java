package Preparation;

import java.util.Arrays;

public class ThreeSumClosest {

    public int threeSumClosest(int[] arr, int target) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int ans = min;
        for(int i = 0; i < arr.length; i++){
            int curr = arr[i];
            int start = 0, end = arr.length - 1;
            while(start < end){
                if(start == i) start++;
                else if( end == i) end--;
                else{
                    int sum = arr[start] + arr[end] + curr;
                    int diff =  Math.abs(sum - target);
                    if(diff < min){
                        min = diff;
                        ans = sum;
                    }
                    if( sum < target){
                        start++;
                    }else if( sum > target) end--;
                    else return target;
                }
            }
        }
        return ans;
    }
}
