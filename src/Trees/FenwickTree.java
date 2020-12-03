package Trees;

/**
 * * Write a program to implement fenwick or binary indexed tree
 * *
 * * A Fenwick tree or binary indexed tree is a data structure providing efficient methods
 * * for calculation and manipulation of the prefix sums of a table of values.
 * *
 * * Space complexity for fenwick tree is O(n)
 * * Time complexity to create fenwick tree is O(nlogn)
 * * Time complexity to update value is O(logn)
 * * Time complexity to get prefix sum is O(logn)
 */
public class FenwickTree {


    public int[] createTree(int[] input) {
        int[] balancedTree = new int[input.length + 1];

        for (int index = 1; index < input.length; index++) {
            updateAndAddValue(balancedTree, input[index - 1], index);
        }

        return balancedTree;
    }

    public void updateAndAddValue(int[] arr, int value, int index) {

        while (index < arr.length) {
            arr[index] += value;
            index = getNextIndex(index);
        }
    }

    /**
     * To get next
     * 1) 2's complement of get minus of index
     * 2) AND this with index
     * 3) Add it to index
     */
    public int getNextIndex(int index) {
        return index + (index & -index);
    }

    public int getSum(int[] arr, int index) {
        index = index + 1;
        int sum = 0;

        while (index > 0) {
            sum += arr[index];
            index = getParent(index);
        }

        return sum;
    }

    /**
     * To get parent
     * 1) 2's complement to get minus of index  ( We can find the 2's complement by reversing all the bits and add 1 )
     * 2) AND this with index
     * 3) Subtract that from index
     */
    public int getParent(int index) {
        return index - (index & -index);
    }

    public int sumRange(int[] arr, int index1, int index2 ){
        return getSum(arr, index2) - getSum(arr, index1 - 1); //Inclusive
    }
    public static void main(String[] args) {

        FenwickTree fenwickTree = new FenwickTree();
        int[] input = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arr = fenwickTree.createTree(input);
        System.out.println(fenwickTree.getSum(arr, 3));
        //fenwickTree.updateAndAddValue(arr, 3, 1);
        System.out.println(fenwickTree.getSum(arr, 3));
        System.out.println(fenwickTree.sumRange(arr, 1, 3));
    }
}
