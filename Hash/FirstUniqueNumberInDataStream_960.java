package leetcode.Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstUniqueNumberInDataStream_960 {

}

// 時O(1) 空O(N)
class DataStream {

  private ListNode dummy;

  // tail指到鏈錶最後一個
  private ListNode tail;
  // 方便快速查找（當前node的value : 前一個node）
  private Map<Integer, ListNode> numToPrev;

  // 次數(>= 2)
  private Set<Integer> duplicates;


  DataStream() {
    // dummy點的作用：做為頭節點的前一個節點，如果沒有dummy點，頭節點就沒有前一個節點，需要再分類討論很麻煩
    dummy = new ListNode(0);
    tail = dummy;
    numToPrev = new HashMap<>();
    duplicates = new HashSet<>();
  }

  public void add(int num) {
    // 如果num已經出現過兩次含以上，return
    if (duplicates.contains(num)) {
      return;
    }

    // 如果num沒有出現過，加入
    if (!numToPrev.containsKey(num)) {
      addToListTail(num);
      return;
    }

    // 如果num出現過一次，刪掉並加入黑名單
    remove(num);
    duplicates.add(num);
  }


  // 此結構只存出現一次的
  public int firstUnique() {

    // dummy node會直接指向first unique number
    if (dummy.next == null) {
      return -1;
    }
    return dummy.next.val;
  }

  private void remove(int num) {
    // 透過numToPrev()找到此node的前一個node;
    ListNode prev = numToPrev.get(num);
    prev.next = prev.next.next; // 跨過num node
    numToPrev.remove(num);

    // 如果刪掉的是中間點
    if (prev.next != null) {
      numToPrev.put(prev.next.val, prev);
    }
    // 如果刪掉的是最後一個點
    else {
      tail = prev;
    }
  }

  private void addToListTail(int num) {
    tail.next = new ListNode(num);
    numToPrev.put(num, tail); // 這裡的tail是本來的tail
    tail = tail.next; // tail後退指到尾部
  }
}