package Trees;

import java.util.*;

//Implementation of the segment tree where we can do minimum range queries

/**
 * Time and space complexity to create the segment tree is O(n)
 * To perform search or update operation time complexity is O( log(n) )
 */
public class SegmentTree {

    int[] tree;

    public int[] constructTree(int[] arr) {
        int len = arr.length;
        //segment tree is always have length 2 * (power of 2 for len) + 1
        int nextPower2 = nextPowerTwo(len);

        tree = new int[nextPower2 * 2 + 1];

        Arrays.fill(tree, Integer.MIN_VALUE);

        constructMinSegmentTree(arr, tree, 0, len - 1, 0);
        return tree;
    }

    public long rangeMinQuery(int[] tree, int qlow, int qhigh, int len) {
        if( qhigh >= len || qlow > qhigh) return -1;
        return rangeMinimumQuery(tree, 0, len - 1, qlow, qhigh, 0);
    }

    private long rangeMinimumQuery(int[] tree, int low, int high, int qlow, int qhigh, int pos) {
        if (qlow <= low && qhigh >= high) { //completely overlapp
            return tree[pos];
        }

        if (qlow > high || qhigh < low) { //no overlapp
            return Integer.MAX_VALUE;
        }

        //else partial overlapp
        int mid = (low + high) / 2;
        return Math.min(rangeMinimumQuery(tree, low, mid, qlow, qhigh, 2 * pos + 1),
                rangeMinimumQuery(tree, mid + 1, high, qlow, qhigh, 2 * pos + 2));


    }

    private void constructMinSegmentTree(int[] arr, int[] tree, int low, int high, int pos) {

        if (low == high) {
            tree[pos] = arr[low];
            return;
        }

        int mid = (low + high) / 2; //As segment tree is always of odd size 2*n + 1

        constructMinSegmentTree(arr, tree, low, mid, 2 * pos + 1);
        constructMinSegmentTree(arr, tree, mid + 1, high, 2 * pos + 2);

        tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);

    }

    private int nextPowerTwo(int num) {
        if (num == 0) return 1;

        if (num > 0 && (num & (num - 1)) == 0) { // (num & (num - 1) ) == 0 is only when it is even number
            return num;
        }

        while ((num & (num - 1)) > 0) {
            num = num & (num - 1);
        }

        return num << 1; //left shift to find next power of 2
    }


    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree();
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        int len = arr.length;
        arr = segmentTree.constructTree(arr);

        System.out.println(segmentTree.rangeMinQuery(arr, 4, 4, len));
    }

}
