package InterviewBit;

import java.util.*;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or NULL.
 *
 * Return a deep copy of the list.
 *
 * Example
 *
 * Given list
 *
 *    1 -> 2 -> 3
 * with random pointers going from
 *
 *   1 -> 3
 *   2 -> 1
 *   3 -> 1
 */
public class CopyList {

    public static RandomListNode copyRandomList(RandomListNode head) {

        RandomListNode res = new RandomListNode(0);
        RandomListNode start = res;
        RandomListNode traverse = head;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        while (traverse != null) {
            res.next = new RandomListNode(traverse.label);
            map.put(traverse, res.next);
            res = res.next;
            traverse = traverse.next;

        }


        traverse = head;
        RandomListNode temp = start.next;

        while (traverse != null) {
            if (map.containsKey(traverse.random)) {
                temp.random = map.get(traverse.random);
            }

            traverse = traverse.next;
            temp = temp.next;
        }

        return start.next;
    }

    public static void main(String[] args) {
        RandomListNode randomListNode = new RandomListNode(1);
        randomListNode.next = new RandomListNode(2);
        randomListNode.next.next = new RandomListNode(3);
        randomListNode.next.next.next = new RandomListNode(4);
        randomListNode.random =randomListNode.next.next;
        randomListNode.next.random = randomListNode;
        randomListNode.next.next.random = randomListNode;
        randomListNode.next.next.next.random = randomListNode.next;

        RandomListNode res = copyRandomList2(randomListNode);

        while ( res != null ){
            System.out.println( res.label + ", " + res.random.label);
            res = res.next;
        }
    }

    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public static RandomListNode copyRandomList2(RandomListNode head) {

        RandomListNode traverse = head;

        while ( traverse != null ){
            RandomListNode temp = new RandomListNode(traverse.label);
            RandomListNode Next = traverse.next;
            traverse.next = temp;
            temp.next = Next;
            traverse = Next;
        }

        traverse = head;
        RandomListNode res = traverse.next;
        while ( traverse != null ){
            //Setting up random pointers
            RandomListNode addedNode = traverse.next;
            addedNode.random = (traverse.random != null ) ? traverse.random.next: null;

            traverse = traverse.next.next;
            if( traverse != null ){
                addedNode.next = traverse.next;
            }

        }

        return res;
    }
}
