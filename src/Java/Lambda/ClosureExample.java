package Java.Lambda;

public class ClosureExample {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        //lambda assume that b will never change as it is final
        doProcess(a, i -> System.out.println(i + b));
    }

    private static void doProcess(int a, Process p) {
        p.process(a);
    }
}

interface Process{
    void process(int i);
}
