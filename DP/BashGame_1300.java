package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

// 直接 return n & 4 == 0 就好
// 這個題目不能用記憶話搜索來做，會stackoverflow
// 當時間複雜度是 O(n) 且遞歸深度是 O(n) 時會stackoverflow
public class BashGame_1300 {

  public static void main(String[] args) {
    BashGame_1300 demo = new BashGame_1300();
    System.out.println(demo.canWinBash(10));

  }

  public boolean canWinBash(int n) {
    return memoSearch(n, new HashMap<Integer, Boolean>());
  }

  private boolean memoSearch(int n, Map<Integer, Boolean> memo) {
    if (n <= 3) {
      return true;
    }
    if (memo.containsKey(n)) {
      return memo.get(n);
    }
    for (int i = 1; i <= 3; i++) {
      // 若后续状态有必败态，则当前为必胜态
      if (!memoSearch(n - i, memo)) {
        memo.put(n, true); // 紀錄true
        return true;
      }
      memo.put(n,false); // 紀錄false
    }
    // 若后续状态无必败态，则当前为必败态
    return false;
  }
}