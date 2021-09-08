package LeetCode.BiweeklyContest60;

import java.util.*;

public class LockingTree {

    ArrayDeque<Integer>[] tree;
    int[] isLocked, parent;

    public static void main(String[] args) {
        LockingTree lockingTree = new LockingTree(new int[]{-1, 0, 0, 1, 1, 2, 2});
        System.out.println(lockingTree.lock(2, 2));    // return true because node 2 is unlocked.
        // Node 2 will now be locked by user 2.
        System.out.println(lockingTree.unlock(2, 3));  // return false because user 3 cannot unlock a node locked by user 2.
        System.out.println(lockingTree.unlock(2, 2));  // return true because node 2 was previously locked by user 2.
        // Node 2 will now be unlocked.
        System.out.println(lockingTree.lock(4, 5));    // return true because node 4 is unlocked.
        // Node 4 will now be locked by user 5.
        System.out.println(lockingTree.upgrade(0, 1)); // return true because node 0 is unlocked and has at least one locked descendant (node 4).
        // Node 0 will now be locked by user 1 and node 4 will now be unlocked.
        System.out.println(lockingTree.lock(0, 1));    // return false because node 0 is already locked.
    }


    public LockingTree(int[] parent) {
        tree = new ArrayDeque[parent.length + 1];
        for (int i = 0; i <= parent.length; i++)
            tree[i] = new ArrayDeque<>();
        for (int i = 1; i < parent.length; i++) {
            tree[parent[i]].add(i);
        }
        isLocked = new int[parent.length + 1];
        Arrays.fill(isLocked, -1);
        this.parent = parent;
    }

    public boolean lock(int num, int user) {
        if (isLocked[num] == -1) {
            isLocked[num] = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user) {
        if (isLocked[num] == user) {
            isLocked[num] = -1;
            return true;
        }
        return false;
    }

    public boolean upgrade(int num, int user) {
        if (isLocked[num] == -1) {
            boolean isLockedAncestor = isLockedAncestor(num);
            if (!isLockedAncestor) {
                // If it does not have any locked ancestor

                if (isDescendantLocked(num)) {
                    unlockAllDescendants(num);
                    isLocked[num] = user;
                    return true;
                }
            }
        }
        return false;
    }

    private void unlockAllDescendants(int num) {
        isLocked[num] = -1;
        for (int child : tree[num]) {
            unlockAllDescendants(child);
        }
    }

    boolean isDescendantLocked(int node) {
        if (isLocked[node] != -1)
            return true;
        for (int child : tree[node]) {
            if (isDescendantLocked(child))
                return true;
        }
        return false;
    }

    boolean isLockedAncestor(int node) {
        while (node != -1) {
            if (isLocked[node] != -1) return true;
            node = parent[node];
        }
        return false;
    }
}
