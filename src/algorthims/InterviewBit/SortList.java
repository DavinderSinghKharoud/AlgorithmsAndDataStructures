package algorthims.InterviewBit;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Example :
 * <p>
 * Input : 1 -> 5 -> 4 -> 3
 * <p>
 * Returned list : 1 -> 3 -> 4 -> 5
 */
public class SortList {
    //O(n Log(n) ) time complexity and In place sorting O(1) space complexity MergeSort
    public static ListNode sortList(ListNode lst) {

        if (lst == null || lst.next == null) { //if there is no element or only single element
            return lst;
        }

        ListNode middle = getMiddle(lst);
        ListNode nextOfMiddle = middle.next;
        middle.next = null;

        ListNode left = sortList(lst);
        ListNode right = sortList(nextOfMiddle);

        return merge(left, right);
    }


    public static ListNode merge(ListNode left, ListNode right) {

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        if (left.val < right.val) {
            return mergeUtil(left, right);
        } else {
            return mergeUtil(right, left);
        }


    }

    public static ListNode mergeUtil(ListNode left, ListNode right) {
        //left ListNode will always have smaller or equal value
        if (left.next == null) {
            left.next = right;
            return left;
        }

        ListNode curr1 = left, next1 = left.next;
        ListNode curr2 = right, next2 = right.next;

        while (curr2 != null) {
            //if curr2 lies in between curr1 and next1
            //then curr1 --> curr2 --> next1

            if (curr2.val >= curr1.val && curr2.val <= next1.val) {
                next2 = curr2.next;
                curr1.next = curr2;
                curr2.next = next1;

                curr1 = curr2;
                curr2 = next2;
            } else {

                if (next1.next != null) { //more ListNodes in the list
                    next1 = next1.next;
                    curr1 = curr1.next;
                } else {
                    //end of the list
                    next1.next = curr2;
                    return left;
                }
            }
        }

        return left;
    }

    public static ListNode getMiddle(ListNode lst) {
        ListNode slow = lst;
        ListNode fast = lst;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {

//        ListNode lst = new ListNode(6);
//        lst.next = new ListNode(5);
//        lst.next.next = new ListNode(4);
//        lst.next.next.next = new ListNode(1);
//        lst.next.next.next.next = new ListNode(7);
        ListNode lst = new ListNode(0);
        lst.next = new ListNode(1);
        lst.next.next = new ListNode(7);
        lst.next.next.next = new ListNode(14);
        ListNode res = sortList2(lst);

        while ( res != null ){
            System.out.println(res.val);
            res = res.next;
        }
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


	//Use a quickSort
    public static ListNode sortList2(ListNode start) {
        
		ListNode end = start;
		while( end.next != null ){
			end = end.next;
		}
		
		quickSort(  start, end );
		return start;
    }
    
    public static void quickSort( ListNode start, ListNode end ){
		if( start == end ){
			return;
		}
		
		ListNode pivot_prev = partitionLast( start, end );
		//perform quickSort for left side
		quickSort( start, pivot_prev );
		
		//perform quickSort for right side

        if( pivot_prev != null && pivot_prev.next != null && pivot_prev.next == end ){
            return;
        }
		if( pivot_prev != null && pivot_prev == start ){ //if the pivot picked is moved to the starting position that means pivot and start is same.
			quickSort( pivot_prev.next, end );
			
		}else if( pivot_prev != null && pivot_prev.next != null ){
			//if pivot is in-between of the list
			quickSort( pivot_prev.next.next, end);
		}
	}
	
	public static ListNode partitionLast( ListNode start, ListNode end ){
		
		if( start == end || start == null || end == null ){
			return start;
		}
		
		ListNode pivot_prev = start;
		ListNode curr = start;
		int pivot = end.val;
		
		while(  start != end ){
			
			if( start.val < pivot ){
				
				pivot_prev = curr;
				int temp = curr.val;
				curr.val = start.val;
				start.val = temp;
				
				curr = curr.next;
			}
			start = start.next;
		}
		
		//swap the position of the current
		int temp = curr.val;
		curr.val = pivot;
		end.val  = temp;
		
		return pivot_prev;
	}

}
