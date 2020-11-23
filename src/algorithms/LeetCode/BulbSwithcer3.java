package algorithms.LeetCode;

public class BulbSwithcer3 {
    public static int numTimesAllBlue(int[] light) {
        int BulbMax = -1;
        int res = 0;
        for(int i = 0; i < light.length; i++) {
            BulbMax = Math.max(BulbMax, light[i]);
            if(BulbMax == i + 1) res++;
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println( numTimesAllBlue(new int[]{
                4,1,2,3
        }));
    }
}
