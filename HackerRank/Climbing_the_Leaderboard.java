package HackerRank;

/**
 * Alice is playing an arcade game and wants to climb to the
 * top of the leaderboard and wants to track her ranking.
 * The game uses Dense Ranking, so its leaderboard works like this:
 *
 * The player with the highest score is ranked number  on the leaderboard.
 * Players who have equal scores receive the same ranking number,
 * and the next player(s) receive the immediately following ranking number.
 */

public class Climbing_the_Leaderboard {


    static int[] climbingLeaderboard(int[] scores, int[] alice) {

        int[] ranking = new int[scores.length];
        int[] finalArr = new int[alice.length];

        ranking[0] = 1;
        for(int i = 1; i<scores.length; i++){
            if(scores[i] == scores[i-1]){
                ranking[i] = ranking[i-1];
            }
            else{
                ranking[i] = ranking[i-1] + 1;
            }
        }

        for( int i = 0; i < alice.length; i++){
            int aScore = alice[i];
            if(aScore>scores[0]){
                finalArr[i] = 1;
            }else if(aScore < scores[scores.length-1]){
                finalArr[i] = ranking[ranking.length - 1]+1;
            }else{
                int index = binarySearchNum(scores, aScore);
                finalArr[i] = ranking[index];
            }
        }
        return finalArr;
    }

    private static int binarySearchNum(int[] scores, int key) {

        int low = 0;
        int hi = scores.length - 1;

        while (low<=hi){
            int mid = low + (hi - low)/2;

            if(scores[mid] == key){
                return mid;
            }else if(scores[mid] < key && key< scores[mid - 1]){
                return mid;
            }else if(scores[mid] > key && key >= scores[mid + 1]){
                return mid+1;
            }else if(scores[mid] < key){
                hi = mid-1;
            }else if(scores[mid] > key){
                low = mid + 1;
            }
        }
        return -1;

    }


//    static int[] climbingLeaderboard(int[] scores, int[] alice) {
//        int rank;
//        int []ranking = new int[alice.length];
//
//        for (int indexAl = 0; indexAl < alice.length; indexAl++) {
//
//            rank = 1;
//
//            for (int indexSc = 0; indexSc < scores.length-1; indexSc++) {
//
//                if(alice[indexAl] >= scores[indexSc]){
//                    break;
//                }
//
//
//                if(scores[indexSc]>scores[indexSc+1]){
//                    rank+=1;
//                }
//                if(indexSc == scores.length-2 && alice[indexAl] >= scores[indexSc+1]){
//                    break;
//                }else if(indexSc == scores.length-2){
//                    rank+=1;
//                }
//            }
//
//            ranking[indexAl] = rank;
//        }
//
//        return ranking;
//    }

    public static void main(String[] args) {
        int[] scores = new int[]{
                100, 100, 50, 40, 40, 20, 10
        };
        int[] alice = new int[]{
                5,25,50,120
        };
        int i[] = climbingLeaderboard(scores, alice);
        for (int num : i) {
            System.out.println(num);
        }
    }
}
