package leetcode.OfflineAndOnline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKLargestNumbersII_545 {

  private int k;
  private Queue<Integer> minHeap;

  public TopKLargestNumbersII_545(int k) {
    this.k = k;
    minHeap = new PriorityQueue<>();
  }


  public void add(int num) {
    minHeap.offer(num);
    if (minHeap.size() > k) {  // 維持k的大小
      minHeap.poll();
    }
  }


  public List<Integer> topk() {

    List<Integer> ans = new ArrayList<>();

    Iterator it = minHeap.iterator();
    while(it.hasNext()){
      ans.add((Integer) it.next());
    }

    Collections.sort(ans, Collections.reverseOrder()); // 大到小

    return ans;
  }
}