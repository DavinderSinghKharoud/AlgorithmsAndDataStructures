import java.util.*;

class Test {
    public static void main(String[] args) {
        Test o = new Test();
        int[] arr = {1, 2, 2};
        List<Integer> lst =  Arrays.asList(1, 2, 2, 2, 4, 5);
//        System.out.println(o.lowerBound(arr, 3));
//        System.out.println(o.upperBound(arr, 3));
//        System.out.println(o.lowerbound(lst, 3));
//        System.out.println(o.upperbound(lst, 3));

        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println(segmentTree.query(0, 1));
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


      static class SegmentTree {

        int[] arr;
        int[] nodes;
        int n;

        public SegmentTree(int[] nodes){
            arr = new int[( getSize(nodes.length) * 2 + 1)];
            n = nodes.length;
            this.nodes = nodes;
            construct(0, n - 1, 0);
        }

        void construct(int l, int h, int pos ){
            if( l == h ){
                arr[pos] = nodes[l];
            }else{
                int mid = l + (h - l)/2;
                int p = pos << 1;
                construct(l, mid, p + 1);
                construct(mid + 1, h, p + 2);
                arr[pos] = Math.max( arr[p + 1], arr[p + 2]);
            }
        }

        void update(int node, int value ){
            update(0, n - 1, 0, node, value);
        }

        private void update(int l, int h, int pos, int node, int value ){
            if( l == h ){
                arr[pos] = value;
            }else{
                int mid = l + (h - l)/2;
                int p = pos << 1;
                if( node <= mid ){
                    update(l, mid, p + 1, node, value );
                }else{
                    update(mid + 1, h, p + 2, node, value);
                }

                arr[pos] = Math.max( arr[p + 1], arr[p + 2]);
            }

        }

        long query(int l, int r ){
            return query(0, n - 1, 0, l, r);
        }

        private int query(int start, int end, int pos, int sumOfDigitsInBaseK, int b ){
            if( start > b || end < sumOfDigitsInBaseK ) return Integer.MIN_VALUE;

            if( start >= sumOfDigitsInBaseK && end <= b ){
                return arr[pos];
            }else{
                int mid = start + (end - start)/2;
                int p = pos << 1;
                return Math.max( query(start, mid, p + 1, sumOfDigitsInBaseK, b ), query(mid + 1, end, p + 2, sumOfDigitsInBaseK, b) );
            }
        }


        int getSize(int len ){
            if( len < 2 ) return 1;

            if( (len & (len - 1)) == 0 ) return len;

            while( ( len & (len - 1) ) != 0 ){
                len = len & (len - 1);
            }

            return len << 1;
        }
    }


}