package Java.Lambda;

public class RunnableLambda {

    public static void main(String[] args) {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Printer");
            }
        });

        myThread.start();

        Thread myLambdaThread = new Thread(() -> System.out.println("Lambda Runnable"));
        myLambdaThread.start();
    }
}
