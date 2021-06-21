package leetcode.dataStructure.ExternalSorting.MergeKSortedLists_104;

import java.util.List;
import leetcode.Hash.ListNode;

// O(N logK)  N: O(N)每層的兩兩合併的時間複雜度  logk: 樹的高度
// 備忘錄
public class MergeSortedLists_104_DC {

  public ListNode mergeKLists(List<ListNode> lists) {

    if (lists == null || lists.size() == 0) {
      return null;
    }

    return mergeSort(0, lists.size() - 1, lists);
  }

  private ListNode mergeSort(int start, int end, List<ListNode> lists) {

    // 出口
    if (start == end) { // 歸到最底層只剩一個node
      return lists.get(start);
    }

    int mid = start + (end - start) / 2; // 注意模板
    ListNode left = mergeSort(start, mid, lists);
    ListNode right = mergeSort(mid + 1, end, lists);
    return merge(left, right);
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
