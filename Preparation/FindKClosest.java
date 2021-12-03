package Preparation;

import java.util.*;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 * <p>
 * An integer a is closer to x than an integer b if:
 * <p>
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class FindKClosest {

    public List<Integer> findClosestElements5(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - 1;
        while (hi - lo >= k) {
            if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
                lo++;
            } else {
                hi--;
            }
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = lo; i <= hi; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int diff1 = Math.abs(o1 - x), diff2 = Math.abs(o2 - x);
            if (diff1 == diff2) return Integer.compare(o2, o1);
            return Integer.compare(diff2, diff1);
        });

        for (int num : arr) {
            pq.add(num);
            if (pq.size() > k) pq.remove();
        }
        while (!pq.isEmpty()) {
            ans.add(pq.remove());
        }
        Collections.sort(ans);
        return ans;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        //Find the closest (best left) if possible
        int closest = 0;
        int bestDiff = Math.abs(arr[0] - x);
        for (int i = 0; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - x);
            if (bestDiff > diff) {
                bestDiff = diff;
                closest = i;
            }
        }

        int start = closest, end = closest + 1;
        while (start >= 0 && end < arr.length) {
            //Always check left if diffs are equals
            int diff1 = Math.abs(arr[start] - x), diff2 = Math.abs(arr[end] - x);
            if (diff1 <= diff2) {
                ans.add(arr[start--]);
            } else {
                ans.add(arr[end++]);
            }
            if (ans.size() == k) break;
        }
        while (ans.size() != Math.min(k, arr.length)) {
            if (start >= 0) {
                ans.add(arr[start--]);
            } else {
                ans.add(arr[end++]);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        //Find the closest (best left) if possible
        int closest = 0;
        int bestDiff = Math.abs(arr[0] - x);
        for (int i = 0; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - x);
            if (bestDiff > diff) {
                bestDiff = diff;
                closest = i;
            }
        }

        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        int start = closest, end = closest + 1;
        while (start >= 0 && end < arr.length) {
            //Always check left if diffs are equals
            int diff1 = Math.abs(arr[start] - x), diff2 = Math.abs(arr[end] - x);
            if (diff1 <= diff2) {
                left.add(arr[start--]);
            } else {
                right.add(arr[end++]);
            }
            if (left.size() + right.size() == k) break;
        }
        while ((left.size() + right.size()) != Math.min(k, arr.length)) {
            if (start >= 0) {
                left.add(arr[start--]);
            } else {
                right.add(arr[end++]);
            }
        }
        for (int i = left.size() - 1; i >= 0; i--) {
            ans.add(left.get(i));
        }
        for (int n : right) ans.add(n);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new FindKClosest().findClosestElements4(new int[]{2, 5, 7, 9}, 3, 5));
    }

    public List<Integer> findClosestElements4(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int start = 0, end = arr.length - k - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            //arr[mid + k] gives next number than k
            if (arr[mid + k] < x || Math.abs(x - arr[mid]) > Math.abs(arr[mid + k] - x)) start = mid + 1;
            else end = mid - 1;
        }
        for (int i = start; i <= end + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
