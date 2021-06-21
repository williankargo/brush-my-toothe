package leetcode.dataStructure.ExternalSorting.MergeKSortedLists_104;

import java.util.ArrayList;
import java.util.List;
import leetcode.Hash.ListNode;

// O(N logk) N 是所有node的數目，logk是樹的高度，k是一開始有多少個老大
// 備忘錄

public class MergeKSortedLists_104_MergeTwoByTwo {

  public ListNode mergeKLists(List<ListNode> lists) {

    if (lists == null || lists.size() == 0) {
      return null;
    }

    while (lists.size() > 1) { // == 1會退出
      List<ListNode> new_list = new ArrayList<>();
      for (int i = 0; i < lists.size() - 1; i += 2) {
        new_list.add(merge(lists.get(i), lists.get(i + 1)));
      }
      if (lists.size() % 2 == 1) {
        new_list.add(lists.get(lists.size() - 1));
      }
      lists = new_list;
    }

    return lists.get(0);
  }

  private ListNode merge(ListNode node1, ListNode node2) {
    ListNode dummy = new ListNode(0);
    ListNode last = dummy;

    while (node1 != null && node2 != null) {
      if (node1.val < node2.val) {
        last.next = node1;
        node1 = node1.next;
      } else {
        last.next = node2;
        node2 = node2.next;
      }
      last = last.next;
    }

    if (node1 != null) {
      last.next = node1;
    }
    if (node2 != null) {
      last.next = node2;
    }
    return dummy.next;
  }

}
