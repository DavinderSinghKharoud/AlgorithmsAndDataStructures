package Others.RemoveDuplicates;

import java.util.Scanner;

/**
 * Task
 * A Node class is provided for you in the editor. A Node object has an integer data field, data ,
 * and a Node instance pointer, next, pointing to another node (i.e.: the next node in a list).
 *
 * A removeDuplicates function is declared in your editor, which takes a pointer to the head node
 * of a linked list as a parameter. Complete removeDuplicates so that it deletes any duplicate
 * nodes from the list and returns the head of the updated list.
 *
 * Note: The  pointer may be null, indicating that the list is empty. Be sure to reset your next
 * pointer when performing deletions to avoid breaking the list.
 *
 * Constraints:
 * The data elements of the linked list argument will always be in non-decreasing order.
 */

public class LinkedList {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node head = null;
        System.out.print("Enter the size: ");
        int T = sc.nextInt();
        while (T-- > 0) {
            System.out.print("Enter the number: ");
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        head = removeDuplicates(head);
        display(head);
    }

    private static Node removeDuplicates(Node head) {

        Node start = head;
        while (start != null) {
            Node nextNode = start.next;
            if (nextNode != null) {

                while (start.data == start.next.data) {
                    if (start.data == start.next.data) {

                        if (start.next.next != null) {
                            start.next = start.next.next;

                            nextNode.next = null;
                        }else {
                            start.next = null;
                            break;
                        }

                    }
                }
            }
            start = start.next;
        }

        return head;
    }


    //Insert the node
    public static Node insert(Node head, int data) {
        Node p = new Node(data);
        if (head == null)
            head = p;
        else if (head.next == null)
            head.next = p;
        else {
            Node start = head;
            while (start.next != null)
                start = start.next;
            start.next = p;

        }
        return head;
    }

    //Printing the list
    public static void display(Node head) {
        Node start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }
}
