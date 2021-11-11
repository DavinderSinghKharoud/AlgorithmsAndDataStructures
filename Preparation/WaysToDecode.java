package Preparation;

public class WaysToDecode {
    public static void main(String[] args) {
        System.out.println(new WaysToDecode().numDecodings("123"));
    }

    public int numDecodings(String s) {
        int len = s.length();
        int[] res = new int[len + 1];
        int mod = (int)(1e9 + 7);
        res[0] = 1;

        for(int i = 0; i < len; i++){
            char curr = s.charAt(i);
            //Single digit is always possible
            if (curr > '0')
                res[i + 1] = res[i];
            //Check if two digits are possible
            if (i - 1 >= 0) {

                char prev = s.charAt(i - 1);
                if( prev == '1' || ( prev == '2' && curr <= '6')){
                    res[i + 1] = sum(res[i + 1], res[i - 1], mod);
                }
            }
            // System.out.println(Arrays.toString(res));
        }
        return res[len];
    }

    int sum(int a, int b, int mod) {
        return (a + b) % mod;
    }
}
