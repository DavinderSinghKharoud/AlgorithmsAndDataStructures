package LeetCode;

public class InsertIntervals {
    int n, m;
    public int[][] insert(int[][] intervals, int[] newI) {
        n = intervals.length;
        if(n == 0 ){
            int[][] a = new int[1][2];
            a[0] = newI;
            return a;
        }
        m = intervals[0].length;

        List<int[]> unique = new ArrayList<>();

        for(int[] interval: intervals){
            if( areOverlapped(interval, newI)){
                newI = merge(interval, newI);
            }else unique.add(interval);
        }

        int[][] ans = new int[unique.size() + 1][2];

        //find the place to put newI
        int i = 0;
        for(int[] interval: unique){
            System.out.println(interval[0] + " " + interval[1]);
            if(newI != null && newI[1] < interval[0]){
                ans[i++] = newI;
                newI = null;
            }
            ans[i++] = interval;
        }
        if(newI!= null)ans[i] = newI;
        return ans;
    }

    int[] merge(int[] i1, int[] i2){
        return new int[] {Math.min(i1[0], i2[0]), Math.max(i1[1], i2[1])};
    }
    boolean areOverlapped(int[] i1, int[] i2){
        return !( (i1[0] < i2[0] && i1[1] < i2[0]) ||
                (i2[0] < i1[0] && i2[1] < i1[0]));
    }
}
