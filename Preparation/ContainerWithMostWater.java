package Preparation;

import java.util.*;

/**
 * Given n non-negative integers a1, a2, ..., an,
 * <p>
 * where each represents a point at coordinate (i, ai).
 * <p>
 * 'n' vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * <p>
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Your program should return an integer which corresponds to the maximum area of water that can be contained ( Yes, we know maximum area instead of maximum volume sounds weird. But this is 2D plane we are working with for simplicity ).
 * <p>
 * Note: You may not slant the container.
 * <p>
 * Example :
 * <p>
 * Input : [1, 5, 4, 3]
 * Output : 6
 * <p>
 * Explanation : 5 and 3 are distance 2 apart. So size of the base = 2. Height of container = min(5, 3) = 3.
 * So total area = 3 * 2 = 6
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater().maxArea(Arrays.asList(1, 4, 12, 3, 3, 2, 1)));
        System.out.println(new ContainerWithMostWater().maxArea2(Arrays.asList(1, 4, 12, 3, 3, 2, 1)));
    }

    public int maxArea(List<Integer> lst) {

        int start = 0, end = lst.size() - 1;
        int ans = 0;
        while (start < end) {
            int diff = (end - start) * Math.min(lst.get(start), lst.get(end));
            ans = Math.max(ans, diff);
            if (lst.get(start) < lst.get(end)) {
                start++;
            } else end--;
        }
        return ans;
    }

    public int maxArea2(List<Integer> lst) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            list.add(new Node(i, lst.get(i)));
        }
        int len = lst.size();
        Collections.sort(list, Comparator.comparingInt(o -> o.val));
        int maxIndexRight = len - 1;
        int ans = 0;
        //Alternative just maintain two variables, min and max
        TreeSet<Node> set = new TreeSet<>(Comparator.comparingInt(o -> o.index));
        set.add(list.get(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            Node curr = list.get(i);
            Node first = set.first();
            Node last = set.last();
            ans = Math.max(ans, Math.max(getArea(first.index, curr.index, curr.val),
                    getArea(last.index, curr.index, curr.val)));
            set.add(curr);
        }
        return ans;
    }

    int getArea(int a, int b, int h) {
        return Math.abs(a - b) * h;
    }

    static class Node {
        int val, index;

        public Node(int i, int v) {
            index = i;
            val = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return val == node.val && index == node.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, index);
        }
    }
}
