package LeetCode;

public class MissingNumbers {

    //Using gauss formula( Summation Formula)
    public static int missingNumber(int[] nums) {

        int expectedSum = nums.length * ( nums.length + 1)/2;
        int sum = 0;
        for( int num: nums ){
            sum += num;
        }

        return expectedSum - sum;


    }

    public static void main(String[] args) {

        System.out.println( missingNumber( new int[]{
                3,0,1
        }));
    }
}
