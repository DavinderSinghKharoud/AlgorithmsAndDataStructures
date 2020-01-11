package ProblemSolvingHackerRank;

/**
 * There is a collection of input strings and a collection of query strings.
 * For each query string, determine how many times it occurs in the list of input strings.
 */
public class SparseArray {

    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {

        int result[] = new int[queries.length];

        for(int i = 0; i<queries.length; i++){

            for(int j = 0; j<strings.length; j++){

                if(queries[i].equals(strings[j])){

                    int value = result[i];
                    result[i] = value+1;


                }
            }

        }

        return result;
    }

    public static void main(String[] args) {

        String []strings = {"ab","ab","abc"};
        String []queries = {"ab","abc","bc"};


        int []result = matchingStrings(strings,queries);

        for(int num: result){
            System.out.println(num);
        }



    }
}
