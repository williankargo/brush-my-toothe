package leetcode.dataStructure.ExternalSorting;

import leetcode.Hash.ListNode;

public class MergeTwoSortedLists_165 {

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode last = dummy;

    while(l1 != null && l2 != null){
      if(l1.val < l2.val){
        last.next = l1;
        l1 = l1.next; // 往下指到下一個數
      }else{
        last.next = l2;
        l2 = l2.next; // 往下指到下一個數
      }
      last = last.next; // last持續放在末尾
    }

    if(l1 != null){ // l2已經用完
      last.next = l1;
    }

    if(l2 != null){ // l1已經用完
      last.next = l2;
    }

    return dummy.next;
  }

}
