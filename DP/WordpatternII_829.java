package leetcode.DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 此題沒有必要用DP做
public class WordpatternII_829 {


  public boolean wordPatternMatch(String pattern, String str) {
    Map<Character, String> mapping = new HashMap<>();
    Set<String> used = new HashSet<>();
    return isMatch(pattern, str, mapping, used);

  }

  // DFS原理
  private boolean isMatch(String pattern, String str, Map<Character, String> mapping,
      Set<String> used) {

    // 出口
    // " " : " "
    if (pattern.length() == 0) {  // "a".substring(1) => "", 不會有越界error
      return str.length() == 0;
    }

    Character ch = pattern.charAt(0);

    if (mapping.containsKey(ch)) {

      String word = mapping.get(ch);

      // 看word是不是str的前綴
      // 非
      if (!str.startsWith(word)) {
        return false;
      }

      // 是，繼續向下遞歸
      return isMatch(pattern.substring(1), str.substring(word.length()), mapping, used);
    }

    // 拆解
    for (int i = 0; i < str.length(); i++) {

      // 取str的前綴
      String word = str.substring(0, i + 1); // (1)

      // 字符串已經出現過了,就剪枝
      if (used.contains(word)) {
        continue;
      }

      used.add(word);
      mapping.put(ch, word);

      // 繼續遞歸探求 pattern[1:] 和 string[i+1:] 是否可以匹配
      // string[i + 1:] -> 理解成把 String word = str.substring(0, i + 1) 去掉
      if (isMatch(pattern.substring(1), str.substring(i + 1), mapping, used)) { // (2)
        return true;
      }

      // backtracking
      mapping.remove(ch);
      used.remove(word);
    }
    return false;
  }
}