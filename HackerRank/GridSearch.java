package HackerRank;

public class GridSearch {

    static String gridSearch(String[] G, String[] P) {

        if( P.length > G.length || P.length == 0){
            return "No";
        }

        int count = 0;

        for( int i = 0; i< G.length; i++){

            if( G[i] .contains( P[count]) ){

                count++;

                if( count == P.length){
                    break;
                }

            }else if( G[i] .contains(P[0])){
                count = 1;
            }else {
                count = 0;
            }

        }

        if( count ==  P.length){
            return "Yes";
        }else{
            return "No";
        }

    }
    public static void main(String[] args) {
        String []G = new String[]{
                "1234567890",
                "0987654321",
                "1111111111",
                "1111111111",
                "2222222222"
        };

        String []P = new String[]{
                "876543",
                "111111",
                "111111"
        };

        System.out.println(gridSearch( G, P));

    }
}
