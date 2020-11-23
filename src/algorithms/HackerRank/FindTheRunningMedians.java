package algorithms.HackerRank;


import java.text.DecimalFormat;
import java.util.*;

/**
 * The median of a set of integers is the midpoint value of the data set for which an equal number of integers are less than and greater than the value. To find the median, you must first sort your set of integers in non-decreasing order, then:
 * If your set contains an odd number of elements, the median is the middle element of the sorted sample. In the sorted set ,  is the median.
 * If your set contains an even number of elements, the median is the average of the two middle elements of the sorted sample. In the sorted set ,  is the median.
 * Given an input stream of  integers, you must perform the following task for each  integer:
 * Add the  integer to a running list of integers.
 * Find the median of the updated list (i.e., for the first element through the  element).
 * Print the list's updated median on a new line. The printed value must be a double-precision number scaled to  decimal place (i.e.,  format).
 * Input Format
 * The first line contains a single integer, , denoting the number of integers in the data stream.
 * Each line  of the  subsequent lines contains an integer, , to be added to your list.
 * Constraints
 * <p>
 * <p>
 * Output Format
 * After each new integer is added to the list, print the list's updated median on a new line as a single double-precision number scaled to  decimal place (i.e.,  format).
 * Sample Input
 * 6
 * 12
 * 4
 * 5
 * 3
 * 8
 * 7
 * Sample Output
 * 12.0
 * 8.0
 * 5.0
 * 4.5
 * 5.0
 * 6.0
 */
public class FindTheRunningMedians {

    //Time complexity O( n Log(n) ) and Space complexity O(n)

    //Can improve the time complexity using two priority queues already did before
    static double[] runningMedian(int[] a) {

        List<Integer> lst = new ArrayList<>();
        double[] res = new double[a.length];
        int resIndex = 0;
        for (int num : a) {

            int index = Collections.binarySearch(lst, num);
            if (index < 0) {
                index = -1 - (index);
            }
            lst.add(index, num);

            double median = getMedian(lst);
            DecimalFormat df = new DecimalFormat("#.#");
            res[resIndex++] = Double.parseDouble(df.format(median));

        }
        return res;
    }

    static double getMedian(List<Integer> lst) {
        int len = lst.size();
        if (len % 2 == 0) { //If it is even
            return (double) (lst.get(len / 2) + lst.get((len - 1) / 2)) / 2;
        } else { //It is odd
            return lst.get(len / 2);
        }
    }

    public static void main(String[] args) {

        //System.out.println(Arrays.toString(runningMedian(new int[]{0, 0})));

        System.out.println(Arrays.toString(runningMedian2(new int[]{12, 4, 5, 3, 8, 7})));

    }


    //O(log n) time complexity using AVL Trees and O(n) space
    static double[] runningMedian2(int[] a) {
        int len = a.length;
        double[] res = new double[len];

        if (a.length == 0) return res;

        Node root = null;

        for (int index = 0; index < len; index++) {
            root = insert(root, a[index]);

            res[index] = findMedian(root);
        }
        return res;
    }

    private static double findMedian(Node root) {
        int firstIndex = size / 2 + 1;
        int k1 = 0;
        int k2 = 0;
        double median = 0;

        k1 = findKthSmallestNum(root, firstIndex);
        median = k1 / 1.0;
        if (size % 2 == 0) {
            k2 = findKthSmallestNum(root, firstIndex - 1);
            median = (k1 + k2) / 2.0;
        }
        return median;
    }

    private static int findKthSmallestNum(Node node, int k) {
        if (k >= node.lcount + 1 && k < node.lcount + 1 + node.freq) return node.val;

        if (k <= node.lcount) {
            return findKthSmallestNum(node.left, k);
        } else {
            return findKthSmallestNum(node.right, k - node.lcount - node.freq);
        }


    }


    //Using AVL Tree

    static int size = 0;

    static class Node {
        Node left;
        Node right;
        int val;
        int height = 0;
        int freq = 1; //duplicates are allowed in stream
        int lcount = 0; // number of items in left subtree
        int rcount = 0; // number of items in right subtree

        public Node(int val) {
            this.val = val;
        }
    }

    static Node insert(Node node, int val) {

        if (node == null) {
            size++;
            return new Node(val);
        }

        if (node.val < val) {
            node.right = insert(node.right, val);
            node.rcount++;
        } else if (node.val > val) {
            node.left = insert(node.left, val);
            node.lcount++;
        } else {
            node.freq++;
            size++;
            return node;
        }

        //Balance the tree
        int balance = getHeight(node.left) - getHeight(node.right);

        if (balance > 1) {

            if (getHeight(node.left.left) >= getHeight(node.left.right)) {
                //L - L case
                node = rightRotate(node);
            } else {
                //L - R case
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }

        } else if (balance < -1) {

            if (getHeight(node.right.right) >= getHeight(node.right.left)) {
                node = leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }

        node.height = setHeight(node);

        return node;
    }


    static Node rightRotate(Node node) {

        Node newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;

        //*************************************************************
        //Update count
        node.lcount = (node.left != null) ? node.left.lcount + node.left.rcount + node.left.freq : 0;
        newNode.rcount = node.lcount + node.rcount + node.freq;
        //*************************************************************

        //Update heights
        node.height = setHeight(node);
        newNode.height = setHeight(newNode);

        return newNode;
    }

    static Node leftRotate(Node node) {
        Node newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;


        //*************************************************************
        //Update count
        node.rcount = (node.right != null) ? node.right.lcount + node.right.rcount + node.right.freq : 0;
        newNode.lcount = node.lcount + node.rcount + node.freq;
        //*************************************************************

        //Update heights
        node.height = setHeight(node);
        newNode.height = setHeight(newNode);


        return newNode;

    }

    static int getHeight(Node node) {
        if (node == null) return -1;
        return node.height;
    }

    static int setHeight(Node node) {
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }


}
