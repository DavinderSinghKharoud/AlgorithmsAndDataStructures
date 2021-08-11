package HackerRank;

import java.util.*;

public class ArraysAndSimpleQueries {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int querySize = s.nextInt();


        Node root = null;
        Node end = null;
        for (int index = 0; index < size; index++) {
            if (root == null) {
                root = new Node();
                root.val = s.nextInt();
                end = root;
            } else {
                Node curr = new Node();
                curr.val = s.nextInt();
                end.next = curr;
                curr.previous = end;
                end = curr;
            }
        }

        for (int count = 0; count < querySize; count++) {
            int type = s.nextInt();
            int startCut = s.nextInt() - 1;
            int endCut = s.nextInt() - 1;

            if( startCut == 0 && endCut == size - 1) continue;
            Node[] arr = breakNode(root, end, startCut, endCut);
            root = arr[2];
            end = arr[3];

            if (type == 1) {
                root = addFront(root, arr[0], arr[1]);
            } else {
                end = addBack(end, arr[0], arr[1]);
            }
        }

        System.out.println(root.val - end.val);
        StringBuilder sbr = new StringBuilder();
        while (root != null) {
            sbr.append(root.val).append(" ");
            root = root.next;
        }

        System.out.println(sbr.toString());



    }

    public static Node[] breakNode(Node start, Node end, int startIndex, int endIndex) {
        int index = 0;
        Node[] res = new Node[4];
        Node traverse = start;

        while (traverse != null && index != startIndex) {
            traverse = traverse.next;
            index++;
        }

        //Starting point
        Node break1 = traverse.previous;
        Node breakNodeStart = traverse;
        traverse.previous = null;

        for (int count = 0; count < endIndex - startIndex; count++) {
            traverse = traverse.next;
        }

        //Ending point
        Node break2 = traverse.next;
        Node breakNodeEnd = traverse;
        breakNodeEnd.next = null;
        if (break1 != null) break1.next = break2;
        if (break2 != null) break2.previous = break1;

        res[0] = breakNodeStart;
        res[1] = breakNodeEnd;
        res[2] = (break1 != null) ? start : break2; //starting
        res[3] = (break2 != null) ? end : break1; //ending

        return res;

    }

    public static Node addFront(Node root, Node startIndex, Node endIndex) {

        endIndex.next = root;
        root.previous = endIndex;

        return startIndex;
    }

    public static Node addBack(Node end, Node startIndex, Node endIndex) {

        end.next = startIndex;
        startIndex.previous = end;
        return endIndex;
    }

    static class Node {
        Node previous;
        Node next;
        int val;

    }
}
