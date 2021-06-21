package leetcode.TwoPointer.SameDirection;

import java.util.HashMap;

public class LongestRepeatingCharacterReplacement_1246 {

  public int characterReplacement(String s, int k) {

    if (s == null) {
      return 0;
    }

    // ABABA ,k = 2 => AAAAA ,ans = 5

    HashMap<Character, Integer> counter = new HashMap<>();
    int j = 0, answer = 0, maxFreq = 0, count;
    // j 指向滿足條件的子字符串的後面一個位置

    for (int i = 0; i < s.length(); i++) {
      // 找到 i ~ j-1 這一段最長以i開頭滿足不超過k次替換的substring
      // 為啥用『<=k』？ 不能『=k』就退出，這樣可能找不到最右邊並返回maxFreq
      while (j < s.length() && j - i - maxFreq <= k) { // j - i : 字符長度 / j - i - maxFreq : 需要替換的字符
        count = counter.getOrDefault(s.charAt(j), 0) + 1;
        counter.put(s.charAt(j), count);
        maxFreq = Math.max(count, maxFreq);
        j++;
      }
      if (j - i - maxFreq > k) {
        // i ~ j-1 的 substring 需要 k+1
        // => i ~ j-2 的 substring 只需要 k 次替換
        answer = Math.max(answer, j - i - 1);
      } else {
        // i ~ j-1 的substring <= k 替換
        answer = Math.max(answer, j - i);
      }

      count = counter.get(s.charAt(i)) - 1; // counter往後移, 前一個數字不紀錄
      counter.put(s.charAt(i), count);
      maxFreq = getMaxFreq(counter);
    }
    return answer;
  }

  private int getMaxFreq(HashMap<Character, Integer> counter) {
    int max = 0;
    for (int value : counter.values()) {
      max = Math.max(max, value);
    }
    return max;
  }
}
