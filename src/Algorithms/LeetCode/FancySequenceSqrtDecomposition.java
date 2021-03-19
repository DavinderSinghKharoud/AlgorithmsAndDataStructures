package Algorithms.LeetCode;

import java.util.*;

public class FancySequenceSqrtDecomposition {

    /**
     * Time complexity O( sqrt(n)) for all operations except getIndex which is O(1) using square root decomposition
     * @param args
     */
    public static void main(String[] args) {

        FancySequenceSqrtDecomposition fancy = new FancySequenceSqrtDecomposition();
        fancy.append(6);
        System.out.println(fancy.getIndex(0));
        System.out.println(fancy.getIndex(4));
        System.out.println(fancy.getIndex(0));
//
//        fancy.append(2);   // fancy sequence: [2]
//        fancy.addAll(3);   // fancy sequence: [2+3] -> [5]
//        fancy.append(7);   // fancy sequence: [5, 7]
//        fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -> [10, 14]
//        fancy.getIndex(0); // return 10
//        fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -> [13, 17]
//        fancy.append(10);  // fancy sequence: [13, 17, 10]
//        fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
//        System.out.println(fancy.getIndex(0)); // return 26
//        System.out.println(fancy.getIndex(1)); // return 34
//        System.out.println(fancy.getIndex(2)); // return 20
    }

    List<Operation> buckets = new ArrayList<>();
    List<Integer> currBucket = new ArrayList<>();List<List<Integer>> arr = new ArrayList<>();
    Operation currOperation = new Operation();
    int mod = (int) 1e9 + 7;
    int maxBucketSize =  (int) Math.sqrt(100000);

    public FancySequenceSqrtDecomposition() {
    }

    public void append(int val) {
        // Check if curr bucket size reach maxLimit
        if (currBucket.size() % maxBucketSize == 0 && currBucket.size() > 0) {
            buckets.add(currOperation);
            currOperation = new Operation();
            arr.add(currBucket);
            currBucket = new ArrayList<>();
        }

        // Update the curr Bucket before adding new number
        for (int i = 0; i < currBucket.size(); i++) {
            int updated = getAns(currBucket.get(i) * currOperation.mul + currOperation.sum);
            currBucket.set(i, updated);
        }

        currBucket.add(val);
        currOperation = new Operation();
    }

    public void addAll(int inc) {
        // Update all the previous buckets
        for (Operation curr : buckets) {
            curr.sum = (curr.sum + inc) % mod;
        }

        // Update the current operation
        currOperation.sum = (currOperation.sum + inc) % mod;
    }

    public void multAll(int m) {

        // Update all the previous buckets
        for (Operation curr : buckets) {
            curr.mul = (curr.mul * m) % mod;
            curr.sum = (curr.sum * m) % mod;
        }

        // Update the current operation
        currOperation.mul = (currOperation.mul * m) % mod;
        currOperation.sum = (currOperation.sum * m) % mod;
    }

    public int getIndex(int idx) {
        int bucket = idx / maxBucketSize;
        int totalBuckets = buckets.size() + ((currBucket.size() == 0) ? 0 : 1);
        if (bucket >= totalBuckets)
            return -1;

        int index = idx % maxBucketSize;
        if (bucket < buckets.size()) {
            Operation operation = buckets.get(bucket);
            return getAns(arr.get(bucket).get(index) * operation.mul + operation.sum);
        } else if (index < currBucket.size()) {
            return getAns(currBucket.get(index) * currOperation.mul + currOperation.sum);
        }
        return -1;
    }

    private int getAns(long n) {
        n %= mod;
        if (n < 0) {
            n += mod;
        }
        return (int) n;
    }

    public static class Operation {
        long mul = 1, sum = 0;
    }
}
