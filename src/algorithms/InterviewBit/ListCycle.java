package algorithms.InterviewBit;


public class ListCycle {

    public static ListNode detectCycle(ListNode root) {


        if( root == null || root.next == null ) return null;
        ListNode slow = root;
        ListNode fast = root.next.next;

        while ( fast != null && fast.next != null ){

            slow = slow.next;
            fast = fast.next.next;

            if( slow == fast ){
                break;
            }
        }
        while ( slow != root){
            root = root.next;
            slow = slow.next;
        }

        return root;
    }
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = root.next.next;

        ListNode res = detectCycle( root );

        System.out.println(res.val);
    }


    static class ListNode {
        public int val;
        public ListNode next;
        ListNode(int x) { val = x; next = null; }
    }
}
