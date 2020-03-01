package algorthims.LeetCode;

public class EasyTemperature {

    //Brute Force
    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Boolean noIncrease = false;

        for( int i = 0; i < T.length; i++ ){

            for( int j = i + 1; j < T.length; j++ ){

              if( T[j] - T[i] > 0 ){
                  noIncrease = true;
                  result[i] = j - i;
                  break;
              }
            }

            if( !noIncrease ){
                result[i] = 0;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[]  result = dailyTemperatures( new int[]{
                73, 74, 75, 71, 69, 72, 76, 73
        });

        for( int index = 0 ; index<result.length; index++ ){
            System.out.println( result[index] );
        }
    }
}
