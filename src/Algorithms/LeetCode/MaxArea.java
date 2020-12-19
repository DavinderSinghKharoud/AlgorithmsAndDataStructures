package Algorithms.LeetCode;

public class MaxArea {

    public static int maxArea(int[] height) {

        int max = 0;
        int start = 0;
        int end = height.length - 1;

        while (start < end) {
            max = Math.max( max, Math.min( height[start], height[end])* (end - start));

            if( height[start] > height[end]){
                end--;
            }else{
                start++;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println(maxArea(new int[]{
                1,8,6,2,5,4,8,3,7
        }));
    }
}
