package algorthims.LeetCode;

import java.util.Stack;

public class EasyTemperature {

    //Brute Force
    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Boolean noIncrease = false;

        for (int i = 0; i < T.length; i++) {

            for (int j = i + 1; j < T.length; j++) {

                if (T[j] - T[i] > 0) {
                    noIncrease = true;
                    result[i] = j - i;
                    break;
                }
            }

            if (!noIncrease) {
                result[i] = 0;
            }
        }

        return result;
    }


    //Using Stack
    public static int[] dailyTemperatures1(int[] T) {
        int[] result = new int[T.length];
        Stack<int[]> stack = new Stack<>();

        for (int i = T.length - 1; i >= 0; i--) {


            while (  !stack.isEmpty() && stack.peek()[1] <= T[i] ){
                stack.pop();
                if( stack.isEmpty() ){
                    break;
                }
            }

            if( !stack.isEmpty()  ){
                result[i] = stack.peek()[0] - i;
            }else{
                result[i] = 0;
            }


            stack.add( new int[]{
                    i, T[i]
            });

        }

        return result;
    }

    //Using Stack 2
    public static int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
    public static void main(String[] args) {

        int[] result = dailyTemperatures1(new int[]{
                73, 74, 75, 71, 69, 72, 76, 73
        });

        for (int index = 0; index < result.length; index++) {
            System.out.println(result[index]);
        }
    }
}
