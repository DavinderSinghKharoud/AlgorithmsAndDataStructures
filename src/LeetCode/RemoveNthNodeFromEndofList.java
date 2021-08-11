//4package algorthims.LeetCode;


public class RemoveNthNodeFromEndofList {
    
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	 public static ListNode removeNthFromEnd(ListNode head, int n) {
	     
	     
	       ListNode temp=head;
        ListNode p=head;
        ListNode q=null;
        int count=0;
        while(temp!=null) {
            count++;
            temp=temp.next;
        }
        int index=(count-n);
        if(index==0) {
            return head.next;
        }
        int i=0;
        while(i<=index) {
            if(i==index) {
                p=p.next;
                break;
            } else {
                q=p;
                p=p.next;
                i++;
			}
        }
        q.next=p;
        
        return head;

	}
	public static void main (String[] args) {
		
//		ListNode lst = new ListNode(1);
//		lst.next = new ListNode(2);
//		lst.next.next = new ListNode(3);
//		lst.next.next.next = new ListNode(4);
//		lst.next.next.next.next = new ListNode(5);

		ListNode lst = new ListNode(1);
		lst.next = new ListNode(2);
		
	    ListNode res = removeNthFromEnd( lst, 2 );
		
		while( res != null ){
		    System.out.println( res.val );
		    res = res.next;
		}
		
	}
}

