package LeetCode;

import java.util.*;

/**
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * <p>
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 * <p>
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
 * Example 2:
 * <p>
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 * <p>
 * Since this is a randomization problem, multiple answers are allowed.
 * All of the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 */
public class PickRandomIndex {

    public static void main(String[] args) {
        PickRandomIndex p = new PickRandomIndex();
        p.setUp(new int[]{1, 3, 4, 2, 6});
        System.out.println(p.pickIndex());
        System.out.println(p.pickIndex());
        System.out.println(p.pickIndex());
        System.out.println(p.pickIndex());
        System.out.println(p.pickIndex());
        System.out.println(p.pickIndex());
    }

    int len;
    long sum;
    double[][] arr;

    public void setUp(int[] w) {
        for (int ww : w) sum += ww;
        len = w.length;

        List<Node> lst = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int ww = w[i];
            lst.add(new Node((double) ww / sum, i));
        }

        lst.sort(Comparator.comparingDouble(o -> o.prob));
        double total = 0;
        arr = new double[len][3];
        int i = 0;
        for (Node curr : lst) {
            arr[i++] = new double[]{total, total + curr.prob, curr.index};
            total += curr.prob;
        }
    }

    public int pickIndex() {
        double random = Math.random();

        return search(random);
    }

    int search(double target) {
        int start = 0, end = len - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid][0] > target) {
                end = mid - 1;
            } else if (arr[mid][1] < target) {
                start = mid + 1;
            } else return (int) arr[mid][2];
        }

        return -1;
    }

    static class Node {
        double prob;
        int index;

        public Node(double p, int i) {
            index = i;
            prob = p;
        }
    }
}
