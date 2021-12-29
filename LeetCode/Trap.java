package LeetCode;

public class Trap {
    public static void main(String[] args) {
        System.out.println(new Trap().trap(new int[]{
                2, 0, 3, 4
        }));
    }
    public int trap(int[] height) {
        int len = height.length;
        if(len <= 1) return 0;
        int[] left = new int[len], right = new int[len];

        int max = height[0];
        left[0] = max;
        for(int i = 1; i < len; i++){
            left[i] = max;
            max = Math.max(max, height[i]);
        }

        max = height[len - 1];
        right[len - 1] = max;
        for(int i = len - 2; i >= 0; i--){
            right[i] = max;
            max = Math.max(max, height[i]);
        }

        int ans = 0;
        for(int i = 1; i < len - 1; i++){
            int leftMax = left[i], rightMax = right[i];
//            System.out.println(leftMax + " " +  rightMax + " " + height[i]);
            int curr = Math.min(leftMax, rightMax) - height[i];
            if(curr > 0) ans += curr;
        }
        return ans;
    }
}
