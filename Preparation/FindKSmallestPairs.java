package Preparation;

import java.util.Objects;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * <p>
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 * <p>
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 and nums2 both are sorted in ascending order.
 * 1 <= k <= 1000
 */
import java.util.*;

public class FindKSmallestPairs {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        Set<Node> vis = new HashSet<>();
        int len1 = nums1.length, len2 = nums2.length;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(nums1[o1.i] + nums2[o1.j],
                    nums1[o2.i] + nums2[o2.j]);
        });
        pq.add(new Node(0, 0));
        vis.add(new Node(0, 0));
        List<List<Integer>> ans = new ArrayList<>();
        while (!pq.isEmpty() && ans.size() < k) {
            Node curr = pq.poll();
            int i = curr.i, j = curr.j;
            ans.add(Arrays.asList(nums1[i], nums2[j]));
            if (i + 1 < len1) {
                Node mod = new Node(i + 1, j);
                if (!vis.contains(mod)) {
                    pq.add(mod);
                    vis.add(mod);
                }
            }
            if (j + 1 < len2) {
                Node mod = new Node(i, j + 1);
                if (!vis.contains(mod)) {
                    pq.add(mod);
                    vis.add(mod);
                }
            }
        }
        return ans;
    }

    static class Node {
        int i, j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i && j == node.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
