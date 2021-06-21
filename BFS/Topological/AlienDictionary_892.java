package leetcode.BFS.Topological;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary_892 {

  public String alienOrder(String[] words) {
    Map<Character, Set<Character>> graph = constructGraph(words);
    if (graph == null) {
      return "";
    }
    return topologicalSorting(graph);
  }


  private Map<Character, Set<Character>> constructGraph(String[] words) {

    // 存放(字母->右面的多個字母)的映射關係
    Map<Character, Set<Character>> graph = new HashMap<>();

    // 生成所有的點，每個點的後續點暫時為空
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words[i].length(); j++) {
        char c = words[i].charAt(j);
        if (!graph.containsKey(c)) {
          graph.put(c, new HashSet<Character>());
        }
      }
    }

    // words = ["wrt","wrf","er","ett","rftt"]
    // 找到一個點之後的點
    for (int i = 0; i < words.length - 1; i++) { // 因為下面有word[i + 1]，所以這裡到-1就好
      int index = 0;
      while (index < words[i].length() && index < words[i + 1].length()) {
        if (words[i].charAt(index) != words[i + 1].charAt(index)) {
          graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
          break;
        }
        index++;
      }
      // 如果是["abc", "ab"] 就不合法，"abc"不能出現在"ab"前
      // ["wrf","er"] 會在上面被解決掉
      // ["ab","abc"]就合法
      if (index == Math.min(words[i].length(), words[i + 1].length())){ // index已經探底到其中一個
        if (words[i].length() > words[i + 1].length()) {
          return null;
        }
      }
    }
    return graph;
  }


  private String topologicalSorting(Map<Character, Set<Character>> graph) {
    // step 1.
    Map<Character, Integer> indegree = getIndegree(graph);

    // 題目要求可能有多個有效的字母順序，這時要返回以正常字典順序看來最小的
    Queue<Character> queue = new PriorityQueue<>();

    // step 2.
    for (Character c : indegree.keySet()) {
      if (indegree.get(c) == 0) {
        queue.offer(c);
      }
    }

    // topo order
    StringBuilder sb = new StringBuilder();

    // step 3.
    while (!queue.isEmpty()) {
      Character head = queue.poll();
      sb.append(head);
      for (Character neighbor : graph.get(head)) {
        indegree.put(neighbor, indegree.get(neighbor) - 1);

        // step 4.
        if (indegree.get(neighbor) == 0) {
          queue.offer(neighbor);
        }
      }
    }

    // 如果有些字母沒有出現在字典中，那麼沒有答案
    return (sb.length() == indegree.size()) ? sb.toString() : "";
  }

  // 統計每個點的入度
  private Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
    Map<Character, Integer> indgree = new HashMap<>();

    // 初始化所有點的入度為0
    for (Character u : graph.keySet()) {
      indgree.put(u, 0);
    }

    for (Character u : graph.keySet()) {
      // 所有鄰居的入度加1
      for (Character v : graph.get(u)) {
        indgree.put(v, indgree.get(v) + 1);
      }
    }
    return indgree;
  }
}
