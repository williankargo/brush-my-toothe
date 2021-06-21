package leetcode.dataStructure.ExternalSorting.MergeKSortedLists_104;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import leetcode.Hash.ListNode;

// O(N logk) N:全部node的數目 k:一開始有幾個老大(放到heap中)
// 備忘錄

public class MergeKSortedLists_104_heap {

  private final Comparator<ListNode> listNodeComparator = new Comparator<ListNode>() {
    @Override
    public int compare(ListNode o1, ListNode o2) {
      return o1.val - o2.val; // 前面減後面的，就是按照順序小到大
    }
  };

  public ListNode mergeKLists(List<ListNode> lists) { // k sorted listNodes

    if (lists == null || lists.size() == 0) {
      return null;
    }

    Queue<ListNode> heap = new PriorityQueue<>(lists.size(), listNodeComparator);

    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i) != null) { // 記得不要把null加進去！
        heap.offer(lists.get(i)); // 先把各位首領加進去
      }
    }

    ListNode dummy = new ListNode(0);
    ListNode last = dummy;

    while (!heap.isEmpty()) {
      ListNode node = heap.poll();
      last.next = node;
      last = last.next; // last往後移

      if (node.next != null) {
        heap.offer(node.next);
      }
    }

    return dummy.next;
  }
}
