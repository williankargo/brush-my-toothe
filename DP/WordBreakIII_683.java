package leetcode.DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordBreakIII_683 {

  public int wordBreak3(String s, Set<String> dict) {

    if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
      return 0;
    }

    Set<String> lowerDict = new HashSet<>();
    int maxLength = initialize(dict, lowerDict);

    return memoSearch(s.toLowerCase(), 0, maxLength, lowerDict, new HashMap<Integer, Integer>());

  }

  // 把所有單詞變成小寫，並求出最大單詞長度
  private int initialize(Set<String> dict, Set<String> lowerDict) {
    int maxLength = 0;
    for (String word : dict) {
      lowerDict.add(word.toLowerCase());
      maxLength = Math.max(maxLength, word.length());
    }
    return maxLength;
  }

  // 從index開始的後綴字符串 s[index:...] 可以劃分成dict裡的單詞組合的方案數
  private int memoSearch(String s, int index, int maxLength, Set<String> lowerDict,
      Map<Integer, Integer> memo) {

    // 出口
    if (index == s.length()) {
      return 1;  // 跑到最後一個時return 1才可以加起來
    }

    if (memo.containsKey(index)) {
      return memo.get(index);
    }

    // s[index...]的方案總數初始化
    int result = 0;

    // 拆解
    for (int end = index + 1; end <= s.length(); end++) {

      // pruning
      if (end - index > maxLength) {
        break;
      }

      String word = s.substring(index, end);
      if (!lowerDict.contains(word)) {
        continue;
      }

      // 繼續遞歸並累加數目
      result += memoSearch(s, end, maxLength, lowerDict, memo);
    }

    memo.put(index, result);

    return result;
  }
}
