import java.util.*;

class Test {
    public static void main(String[] args) {
        Test o = new Test();
        int[] arr = {1, 2, 2, 2, 4, 5};
        List<Integer> lst =  Arrays.asList(1, 2, 2, 2, 4, 5);
        System.out.println(o.lowerBound(arr, 3));
        System.out.println(o.upperBound(arr, 3));
        System.out.println(o.lowerbound(lst, 3));
        System.out.println(o.upperbound(lst, 3));


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

     int lowerbound(List<Integer> arr, int target) {
        int l = 0;
        int r = arr.size() - 1;
        int mid;
        while (l < r) {
            mid = (l + r) >> 1;
            if (arr.get(mid) >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

     int upperbound(List<Integer> arr, int target) {
        int l = 0;
        int r = arr.size() - 1;
        int mid;
        while (l < r) {
            mid = (l + r + 1) >> 1;
            if (arr.get(mid) > target)
                r = mid - 1;
            else
                l = mid;
        }
        return l;
    }

}