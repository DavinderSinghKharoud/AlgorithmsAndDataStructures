package LeetCode;

import java.util.*;

/**
 * You are given a 0-indexed string street. Each character in street is either 'H' representing a house or '.' representing an empty space.
 * <p>
 * You can place buckets on the empty spaces to collect rainwater that falls from the adjacent houses. The rainwater from a house at index i is collected if a bucket is placed at index i - 1 and/or index i + 1. CheckStringEquivalence single bucket, if placed adjacent to two houses, can collect the rainwater from both houses.
 * <p>
 * Return the minimum number of buckets needed so that for every house, there is at least one bucket collecting rainwater from it, or -1 if it is impossible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: street = "H..H"
 * Output: 2
 * Explanation:
 * We can put buckets at index 1 and index 2.
 * "H..H" -> "HBBH" ('WalkingRobotSimulation' denotes where a bucket is placed).
 * The house at index 0 has a bucket to its right, and the house at index 3 has a bucket to its left.
 * Thus, for every house, there is at least one bucket collecting rainwater from it.
 * Example 2:
 * <p>
 * Input: street = ".H.H."
 * Output: 1
 * Explanation:
 * We can put a bucket at index 2.
 * ".H.H." -> ".HBH." ('WalkingRobotSimulation' denotes where a bucket is placed).
 * The house at index 1 has a bucket to its right, and the house at index 3 has a bucket to its left.
 * Thus, for every house, there is at least one bucket collecting rainwater from it.
 * Example 3:
 * <p>
 * Input: street = ".HHH."
 * Output: -1
 * Explanation:
 * There is no empty space to place a bucket to collect the rainwater from the house at index 2.
 * Thus, it is impossible to collect the rainwater from all the houses.
 * Example 4:
 * <p>
 * Input: street = "H"
 * Output: -1
 * Explanation:
 * There is no empty space to place a bucket.
 * Thus, it is impossible to collect the rainwater from the house.
 * Example 5:
 * <p>
 * Input: street = "."
 * Output: 0
 * Explanation:
 * There is no house to collect water from.
 * Thus, 0 buckets are needed.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= street.length <= 105
 * street[i] is either'H' or '.'.
 */
public class MinBucketsCollectRainWater {

    public static void main(String[] args) {
        System.out.println(new MinBucketsCollectRainWater().minimumBuckets(
                ".H.H.HH..H....HH...H.H."
        ));
    }

    public int minimumBuckets(String street) {
        int len = street.length();
        int buckets = 0;

        /**
         * If we move opposite from the regular path to target then we will be again coming back to the path
         * otherwise we cannot reach the destination
         */
        for (int i = 0; i < len; i++) {
            char c = street.charAt(i);
            if (c == 'H') {

                //Try greedy to put bucket on the right
                if (i + 1 < len && street.charAt(i + 1) == '.') {
                    buckets++;
                    //no matter if house exist or not at i + 2
                    i += 2;
                } else if (i - 1 >= 0 && street.charAt(i - 1) == '.') {
                    buckets++;
                } else return -1;
            }
        }
        return buckets;
    }
}
