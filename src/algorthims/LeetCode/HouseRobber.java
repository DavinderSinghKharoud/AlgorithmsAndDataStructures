package algorthims.LeetCode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HouseRobber {
    static int []record;
    public static int rob(int[] nums) {

        record = new int[ nums.length + 1];

        Arrays.fill( record, 0 ,record.length,-1);

        return Math.max( solve( nums, 0), solve( nums, 1));
    }

    private static int solve(int[] nums, int index) {

        if( index>= nums.length){
            return 0;
        }

        if( record[index] != -1){
            return record[index];
        }

        int odd = solve( nums, index + 2);
        int even = solve( nums, index + 3);

        record[index] = nums[index] + Math.max( odd, even);
        return record[index];
    }

    public static void main(String[] args) {

        System.out.println( rob(new int[]{
                1,1,1
        }));
    }
}
