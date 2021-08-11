package HackerRank;

public class isAnagram {


    static boolean isAnagram(String first, String second) {
        String firstM = first.toLowerCase();
        String secondM = second.toLowerCase();

        for( int i = 0; i<first.length(); i++){


            for(int j = 0; j<secondM.length(); j++){


                if(firstM.charAt(0) == secondM.charAt(j)){

                    firstM = firstM.substring(1);

                    secondM = secondM.substring(0, j) + secondM.substring(j+1);
                    break;
                }
            }
        }

        if(firstM.isEmpty() && secondM.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {

        boolean ret = isAnagram("anagram", "margana");
        System.out.println(ret);
    }
}
