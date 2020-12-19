package Algorithms.AtCoderAndGeeksForGeeks;

public class TripleDots {

    public static void main(String[] args) {
        FastReader fastReader = new FastReader();
        filter( fastReader.nextInt(), fastReader.next() );

    }

    private static void filter(int reach, String s) {

        int len = s.length();
        if( len <= reach ){
            System.out.println(s);
        }else{
            System.out.println( s.substring(0, reach ) + "..." );
        }

    }
}
