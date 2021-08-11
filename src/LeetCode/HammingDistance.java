package LeetCode;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 *
 * Note:
 * 0 ≤ x, y < 231.
 *
 * Example:
 *
 * Input: x = 1, y = 4
 *
 * Output: 2
 *
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * The above arrows point to positions where the corresponding bits are different.
 */
public class HammingDistance {

    public static int hammingDistance(int x, int y) {
        int count = 0;
        for( int i = 0; i < 32; i++ ){
            int extract1 = (x & 1);
            int extract2 = ( y & 1 );
            if( extract1 != extract2 ){
                count++;
            }
            x = (x >> 1);
            y = (y >> 1);
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println( hammingDistance(1, 4));
    }
}
