package algorthims.LeetCode;

public class JumpGame {

    public static void main(String[] args) {
        System.out.println( checkReach( new int[]{
                3,2,1,0,4
        }) );
    }

    private static boolean checkReach(int[] arry) {
        int index = 0;
        int finalIndex = arry.length - 1;
        while ( index != finalIndex && index<arry.length ){

            int value = arry[index];
            if( value == 0){
                return false;
            }
            index+=value;

        }

        return true;
    }
}
