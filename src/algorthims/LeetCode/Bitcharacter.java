package algorthims.LeetCode;

public class Bitcharacter {

    public static void main(String[] args) {
        int []bit = {1,0,0};
        System.out.println( isOneBitCharacter(bit));
    }
    public static boolean isOneBitCharacter(int[] bits) {

        int i = 0;
        while ( i<bits.length - 1){
            i+=bits[i] + 1;
        }

        return i==bits.length - 1;
    }
}
