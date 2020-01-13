package Java;

public class JavaStringTokens {



    static private void splitString(String str){





    }
    public static void main(String[] args) {

        String s = "hello world's ";



        s = s.trim();
        if(s.length() == 0){

            System.out.println(0);
            return;
        }
        String[] removeStr = s.split("[^a-zA-Z]+");
        System.out.println(removeStr.length);

        for(String word: removeStr){
            System.out.println(word);

        }

    }
}
