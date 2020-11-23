package algorithms.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class ReverseBits {
    public static int reverseBits(int n) {
        List<Integer> lst = new ArrayList<>();
        int index = 0;
        while (index < 32){
            int t = n & 1;
            lst.add( t );
            n = n >> 1;
            index ++;
        }

        int res = 0;
        for( int i = 0; i<lst.size(); i++ ){
            res  = res << 1;
            res = res | lst.get(i);
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println( reverseBits1(43261596));
    }

    public static int reverseBits1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int rightbit = n & 1;
            n = n >> 1;
            res = i < 31 ?  (res | rightbit) << 1 : (res | rightbit);
        }
        return res;
    }

}
