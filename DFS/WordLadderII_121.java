package leetcode.DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// BFS 太耗記憶體 -> 讓它不要存那麼多東西，但仍保留找最短路徑的特色
// DFS 可能鑽到天荒地老 -> 用BFS提供的資訊讓它明智的鑽下去
public class WordLadderII_121 {

  public List<List<String>> findLadders(String start, String end, Set<String> dict) {

    // 起終點放入dict
    dict.add(start);
    dict.add(end);

    // 紀錄從起點開始，到達某個詞的最短路徑長度
    Map<String, Integer> distance = new HashMap<String, Integer>();

    // 紀錄從某一個詞開始，可以到達的『不繞遠』的下一個詞的集合
    Map<String, List<String>> fromToMap = new HashMap<String, List<String>>();

    for (String s : dict) {
      fromToMap.put(s, new ArrayList<String>());
    }

    // <BFS>
    // 建立一些數據
    bfs(fromToMap, distance, start, end, dict);

    // <DFS>
    List<String> path = new ArrayList<>(); // 紀錄當前的路徑
    List<List<String>> results = new ArrayList<>(); // 紀錄所有最短路徑
    dfs(results, path, start, end, fromToMap, distance.get(end));

    return results;
  }

  private void bfs(Map<String, List<String>> fromToMap, Map<String, Integer> distance,
      String start, String end, Set<String> dict) {

    distance.put(start, 0);

    Queue<String> q = new ArrayDeque<>();
    q.offer(start);

    while (!q.isEmpty()) {
      String currWord = q.poll();
      List<String> nextWordsList = getNextWords(currWord, dict);
      for (String nextWord : nextWordsList) {

        // 如果nextWord沒有出現過，或在當前層出現過
        if (!distance.containsKey(nextWord)
            || distance.get(nextWord) == distance.get(currWord) + 1) {
          fromToMap.get(currWord).add(nextWord);
        }

        // 所以如果在同層出現過的，就不會被加到queue裡，因為就算找了結果會跟另一個同node的一樣
        if (!distance.containsKey(nextWord)) {
          distance.put(nextWord, distance.get(currWord) + 1);
          q.offer(nextWord);
        }
      }
    }
  }

  private List<String> getNextWords(String word, Set<String> dict) {

    List<String> nextWords = new ArrayList<String>();

    for (char c = 'a'; c <= 'z'; c++) {
      for (int i = 0; i < word.length(); i++) {

        // 兩個字相同，沒必要替換
        if (c == word.charAt(i)) {
          continue;
        }
        String nextWord = replace(word, i, c);
        if (dict.contains(nextWord)) {
          nextWords.add(nextWord);
        }
      }
    }
    return nextWords;
  }

  private String replace(String s, int index, char c) {
    char[] chars = s.toCharArray();
    chars[index] = c;
    return String.valueOf(chars);
  }

  private void dfs(List<List<String>> results, List<String> path, String currWord,
      String end, Map<String, List<String>> fromToMap, int minLen) {

    // 出口: 如果路徑已經比最短路徑大了，就return
    if (path.size() == minLen + 1) {
      return;
    }

    // 分解
    path.add(currWord);

    // 已經到達終點，紀錄path
    if (currWord.equals(end)) {
      results.add(new ArrayList<String>(path));  // deep copy
    }
    // 沒有到達終點，繼續往下走
    else {
      for (String nextWord : fromToMap.get(currWord)) {
        dfs(results, path, nextWord, end, fromToMap, minLen);
      }
    }
    path.remove(currWord);
  }
}

// (1) 时间复杂度O((V+E))
//  -> bfs O(V+E)遍历所有边 E（即当前字符串的下一跳）和点V，dfs O(size(dict))跑最后的最短路
// (2) 空间复杂度O(size(dict)*k)
//  -> 存每个字符串与下一跳字符串的集合以及最短路径
