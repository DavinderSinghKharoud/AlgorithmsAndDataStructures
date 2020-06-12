package algorthims.LeetCode;

import java.util.Arrays;

public class SortColors {

    public static void sortColors(int[] nums) {
        int countZeroes = 0;
        int countOne = 0;
        int countTwo = 0;

        for( int num: nums ){
            if( num == 0 ){
                countZeroes++;
            }else if( num == 1 ){
                countOne++;
            }else{
                countTwo++;
            }
        }

        int index = 0;
        while( countZeroes != 0 ){
            nums[index++] = 0;
            countZeroes--;
        }

        while( countOne != 0 ){
            nums[index++] = 1;
            countOne--;
        }

        while( countTwo != 0 ){
            nums[index++] = 2;
            countTwo--;
        }

    }

    public static void main(String[] args) {
        int[] arr = { 2,0,2,1,1,0 };
        sortColors( arr );
        System.out.println(Arrays.toString(arr));
    }

    public void sortColors2(int[] nums) {

    }
}
