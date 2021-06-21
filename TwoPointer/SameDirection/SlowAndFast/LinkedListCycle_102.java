package leetcode.TwoPointer.SameDirection.SlowAndFast;

public class LinkedListCycle_102 {

  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode fast, slow;
    slow = head;
    fast = head.next;
    while (fast != slow) { // 如果有環，快慢指針最終會相遇
      if (fast == null || fast.next == null) {
        return false;
      }
      fast = fast.next.next;
      slow = slow.next;
    }
    return true;
  }
}
