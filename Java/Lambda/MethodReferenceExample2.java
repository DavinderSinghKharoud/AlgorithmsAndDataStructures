package Java.Lambda;

public class MethodReferenceExample2 {
    public static void main(String[] args) {

        Thread thread = new Thread(MethodReferenceExample2::printMessage);
        thread.start();
    }

    public static void printMessage(){
        System.out.println("Hello");
    }
}
