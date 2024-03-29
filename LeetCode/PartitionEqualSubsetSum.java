package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that
 * the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * <p>
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 */
public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int i : nums) {
            total += i;
        }

        if (total % 2 != 0) {
            return false;
        }

        return canPartition(nums, 0, 0, total, new HashMap<String, Boolean>());

    }

    private static boolean canPartition(int[] nums, int index, int sum, int total, HashMap<String, Boolean> state) {

        String current = index + "" + sum;
        if( state.containsKey( current )){
            return state.get( current );
        }
        if( sum * 2 == total ){
            return true;
        }
        if( sum > total/2 || index >= nums.length ){
            return false;
        }

        boolean foundPartition = canPartition(nums, index + 1, sum, total, state )
                || canPartition( nums, index + 1, sum + nums[index], total, state );

        state.put(current, foundPartition );
        return foundPartition;
    }

    //More Efficient
    public boolean helper(int nums[],int row,int []check,int target){
        if(row<0)return true;
        int curr=nums[row--];

        for(int i=0;i<check.length;i++){
            if(check[i]+curr<=target){
                check[i]+=curr;
                if(helper(nums,row,check,target)){
                    return true;
                }
                check[i]-=curr;
            }
            if(check[i]==0)break;
        }
        return false;
    }

    public boolean canPartition2(int[] nums) {
        int sum=0;
        for(int i:nums)  {
            sum+=i;
        }
        if(sum%2!=0){
            return false;
        }

        Arrays.sort(nums);

        int target=sum/2;
        int rows=nums.length-1;
        int k=2;
        if(nums[rows]>target){
            return false;
        }
        while(rows>0 && nums[rows]==target){
            rows--;
            k--;
        }
        return helper(nums,rows,new int[k],target);

    }

    public static void main(String[] args) {

        System.out.println( canPartition(new int[]{
                1,5,5,11
        }));

    }
}
