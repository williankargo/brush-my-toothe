package leetcode.TwoPointer.SameDirection.SlowAndFast;

public class IntersectionOfTwoLinkedLists_380 {

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    // get the tail of the list A
    ListNode node = headA;
    while (node.next != null) {
      node = node.next;
    }

    node.next = headB; // 把tail of A 連在 head of B

    ListNode result = listCycleII(headA);

    node.next = null; // 依題目要求恢復結構原狀
    return result;
  }

  private ListNode listCycleII(ListNode headA) {

    // 以下東西背多分
    ListNode slow = headA, fast = headA.next;

    while (slow != fast) {
      if (fast == null || fast.next == null) {
        return null;
      }
      slow = slow.next;
      fast = fast.next.next;
    }


    slow = headA;
    fast = fast.next;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }
}
