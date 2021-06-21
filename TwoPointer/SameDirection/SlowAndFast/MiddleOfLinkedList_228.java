package leetcode.TwoPointer.SameDirection.SlowAndFast;

public class MiddleOfLinkedList_228 {

  public ListNode middleNode(ListNode head){
    if (head == null || head.next == null){
      return head;
    }
    ListNode slow = head, fast = head.next; // slow每次走一步 fast每次走兩部
    while(fast != null && fast.next != null ){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}


class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}