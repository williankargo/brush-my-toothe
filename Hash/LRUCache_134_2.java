package leetcode.Hash;

import java.util.LinkedHashMap;
import java.util.Map;

// 時 get/set O(1)
// 空 O(N)
public class LRUCache_134_2 {

  // LinkedHashMap是種可以快速O(1)找到點的LinkedList，可以維護插入的順序
  Map<Integer, Integer> map = new LinkedHashMap<>();

  int cap;

  public LRUCache_134_2(int capacity) {
    cap = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    int val = map.remove(key); // 從LH中刪掉那個node
    map.put(key, val);  // 並加到LH的尾部
    return val;
  }

  public void set(int key, int value) {

    // 如果key已經存在
    if (map.containsKey(key)) {
      map.remove(key);
      map.put(key, value);
      return;
    }

    // 如果key不存在
    map.put(key, value);

    // 如果cache滿了
    if (map.size() > cap) {
      map.remove(map.entrySet().iterator().next().getKey());  // 把頭部刪掉
    }
  }
}
