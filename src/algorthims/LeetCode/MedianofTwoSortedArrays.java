package algorthims.LeetCode;/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */


public class MedianofTwoSortedArrays {

    //O( m + n ) time complexity and O( m + n ) space complexity
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] arr = new int[len1 + len2];
        int len3 = arr.length;

        merge(nums1, nums2, arr);

        if ((len3 & 1) == 0) {
            //number is even
            int i = len3 / 2;
            return (double) (arr[i] + arr[i - 1]) / 2;
        } else {
            return arr[len3 / 2];
        }

    }

    public static void merge(int[] num1, int[] num2, int[] arr) {

        int index = 0;
        int left = 0;
        int right = 0;

        while (left < num1.length && right < num2.length) {

            if (num1[left] < num2[right]) {
                arr[index++] = num1[left++];
            } else {
                arr[index++] = num2[right++];
            }

        }

        while (left != num1.length) {
            arr[index++] = num1[left++];
        }

        while (right != num2.length) {
            arr[index++] = num2[right++];
        }
    }

    public static void main(String[] args) {

        System.out.println(findMedianSortedArrays2(new int[]{
                1, 3
        }, new int[]{
                2
        }));
    }

    //O( log( m + n) time complexity and O(1) space complexity
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        int left = 0;
        int right = len1;

        while (left <= right) {
            int partitionX = (left + right) / 2;
            // ( len1 + len2 + 1 )/2  are the total count of numbers
            int partitionY = (len1 + len2 + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == len1) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == len2) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //it is even
                if (((len1 + len2) & 1) == 0) {
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }

            } else if (maxLeftX > minRightY) { //we are too far on the right side for partition X , go left
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }

        return -1;
    }
}








