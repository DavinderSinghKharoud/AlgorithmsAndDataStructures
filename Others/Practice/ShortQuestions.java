package Others.Practice;

import java.util.*;

public class ShortQuestions {
    public static void main(String[] args) {
        ShortQuestions o = new ShortQuestions();
        System.out.println(o.encrypt("abc"));
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
        for (int i = 0; i < len; i++) {
            int num = arr[i];
            append(num, max, min);
            ans[i] = getMedian(max, min);
        }
        return ans;
    }

    int getMedian(PriorityQueue<Integer> max, PriorityQueue<Integer> min) {
        int total = max.size() + min.size();
        if (total % 2 == 1) {
            //If it is odd
            return max.peek();
        } else {
            //If it is even
            return (max.peek() + min.peek()) / 2;
        }
    }

    void append(int num, PriorityQueue<Integer> max, PriorityQueue<Integer> min) {
        max.add(num);
        min.add(max.remove());
        if (min.size() > max.size()) max.add(min.remove());
    }


    String rotationalCipherString(String input, int rotationFactor) {
        StringBuilder sbr = new StringBuilder();
        int azlen = 26, intlen = 10;
        int a = 'a', A = 'A', zero = '0';

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= a && c <= 'z') {
                int index = c - a;
                index = (index + rotationFactor) % azlen;
                c = (char) (index + a);
            } else if (Character.isDigit(c)) {
                int index = c - zero;
                index = (index + rotationFactor) % intlen;
                c = (char) (index + zero);
            } else if (c >= A && c <= 'Z') {
                int index = c - A;
                index = (index + rotationFactor) % azlen;
                c = (char) (index + A);
            }
            sbr.append(c);
        }
        return sbr.toString();
    }


    int matchingPairsString(String s, String t) {
        int len = s.length();

        Set<Pair> unmatched = new HashSet<>();
        int[] count = new int[26];
        int ans = 0;

        for (int i = 0; i < len; i++) {
            char ss = s.charAt(i), tt = t.charAt(i);
            if (ss == tt) {
                ans++;
                count[ss - 'a']++;
            } else {
                unmatched.add(new Pair(ss, tt));
            }
        }


        //If all are equal
        if (unmatched.size() == 0) {
            for (int val : count) {
                if (val > 1) return ans;
            }
            return ans - 2;
        } else if (unmatched.size() == 1) {
            for (Pair unmatch : unmatched) {
                if (count[unmatch.a] > 0 || count[unmatch.b] > 0) return ans;
            }
            return ans - 2;
        } else {
            // >= 2

            for (Pair unmatch : unmatched) {
                Pair opposite = new Pair(unmatch.b, unmatch.a);
                if (unmatched.contains(opposite)) {
                    //swap those
                    return ans + 2;
                }
            }

            for (Pair unmatch : unmatched) {
                if (count[unmatch.a] > 0 || count[unmatch.b] > 0) return ans + 1;
            }

            return ans;
        }

    }

    static class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }


    int minLengthSub(String s, String t) {
        if (t.length() > s.length()) return -1;
        int ans = Integer.MIN_VALUE;

        int[] target = new int[26];
        for (int i = 0; i < t.length(); i++) target[t.charAt(i) - 'a']++;

        int len = s.length();
        int[] curr = new int[26];
        int start = 0;
        for (int end = 0; end < len; end++) {
            curr[s.charAt(end) - 'a']++;
            while (start < end && isContain(curr, target)) {
                if (end - start + 1 >= ans) {
                    ans = end - start + 1;
                }
                curr[s.charAt(start--) - 'a']--;
            }
        }
        return (ans == Integer.MIN_VALUE) ? -1 : ans;

    }

    boolean isContain(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (b[i] > 0 && a[i] < b[i]) return false;
        }
        return true;
    }

    int[] findSwapping(int[] arr, int k) {
        int len = arr.length;
        for (int i = 0; i < len && k > 0; i++) {
            //For each index, check which smallest number we can replace
            int replace = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[replace] && j - i <= k) {
                    replace = j;
                }
            }
            k -= (replace - i);
            swap(arr, i, replace);
        }
        return arr;
    }

    void swap(int[] arr, int start, int end) {
        int num = arr[end];
        for (int i = end; i > start; i--) {
            arr[i] = arr[i - 1];
        }
        arr[start] = num;
    }


    int awkwardness(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);

        //Arrange number like
        //[1, 2, 3, 4, 5, 6, 7] --> [6, 4, 2, 1, 3, 5, 7]
        int ans = Math.abs(arr[1] - arr[0]);
        for (int i = 2; i < len; i += 2) {
            //All the odd indices(i + 1)
            ans = Math.max(ans, Math.abs(arr[i] - arr[i - 2]));
        }
        for (int i = 3; i < len; i += 2) {
            //All the even indices(i + 1)
            ans = Math.max(ans, Math.abs(arr[i] - arr[i - 2]));
        }

        ans = Math.max(ans, arr[len - 1] - arr[len - 2]);
        return ans;
    }


    int len;
    String s;

    int[] countOfNodes(Node root, ArrayList<Query> queries, String s) {
        // Write your code here
        this.len = s.length();
        this.s = s;
        int[][] dp = new int[len + 1][26];

        traverse(root, dp);

        int[] ans = new int[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            Query curr = queries.get(i);
            int result = 0;
            if (dp[curr.u] != null) {
                int[] counts = dp[curr.u];
                result = counts[curr.c - 'a'];
            }
            ans[i] = result;
        }

        return ans;
    }

    int[] traverse(Node root, int[][] dp) {
        int[] curr = new int[26];
        curr[s.charAt(root.val - 1) - 'a']++;

        for (Node child : root.children) {
            int[] childCount = traverse(child, dp);
            merge(curr, childCount);
        }
        return dp[root.val] = curr;
    }

    void merge(int[] parent, int[] b) {
        for (int i = 0; i < b.length; i++) {
            parent[i] += b[i];
        }
    }

    // Tree Node
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
            val = 0;
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Query {
        int u;
        char c;

        Query(int u, char c) {
            this.u = u;
            this.c = c;
        }
    }

    int[] getMilestone(int[] revenues, int[] milestones) {
        // Write your code here
        int mlen = milestones.length;
        int[] ans = new int[mlen];
        int[] sum = new int[revenues.length];
        for (int i = 0; i < revenues.length; i++) {
            if (i == 0) sum[i] = revenues[i];
            else {
                sum[i] += sum[i - 1] + revenues[i];
            }
        }

        for (int i = 0; i < mlen; i++) {
            int mile = milestones[i];
            int index = search(mile, sum);
            ans[i] = ((index != -1) ? index + 1 : index);
        }
        return ans;
    }

    int search(int mile, int[] sum) {
        int start = 0, end = sum.length - 1;
        while (start < end) {
            int mid = (end + start) / 2;
            if (sum[mid] >= mile) {
                end = mid;
            } else start = mid + 1;
        }
        return (sum[end] < mile) ? -1 : end;
    }

    int getBillionUsersDaySearch(float[] growthRates) {
        float max = -1;
        for (float num : growthRates) {
            max = Math.max(max, num);
        }

        int start = 0, end = (int) (1e9);
        double target = (double) (1e9);
        while (start < end) {
            int mid = start + (end - start) / 2;
            double pow = Math.pow(max, mid);
            if (Math.pow(max, mid) >= target) {
                end = mid;
            } else start = mid + 1;
        }

        return end;
    }

    String encrypt(String s) {
        if(s.isEmpty()) return s;
        int middle = s.length() / 2;
        if (s.length() % 2 == 0) middle--;
        StringBuilder curr = new StringBuilder();
        curr.append(s.charAt(middle));
        curr.append(encrypt(s.substring(0, middle)));
        if (middle + 1 <= s.length()) curr.append(encrypt(s.substring(middle + 1)));
        return curr.toString();
    }



    boolean canGetExactChange(int targetMoney, int[] denominations) {
        boolean[] visited = new boolean[targetMoney + 1];
        return isPossible(targetMoney, denominations, visited);
    }

    boolean isPossible(int target, int[] coins, boolean[] visited){
        if( target < 0 || visited[target]) return false;
        if( target == 0) return true;
        for(int coin: coins){
            if( target % coin == 0){
                return true;
            }
            if(isPossible(target - coin, coins, visited)){
                return true;
            }
        }
        visited[target] = true;
        return false;
    }

    boolean balancedSplit(int[] arr) {
        // Write your code here
        Arrays.sort(arr);
        long total = 0, sum = 0;
        for(int num: arr) total += num;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if( sum == (total - sum)){
                //Next number if exist should not be same
                if( i + 1 < arr.length && arr[i + 1] != arr[i]){
                    return true;
                }
            }
        }
        return false;
    }
}
