package leetcode.BFS.DoubleBFS;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class WordLadder_120_2 {

  public int ladderLength(String start, String end, Set<String> wordSet) {
    if (start.equals(end)) {
      return 1;
    }

    HashMap<String, Set<String>> graph;
    Queue<String> forwardQueue = new ArrayDeque<>();
    Queue<String> backwardQueue = new ArrayDeque<>();
    Set<String> forwardSet = new HashSet<>();
    Set<String> backwardSet = new HashSet<>();

    wordSet.add(start);
    wordSet.add(end);
    graph = constructGraph(wordSet);
    forwardQueue.offer(start);
    backwardQueue.offer(end);
    forwardSet.add(start);
    backwardSet.add(end);

    int distance = 1;
    while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {

      distance++;
      if (extendQueue(graph, forwardQueue, forwardSet, backwardSet)) {
        return distance;
      }

      distance++;
      if (extendQueue(graph, backwardQueue, backwardSet, forwardSet)) {
        return distance;
      }
    }

    return -1;
  }

  private boolean extendQueue(
      HashMap<String, Set<String>> graph,
      Queue<String> queue,
      Set<String> visited,
      Set<String> oppositeVisited) {

    int queueLength = queue.size();
    for (int i = 0; i < queueLength; i++) {
      String word = queue.poll();
      Set<String> nextWords = graph.get(word);
      for (String nextWord : nextWords) {
        if (visited.contains(nextWord)) {
          continue;
        }
        if (oppositeVisited.contains(nextWord)) {
          return true;
        }
        queue.offer(nextWord); // 如果空間不夠時，add會拋出error，offer會返回false
        visited.add(nextWord);
      }
    }
    return false;
  }

  private HashMap<String, Set<String>> constructGraph(Set<String> wordSet) {
    HashMap<String, Set<String>> graph = new HashMap<>();

    for (String word : wordSet) {
      graph.put(word, getNextWords(word, wordSet));
    }

    return graph;
  }

  private Set<String> getNextWords(String word, Set<String> wordSet) {

    Set<String> nextWordSet = new HashSet<>();
    int wordLength = word.length();
    for (int i = 0; i < wordLength; i++) { // O(Length of word)
      String prefix = word.substring(0, i);
      String suffix = word.substring(i + 1);
      char[] chars = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
      for (int j = 0; j < 26; j++) { // O(26)
        if (word.charAt(i) == chars[j]) {
          continue;
        }
        String nextWord = prefix + chars[j] + suffix;
        if (wordSet.contains(nextWord)) { // 花 O(Length of word) 來檢查在不在 HashSet 裡，不是O(1)
          nextWordSet.add(nextWord);
        }
      }
    }
    return nextWordSet;
  }
}
