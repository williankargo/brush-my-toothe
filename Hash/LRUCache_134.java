package leetcode.Hash;

import java.util.HashMap;
import java.util.Map;


// 時 get,set O(1)
// 空 O(N)
public class LRUCache_134 {

  // cache的最大容量
  private int capacity;

  private ListNode2 dummy;

  private ListNode2 tail;

  private Map<Integer, ListNode2> keyToPrev;

  public LRUCache_134(int capacity) {
    this.capacity = capacity;
    this.keyToPrev = new HashMap<>();
    this.dummy = new ListNode2(0, 0); // dummy node key,value隨意
    this.tail = this.dummy;
  }

  public int get(int key) {

    // 如果這個key根本不存在cache中
    if (!keyToPrev.containsKey(key)) {
      return -1;
    }
    kick(key);
    return tail.val;
  }

  public void set(int key, int value) {

    // 如果key已經存在，須更新keyNode value
    if (get(key) != -1) {
      kick(key);
      tail.val = value; // 更新key的value
      return;
    }

    // 如果key不存在，加到尾部
    pushBack(new ListNode2(key, value));
    if (keyToPrev.size() > capacity) {
      popFront();
    }
  }

  private void kick(int key) {

    // key前面的node
    ListNode2 prev = keyToPrev.get(key);

    // key本身的node
    ListNode2 keyNode = prev.next;

    // 如果key所對應的點已經在錶尾，無需移動
    if (keyNode == tail) {
      return;
    }

    // (1)
    prev.next = keyNode.next;

    // (2)
    keyToPrev.put(keyNode.next.key, prev);

    // (3) 因為之後要pushBak() ，所以要斷開！
    keyNode.next = null;

    // 把key node放到隊尾
    pushBack(keyNode);
  }

  // 這裡並沒有改變tail的值
  private void pushBack(ListNode2 node) {
    keyToPrev.put(node.key, tail);
    tail.next = node;
    tail = node;
  }

  // 把dummy後的第一個點刪掉
  private void popFront() {
    ListNode2 head = dummy.next;
    keyToPrev.remove(head.key);
    dummy.next = head.next;
    // 這裡沒有寫 head.next = null; 也沒關係，因為後面不會再用到此head
    keyToPrev.put(head.next.key, dummy);
  }


}

class ListNode2 {

  public int key, val;
  public ListNode2 next;

  public ListNode2(int key, int val) {
    this.key = key;
    this.val = val;
    this.next = null;
  }
}

