package ProjectEuler;

import java.util.ArrayList;
import java.util.List;

public class LongestCollatzSequence {

    public static void main(String[] args) {

//        getMax( 1000000 );
//        //System.out.println( getSequence(13));
//    }
//
//    private static void getMax(int limit) {
//
//        long max = -1;
//        int maxNum = 0;
//
//        for( int i = 1; i<limit; i++){
//
//            long count = getSequence(i);
//            if( max<= count ){
//                max = count;
//                maxNum = i;
//            }
//
//        }
//        System.out.println( maxNum );
//    }
//
//    private static long getSequence(int num) {
//
//        if( num <= 1){
//            return 1;
//        }
//
//        if( num%2 == 0){
//            num = num/2;
//            return 1 + getSequence( num );
//        }else{
//            num = 3*num + 1;
//            return 1 + getSequence( num );
//        }
//    }
//}
        int limit = 100000;
        int size = 0, max = 1, j = 0;
        for (int i = 2; i < limit; i++) {
            size = getChainSize(i);
            if (size > max) {
                max = size;
                j = i;
            }
        }
        System.out.println( j);
    }

    private static int getChainSize(int n) {

        long num = n;
        List<Long> list = new ArrayList<>();
        int size = 0;
        while (num != 1) {
            list.add(num);
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = 3 * num + 1;
            }
        }
        size = list.size();
        return size;
    }
}