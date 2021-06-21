package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// 120.
public class WordLadder_120 {

  public int ladderLength(String start, String end, Set<String> dict) {

    dict.add(end); // 題目給的dict不一定會有end

    HashSet<String> visited = new HashSet<>();
    Queue<String> queue = new ArrayDeque<>();
    queue.offer(start);
    visited.add(start);

    // 紀錄最短路線長度，起始長度為1
    int length = 1;
    while (!queue.isEmpty()) {
      // 到下一層的長度
      length++;
      // 當前層有size個元素
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String word = queue.poll();
        for (String nextWord : getNextWords(word, dict)) {
          if (visited.contains(nextWord)) {
            continue;
          }
          if (nextWord.equals(end)) {
            return length; // 返回答案
          }
          visited.add(nextWord); // 加入下一層，為後面BFS做準備
          queue.offer(nextWord);
        }
      }
    }
    return 0;
  }

  // O(26L^2)時
  private ArrayList<String> getNextWords(String word, Set<String> dict) {
    ArrayList<String> nextWords = new ArrayList<String>();
    // 枚舉當前替換字母
    for (char c = 'a'; c <= 'z'; c++) { // 26

      for (int i = 0; i < word.length(); i++) { // L
        if (c == word.charAt(i)) {
          continue;
        }
        String nextWord = replace(word, i, c); // L
        if (dict.contains(nextWord)) {
          nextWords.add(nextWord);
        }
      }
    }
    return nextWords;
  }

  private String replace(String word, int index, char c) {
    char[] chars = word.toCharArray();
    chars[index] = c;
    return new String(chars);
  }
}
