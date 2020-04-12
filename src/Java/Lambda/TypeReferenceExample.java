package Java.Lambda;

public class TypeReferenceExample {

    public static void main(String[] args) {
        printLamda( s -> s.length() );
    }

    private static void printLamda( StringLengthLambda l ){
        System.out.println( l.getLength("Hello world"));
    }
}


interface StringLengthLambda{
    int getLength( String s);
}
