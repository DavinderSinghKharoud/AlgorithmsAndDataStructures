package algorithms.HackerRank;

import java.util.*;

/**
 * Given an array, your goal is to find, for each element, the largest subarray containing it whose cost is at least .
 * Specifically, let  be an array of length , and let  be the subarray from index  to index . Also,
 * Let  be the largest number in .
 * Let  be the smallest number in .
 * Let  be the bitwise OR of the elements of .
 * Let  be the bitwise AND of the elements of .
 * The cost of , denoted , is defined as
 *
 * The size of  is defined as .
 * You are given the array  and and an integer . For each index  from  to , your goal is to find the largest size of any subarray  such that  and .
 * Consider, array  and . The possible sub-arrays and their costs would be as follows:
 * image
 * Complete the function costlyIntervals which takes two integers  and  as first line of input, and array  in the second line of input. Return an array of  integers, where the  element contains the answer for index  of the input array, . Every element of the output array denotes the largest size of a subarray containing  whose cost is at least , or if there is no such subarray.
 * Constraints
 *
 *
 *
 * Subtasks
 * For  of the maximum score, .
 * For  of the maximum score, .
 * Sample Input
 * ,
 *
 * Sample Output
 *
 * Explanation
 * In this example, we have . There is only one subarray whose cost is at least , and that is , since . Its size is . Thus, for  and , the answer is , and for the others, .
 */
public class CostlyIntervals {

    static int[] costlyIntervals(int n, int k, int[] arr) {

        SegmentTree minTree = new SegmentTree();
        SegmentTree maxTree = new SegmentTree();
        SegmentTree andTree = new SegmentTree();
        SegmentTree orTree = new SegmentTree();
        SegmentTree resTree = new SegmentTree();

        int[] minArr = minTree.constructTree(arr, Type.MINIMUM);
        int[] maxArr = maxTree.constructTree(arr, Type.MAXIMUM);
        int[] andArr = andTree.constructTree(arr, Type.AND);
        int[] orArr = orTree.constructTree(arr, Type.OR);

        int[] res = new int[n];
        Arrays.fill(res, -1);
        int[] resArr = resTree.constructTree(res, Type.MAXIMUM);

        for (int index1 = 0; index1 < n; index1++) {

            for (int index2 = index1 + 1; index2 < n; index2++) {
                long currOr = orTree.rangeORQuery(orArr, index1, index2, n);
                long currAnd = andTree.rangeAndQuery(andArr, index1, index2, n);
                long currMax = maxTree.rangeMaxQuery(maxArr, index1, index2, n);
                long currMin = minTree.rangeMinQuery(minArr, index1, index2, n);
                long cost = (currOr - currAnd) - (currMax - currMin);

                if (cost >= k) {
                    resTree.updateSegmentTreeRangeMax(resArr, index1, index2, index2 - index1 + 1, 0, n - 1, 0);
                }

            }
        }

        for (int index = 0; index < n; index++) {
            res[index] = resTree.binarySearch(resArr, 0, n - 1, 0, index);
        }
        return res;
    }


    public static void main(String[] args) {

        int[] arr = costlyIntervals(5, 6, new int[]{2, 4, 3, 1, 7});
        System.out.println(Arrays.toString(arr));
    }


    static enum Type {
        MINIMUM,
        MAXIMUM,
        AND,
        OR

    }

    static class SegmentTree {

        int[] tree;

        public int[] constructTree(int[] arr, Type type) {
            int len = arr.length;
            //segment tree is always have length 2 * (power of 2 for len) + 1
            int nextPower2 = nextPowerTwo(len);

            tree = new int[nextPower2 * 2 + 1];

            Arrays.fill(tree, Integer.MIN_VALUE);


            switch (type) {
                case MINIMUM:
                    constructMinSegmentTree(arr, tree, 0, len - 1, 0);
                    break;
                case MAXIMUM:
                    constructMaxSegmentTree(arr, tree, 0, len - 1, 0);
                    break;
                case AND:
                    constructAndSegmentTree(arr, tree, 0, len - 1, 0);
                    break;
                case OR:
                    constructORSegmentTree(arr, tree, 0, len - 1, 0);
                    break;
            }


            return tree;
        }

        public long rangeORQuery(int[] tree, int qlow, int qhigh, int len) {
            return rangeORQueryAbstract(tree, 0, len - 1, qlow, qhigh, 0);
        }

        private long rangeORQueryAbstract(int[] tree, int low, int high, int qlow, int qhigh, int pos) {
            if (qlow <= low && qhigh >= high) {
                return tree[pos];
            }
            if (qlow > high || qhigh < low) {
                return Integer.MAX_VALUE;
            }

            int mid = (low + high) / 2;
            long left = rangeORQueryAbstract(tree, low, mid, qlow, qhigh, 2 * pos + 1);
            long right = rangeORQueryAbstract(tree, mid + 1, high, qlow, qhigh, 2 * pos + 2);
            if (left != Integer.MAX_VALUE && right != Integer.MAX_VALUE) {
                return (left | right);
            } else if (left == Integer.MAX_VALUE) {
                return right;
            }
            return left;
        }


        private void constructORSegmentTree(int[] arr, int[] tree, int low, int high, int pos) {
            if (low == high) {
                tree[pos] = arr[low];
                return;
            }

            int mid = (low + high) / 2;

            constructORSegmentTree(arr, tree, low, mid, 2 * pos + 1);
            constructORSegmentTree(arr, tree, mid + 1, high, 2 * pos + 2);

            tree[pos] = (tree[2 * pos + 1] | tree[2 * pos + 2]);
        }

        private void constructAndSegmentTree(int[] arr, int[] tree, int low, int high, int pos) {
            if (low == high) {
                tree[pos] = arr[low];
                return;
            }

            int mid = (low + high) / 2;

            constructAndSegmentTree(arr, tree, low, mid, 2 * pos + 1);
            constructAndSegmentTree(arr, tree, mid + 1, high, 2 * pos + 2);

            tree[pos] = (tree[2 * pos + 1] & tree[2 * pos + 2]);
        }

        private void constructMaxSegmentTree(int[] arr, int[] tree, int low, int high, int pos) {
            if (low == high) {
                tree[pos] = arr[low];
                return;
            }

            int mid = (low + high) / 2;

            constructMaxSegmentTree(arr, tree, low, mid, 2 * pos + 1);
            constructMaxSegmentTree(arr, tree, mid + 1, high, 2 * pos + 2);

            tree[pos] = Math.max(tree[2 * pos + 1], tree[2 * pos + 2]);
        }

        public long rangeAndQuery(int[] tree, int qlow, int qhigh, int len) {
            return rangeAndQueryAbstract(tree, 0, len - 1, qlow, qhigh, 0);
        }


        public long rangeMaxQuery(int[] tree, int qlow, int qhigh, int len) {
            return rangeMaximumQuery(tree, 0, len - 1, qlow, qhigh, 0);
        }


        public long rangeMinQuery(int[] tree, int qlow, int qhigh, int len) {
            if (qhigh >= len || qlow > qhigh) return -1;
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

        private long rangeMaximumQuery(int[] tree, int low, int high, int qlow, int qhigh, int pos) {
            if (qlow <= low && qhigh >= high) {
                return tree[pos];
            }
            if (qlow > high || qhigh < low) {
                return Integer.MIN_VALUE;
            }

            int mid = (low + high) / 2;
            return Math.max(rangeMaximumQuery(tree, low, mid, qlow, qhigh, 2 * pos + 1),
                    rangeMaximumQuery(tree, mid + 1, high, qlow, qhigh, 2 * pos + 2));
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

        private long rangeAndQueryAbstract(int[] tree, int low, int high, int qlow, int qhigh, int pos) {
            if (qlow <= low && qhigh >= high) {
                return tree[pos];
            }
            if (qlow > high || qhigh < low) {
                return Integer.MAX_VALUE;
            }

            int mid = (low + high) / 2;
            long left = rangeAndQueryAbstract(tree, low, mid, qlow, qhigh, 2 * pos + 1);
            long right = rangeAndQueryAbstract(tree, mid + 1, high, qlow, qhigh, 2 * pos + 2);
            if (left != Integer.MAX_VALUE && right != Integer.MAX_VALUE) {
                return (left & right);
            } else if (left == Integer.MAX_VALUE) {
                return right;
            }
            return left;
        }

        public void updateSegmentTreeRangeMax(int segmentTree[], int startRange, int endRange, int delta, int low, int high, int pos) {
            if (low > high || startRange > high || endRange < low) {
                return;
            }

            if (low == high) {
                segmentTree[pos] = Math.max(segmentTree[pos], delta);
                return;
            }

            int middle = (low + high) / 2;
            updateSegmentTreeRangeMax(segmentTree, startRange, endRange, delta, low, middle, 2 * pos + 1);
            updateSegmentTreeRangeMax(segmentTree, startRange, endRange, delta, middle + 1, high, 2 * pos + 2);
            segmentTree[pos] = Math.max(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
        }

        public Integer binarySearch(int[] segmentTree, int low, int high, int pos, int index) {
            if (low > high) return null;

            if (low == high && high == index) {
                return segmentTree[pos];
            }

            int mid = (low + high) / 2;

            if (index <= mid) return binarySearch(segmentTree, low, mid, 2 * pos + 1, index);
            return binarySearch(segmentTree, mid + 1, high, 2 * pos + 2, index);

        }
    }
}
