package algorthims.LinkedList;

import java.util.Scanner;

/**
 * Complete the insert function in your editor so that it creates a new Node
 * (pass  as the Node constructor argument) and inserts it at the tail of the linked list
 * referenced by the  parameter. Once the new node is added, return the reference to the head node.
 * Note: If the  argument passed to the insert function is null, then the initial list is empty.
 */
public class Solution {

    public static  Node insert(Node head,int data) {
        Node newNode = new Node(data);

        if(head == null){
            return newNode;
        }

        Node start = head;
        while (start.next != null){
            start = start.next;
        }

        start.next = newNode;
        return head;
    }

    public static void display(Node head) {
        Node start = head;
        while(start  != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Node head = null;
        int N = sc.nextInt();

        while(N-- > 0) {
            int ele = sc.nextInt();
            head = insert(head,ele);
        }

        display(head);
        sc.close();
    }
}
