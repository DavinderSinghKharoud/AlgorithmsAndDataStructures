package Preparation;

public class RotatedArrayMinimum {
    // DO NOT MODIFY THE LIST
    public int findMin(final List<Integer> lst) {
        int len = lst.size();
        if( lst.get(0) <= lst.get(len - 1)) return lst.get(0);
        int first = lst.get(0), last = lst.get(len - 1);
        int start = 0, end = lst.size() - 1;

        while(start < end){
            int mid = start + (end - start)/2;
            if( lst.get(mid) > first){
                start = mid + 1;
            }else end = mid;
        }
        return lst.get(end);

    }
}
