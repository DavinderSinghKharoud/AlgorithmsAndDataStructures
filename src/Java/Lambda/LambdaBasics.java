package Java.Lambda;

public class LambdaBasics {

    public static void main(String[] args) {
        Greeting greeting = () -> System.out.println("Hello world");


        //This is what we are replacing using lambda expressions
        Greeting innerClassGreeting = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Inner class greeting");
            }
        };

        LambdaBasics lambdaBasics = new LambdaBasics();
        lambdaBasics.greet( greeting );
        lambdaBasics.greet( innerClassGreeting );
    }

    interface Greeting{
        void perform ();
    }

    private void greet( Greeting greeting ){
        greeting.perform();
    }
}
