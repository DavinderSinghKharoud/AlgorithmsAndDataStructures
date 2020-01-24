package ProblemSolvingHackerRank;

import java.util.Scanner;
import java.util.Stack;

public class QueueUsingTwoStacks {

    public static class MyQueue<T> {
        private Stack<Integer> stackNewest = new Stack<Integer>();
        private Stack<T> stackOldest = new Stack<T>();

        public void enqueue(int value) {
            stackNewest.push(value);
        }

        public T peek() {

            if (stackOldest.isEmpty()) {

                while (!stackNewest.isEmpty()) {
                    Integer value = stackNewest.pop();
                    stackOldest.add((T) value);
                }
            }
            return stackOldest.peek();
        }

        public void dequeue() {

            if (stackOldest.isEmpty()) {

                while (!stackNewest.isEmpty()) {
                    Integer value = stackNewest.pop();
                    stackOldest.add((T) value);
                }
            }

            stackOldest.pop();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int limit = sc.nextInt();

        MyQueue<Integer> myQueue = new MyQueue<Integer>();

        for (int i = 1; i <= limit; i++) {

            Integer type = sc.nextInt();

            if ( type == 1) {
                int value = sc.nextInt();
                myQueue.enqueue(value);
            }else if( type == 2){
                myQueue.dequeue();
            }else if( type == 3) {
                System.out.println( myQueue.peek() );
            }
        }

        sc.close();




    }
}
