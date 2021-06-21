package leetcode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII_582_2 {

  public List<String> wordBreak(String s, Set<String> dict) {
    if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
      return new ArrayList<>();
    }

    // memo : (一個字符串 => 所有劃分方案)
    Map<String, ArrayList<String>> memo = new HashMap<>();

    return wordBreakHelper(s, getMaxWordlength(dict), dict, memo);
  }

  private int getMaxWordlength(Set<String> dict) {
    int maxWordLength = 0;
    for (String word : dict) {
      maxWordLength = Math.max(maxWordLength, word.length());
    }
    return maxWordLength;
  }

  // 找到s所有切割方案並return
  public ArrayList<String> wordBreakHelper(String s, int maxWordLength, Set<String> dict,
      Map<String, ArrayList<String>> memo) {

    ArrayList<String> results = new ArrayList<>();

    // 拆解
    for (int prefixLen = 1; prefixLen < s.length(); prefixLen++) {

      // 出口
      // 如果 s 之前被計算過，直接返回結果
      if (memo.containsKey(s)) {
        return memo.get(s);
      }

      // pruning
      if (prefixLen > maxWordLength) {
        break;
      }

      String word = s.substring(0, prefixLen); // 得到前綴  s[0 : prefixLen]

      // pruning
      if (!dict.contains(word)) {
        continue;
      }

      String suffix = s.substring(prefixLen); // 得到後綴  s[prefixLen : ...]

      ArrayList<String> segmentations = wordBreakHelper(suffix, maxWordLength, dict, memo);

      for (String segmentation : segmentations) {
        results.add(word + " " + segmentation);
      }
    }

    // 如果s在詞典中，也是一種方案
    if (dict.contains(s)) {
      results.add(s);
    }

    memo.put(s, results);
    return results;
  }
}
