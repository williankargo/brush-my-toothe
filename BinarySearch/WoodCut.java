package leetcode.BinarySearch;

// 183.
// 在結果集上二分
// 時 O(NlogN)
public class WoodCut {

  public int woodCut(int[] L, int k) {
    if (L == null || L.length == 0) {
      return 0;
    }

    // 初始化start和end, end = min(max in L, sumL / K)
    // 從長度1 start，到長度最大值 end
    int start = 1, end = 0, maxLen = 0;
    long sum = 0;
    for (int l : L) {
      end = Math.max(end, l);
      sum += l;
    }
    end = (int) Math.min(end, sum / k);

    // 如果end小於1，不可能完成任務
    if (end < 1) {
      return 0;
    }

   // 時 O(logN)
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;

      //長度為mid的木頭總數 >= 目標總數，繼續增長木頭長度，選右邊
      if (getCount(L, mid) >= k) {
        start = end;
      }
      // 長度為mid的木頭總數 < 目標總數，繼續縮短木頭長度，選左邊
      else {
        end = mid;
      }
    }
    // 因為之前排除了無解的情況，所以解一定是start或end，end更長所以如果end符合要求首選end
    return getCount(L, end) >= k ? end : start;
  }

  // 時O(N)
  private int getCount(int[] L, int len) {
    int cnt = 0;
    for (int item : L) {
      cnt += item / len;
    }
    return cnt;
  }
}
