package Others.Practice;

import java.util.*;

public class ShortQuestions {
    public static void main(String[] args) {

    }

    boolean areTheyEqual(int[] array_a, int[] array_b) {
        if (array_a.length != array_b.length)
            return false;
        Arrays.sort(array_a);
        Arrays.sort(array_b);

        for (int i = 0; i < array_a.length; i++) {
            if (array_a[i] != array_b[i])
                return false;
        }
        return true;
    }

    int[] countSubarraysMaxPoss(int[] arr) {

        //First step is to find the max on both sides
        Stack<Integer> stack = new Stack<>();
        int len = arr.length;
        int[] left = new int[len], right = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            int curr = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] < curr) stack.pop();
            right[i] = (stack.isEmpty()) ? len : stack.peek();
            stack.push(i);
        }

        //We do similar thing for left
        stack.clear();
        for (int i = 0; i < len; i++) {
            int curr = arr[i];
            while (!stack.isEmpty() && arr[stack.peek()] < curr) stack.pop();
            left[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }

        //Compute the answer for every index
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            int leftMax = left[i], rightMax = right[i];
            leftMax = i - leftMax;
            rightMax = rightMax - i;
            //Current number will be included twice
            ans[i] = leftMax + rightMax - 1;
        }
        return ans;
    }

    int[] maxProduct(int[] arr) {
        int len = arr.length;
        int[] ans = new int[len];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int product = 1;
        for (int i = 0; i < Math.min(2, len); i++) {
            ans[i] = -1;
            product *= arr[i];
            pq.add(arr[i]);
        }


        for (int i = 2; i < len; i++) {
            pq.add(arr[i]);
            product *= arr[i];
            if (pq.size() > 3) {
                product = (product) / pq.remove();
            }
            ans[i] = product;
        }
        return ans;
    }

    int maxCandiesEat(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : arr) pq.add(num);

        int ans = 0;
        while (k-- > 0) {
            if (pq.size() > 0) {
                int max = pq.poll();

                ans += max;
                pq.add(max / 2);
            }
        }
        return ans;
    }

    int[] findMedianPQ(int[] arr) {
        //Max pq would be always equal or greater than min
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int len = arr.length;
        int[] ans = new int[len];
        for(int i = 0; i < len; i++){
            int num = arr[i];
            append(num, max, min);
            ans[i] = getMedian(max, min);
        }
        return ans;
    }

    int getMedian(PriorityQueue<Integer> max, PriorityQueue<Integer> min){
        int total = max.size() + min.size();
        if(total % 2 == 1){
            //If it is odd
            return max.peek();
        }else{
            //If it is even
            return (max.peek() + min.peek())/2;
        }
    }

    void append(int num, PriorityQueue<Integer> max, PriorityQueue<Integer> min){
        max.add(num);
        min.add(max.remove());
        if(min.size() > max.size()) max.add(min.remove());
    }


}