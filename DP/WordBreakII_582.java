package leetcode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 由此可見DP無法搜索『所有的完整方案』
public class WordBreakII_582 {

  public List<String> wordBreak(String s, Set<String> dict) {
    if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
      return new ArrayList<>();
    }

    List<String> results = new ArrayList<>();
    // 通過dfs進行單詞劃分
    dfs(s, 0, getMaxWordlength(dict), dict, new HashMap<Integer, Boolean>(),
        new ArrayList<String>(), results);

    return results;
  }

  private String join(String str, ArrayList<String> used) {
    StringBuilder sb = new StringBuilder();
    boolean isFirst = true;
    // 只有第一個word前面不用加空格
    for (String word : used) {
      if (isFirst) {
        isFirst = false;
      } else {
        sb.append(str);
      }
      sb.append(word);
    }
    return sb.toString();
  }

  private int getMaxWordlength(Set<String> dict) {
    int maxWordLength = 0;
    for (String word : dict) {
      maxWordLength = Math.max(maxWordLength, word.length());
    }
    return maxWordLength;
  }

  // 從index開始的字符串s[index...]劃分成dict裡的單詞組合的方案
  private void dfs(String s, int index, int maxLength,
      Set<String> dict, Map<Integer, Boolean> memo,
      ArrayList<String> used,
      List<String> results) {

    // 出口：已經到末位
    if (index == s.length()) {
      results.add(join(" ", used));
      return;
    }

    // pruning : 從index開始的字符串 s[index...] 是否能劃分成dict裡的單詞組合
    if (!isPossible(s, index, maxLength, dict, memo)) {
      return; // 無需搜索直接返回
    }

    // 拆解
    for (int end = index + 1; end <= s.length(); end++) {

      // pruning
      if (end - index > maxLength) {
        break;
      }

      // 得到s的前綴詞
      String word = s.substring(index, end);

      // 如果這個前綴詞沒有在字典裡
      if (!dict.contains(word)) {
        continue;
      }

      // 把word加入當前路徑
      used.add(word);

      dfs(s, index + word.length(), maxLength, dict, memo, used, results);

      // 把word移開當前路徑
      used.remove(used.size() - 1);
    }
  }

  // 由此可見DP無法搜索『所有的完整方案』
  // 從index開始的字符串 s[index...] 是否能劃分成dict裡的單詞組合
  private boolean isPossible(String s, int index, int maxWordLength, Set<String> dict,
      Map<Integer, Boolean> memo) {

    // 出口
    if (memo.containsKey(index)) {
      return memo.get(index);
    }

    if (index == s.length()) {
      return true;
    }

    // 拆解
    for (int end = index + 1; end <= s.length(); end++) {

      // pruning
      if (end - index > maxWordLength) {
        break;
      }

      String word = s.substring(index, end); // 不包含index = end

      if (!dict.contains(word)) {
        continue;
      }

      // 繼續探究s[end:...]是否可以成為dict裡的單詞組合
      if (isPossible(s, end, maxWordLength, dict, memo)) {
        return true;
      }
    }
    memo.put(index, false);

    return false;
  }

}
