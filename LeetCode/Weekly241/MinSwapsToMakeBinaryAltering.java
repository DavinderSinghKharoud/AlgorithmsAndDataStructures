package LeetCode.Weekly241;

public class MinSwapsToMakeBinaryAltering {
    public static void main(String[] args) {
        MinSwapsToMakeBinaryAltering o = new MinSwapsToMakeBinaryAltering();
        System.out.println(o.minSwaps("1110000000100001010100101010000101010101001000001110101000010111101100000111110001000111010111101100001100001001100101011110100011111100000000100011111011110111111011110111010100111101011111111101101100101010110000011110110100101111000100000001100000"));
    }

    public int minSwaps(String s) {
        int len = s.length();
        int swaps = 0;
        int countOnes = 0, countZeros = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '1')
                countOnes++;
            else
                countZeros++;
        }

        if (len % 2 == 0) {
            if (countZeros != countOnes)
                return -1;
        } else {
            if ((int) Math.abs(countOnes - countZeros) > 1)
                return -1;
        }

        char[] fixed = new char[len];
        char[] fixed2 = new char[len];
        if (len % 2 == 0) {
            fixed[0] = '1';
            for (int i = 1; i < len; i++) {
                if (fixed[i - 1] == '1') {
                    fixed[i] = '0';
                } else
                    fixed[i] = '1';
            }
            fixed2[0] = '0';
            for (int i = 1; i < len; i++) {
                if (fixed2[i - 1] == '1') {
                    fixed2[i] = '0';
                } else
                    fixed2[i] = '1';
            }


            // True for 1
            for (int i = 0; i < len; i++) {
                if (fixed[i] != arr[i])
                    swaps++;
            }
            int curr = 0;
            for (int i = 0; i < len; i++) {
                if (fixed2[i] != arr[i])
                    curr++;
            }
            swaps = Math.min(swaps, curr);
            return swaps / 2;

        } else {
            fixed[0] = (countOnes > countZeros) ? '1' : '0';
            for (int i = 1; i < len; i++) {
                if (fixed[i - 1] == '1') {
                    fixed[i] = '0';
                } else
                    fixed[i] = '1';
            }
        }
        // True for 1
        for (int i = 0; i < len; i++) {
            if (fixed[i] != arr[i])
                swaps++;
        }
        return swaps / 2;
    }
}
