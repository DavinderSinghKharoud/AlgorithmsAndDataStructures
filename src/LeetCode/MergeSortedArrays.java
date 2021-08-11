package LeetCode;

public class MergeSortedArrays {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;
        n--;
        int index = nums1.length - 1;

        while ( index >= 0 ){

            if (m < 0) {
                nums1[index] = nums2[n--];
            } else if (n < 0) {
                nums1[index] = nums1[m--];
            } else {
                if (nums1[m] > nums2[n]) {
                    nums1[index] = nums1[m--];
                } else {
                    nums1[index] = nums2[n--];
                }
            }
            index--;
        }
    }


    public static void main(String[] args) {

        int[] m = new int[]{1, 2, 3, 0, 0, 0};
        int[] n = new int[]{2, 5, 6};

        merge(m, 3, n, 3);

        for (int num : m) {
            System.out.println(num);
        }
    }
}
