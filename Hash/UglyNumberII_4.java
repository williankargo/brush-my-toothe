package leetcode.Hash;

import java.util.HashSet;
import java.util.PriorityQueue;

// 時 O(3Nlog3N) 每次取出一(N)個數，就會加入三個數(3N)，每次加入的成本是 log3N
// 空 O(2N) 取出N個加入3N個，最後有2N個（扣掉重複的會更少）
public class UglyNumberII_4 {

  public int nthUglyNumber(int n) {
    PriorityQueue<Long> heap = new PriorityQueue<>(); // 用long防止相乘的時候溢出
    heap.add(1L);

    HashSet<Long> seen = new HashSet<>(); // 去重用
    seen.add(1L);

    int[] factors = new int[]{2, 3, 5}; // int -> long 自動向上轉型

    // java允許local variable不被initialize，但currUgly是要return的必須先初始化一個數字
    long currUgly = 0, newUgly;

    for (int i = 0; i < n; i++) { // 跑到 (n-1) 那個數字就是答案
      currUgly = heap.poll();
      for (int factor : factors) {
        newUgly = currUgly * factor;
        if (!seen.contains(newUgly)) {
          heap.add(newUgly);
          seen.add(newUgly);
        }
      }
    }
    return (int) currUgly;  // long -> int  強制向下轉型
  }

}
