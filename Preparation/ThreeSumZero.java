package Preparation;

import java.util.*;

public class ThreeSumZero {

    /**
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     *
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     *
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     *
     * The solution set must not contain duplicate triplets.
     *
     * For example, given array S = {-1 0 1 2 -1 -4},
     *
     * A solution set is:
     *
     *   (-1, 0, 1)
     *
     *   (-1, -1, 2)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new ThreeSumZero().threeSum(new int[]{2147483647, -2147483648, -2147483648, 0, 1 })));
    }

    public int[][] threeSum2(int[] arr) {
        Arrays.sort(arr);
        List<int[]> lst = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if( i == 0 || arr[i] != arr[i - 1]){
                int start = i + 1, end = arr.length - 1;
                while(start < end){
                    long sum = (long)arr[i] + arr[start] + arr[end];
                    if( sum  == 0){
                        lst.add(new int[]{arr[i], arr[start], arr[end]});

                        while( start < end && arr[start + 1] == arr[start]) start++;
                        while( start < end && arr[end - 1] == arr[end]) end--;
                    }
                    if( sum < 0){
                        start++;
                    }else end--;
                }
            }
        }

        int[][] ans = new int[lst.size()][3];

        for(int i = 0;i  < lst.size(); i++){
            ans[i] = lst.get(i);
        }
        return ans;
    }

    public int[][] threeSum(int[] arr) {
        Arrays.sort(arr);
        Set<Node> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            find(i, arr, set);
        }

        int[][] ans = new int[set.size()][3];
        int index = 0;
        for (Node node : set) {
            ans[index++] = new int[]{node.a, node.b, node.c};
        }
        return ans;
    }

    void find(int index, int[] arr, Set<Node> lst) {
        int start = index + 1, end = arr.length - 1;
        long target = -1 * (long)arr[index];
        while (start < end && end < arr.length) {
            long sum = (long)arr[start] + arr[end];
            if (sum == target) {
                lst.add(new Node(arr[index], arr[start], arr[end]));
                start++;
                end--;
            } else if (sum < target) {
                start++;
            } else end--;
        }
    }


    static class Node {
        int a, b, c;

        public Node(int a, int b, int c) {
            int[] arr = new int[]{a, b, c};
            Arrays.sort(arr);
            this.a = arr[0];
            this.b = arr[1];
            this.c = arr[2];
        }

        public boolean equals(Object o) {
            Node oo = (Node) o;
            return oo.a == a && oo.b == b && oo.c == c;
        }

        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }
}
