package Java.Lambda;

public class TypeReferenceExample {

    public static void main(String[] args) {

        StringLengthLambda myLamda = ( s ) -> s.length();
        System.out.println( myLamda.getLength("Hello"));
    }
}
interface StringLengthLambda{
    int getLength( String s);
}
