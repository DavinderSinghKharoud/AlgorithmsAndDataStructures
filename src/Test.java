import java.util.*;

class Test {
    public static void main(String[] args) {
        Test o = new Test();
        int[] arr = {1, 2, 2, 2, 4, 5};
        List<Integer> lst = Arrays.asList(1, 2, 2, 2, 4, 5);
        System.out.println(o.lowerBound(arr, 3));
        System.out.println(o.upperBound(arr, 3));
        System.out.println(o.lowerBoundSeg(arr, 3));
        System.out.println(o.upperBoundSeg(arr, 3));

    }

    int lowerBound(int[] arr, int val) {
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val)
                r = mid;
            else
                l = mid;
        }
        return r;
    }

    int upperBound(int[] arr, int val) {
        int l = -1, r = arr.length;
        while (l + 1 < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] <= val) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l + 1;
    }

    //Gives strict lowerBound that previous number would be smaller than the target
    int lowerBoundSeg(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l;
    }

    //Gives strict upperBound that next number would be greater than the target
    int upperBoundSeg(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l + 1) >> 1;
            if (arr[mid] <= val) {
                l = mid;
            } else
                r = mid - 1;
        }
        return l;
    }
}