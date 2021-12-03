package Preparation;

import java.util.Stack;

/**
 * Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disk sits on top of an even larger one).
 * <p>
 * You have the following constraints:
 * <p>
 * (1) Only one disk can be moved at a time.
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk. Write a program to move the disks from the first tower to the
 * last using Stacks.
 */
public class TowerOfHanoi {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(3);
        stack.add(2);
        stack.add(1);

        Stack<Integer> target = new Stack<>();
        new TowerOfHanoi().towerOfHanoi(stack, target);
        while (!target.isEmpty()) {
            System.out.println(target.pop());
        }
    }

    void towerOfHanoi(Stack<Integer> source, Stack<Integer> target) {
        helper(source, target, new Stack<>(), source.size());
    }

    private void helper(Stack<Integer> source, Stack<Integer> target, Stack<Integer> temp, int n) {
        if (n > 0) {
            helper(source, temp, target, n - 1);
            move(source, target);
            helper(temp, target, source, n - 1);
        }

    }

    void move(Stack<Integer> source, Stack<Integer> target) {
        if (!source.isEmpty()) {
            target.add(source.pop());
        }
    }

}
