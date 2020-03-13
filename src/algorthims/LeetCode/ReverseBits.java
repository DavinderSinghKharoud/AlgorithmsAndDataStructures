package algorthims.LeetCode;

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

        System.out.println( reverseBits(43261596));
    }

}
