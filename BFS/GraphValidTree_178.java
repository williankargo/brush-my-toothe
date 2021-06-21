package leetcode.BFS;


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// tree: (1)沒有環的：n個節點就有n-1條邊(題目說沒有重複的邊) (2)連通的：用BFS遍歷是否可以找到全部n
public class GraphValidTree_178 {

  public boolean validTree(int n, int[][] edges) {

    if (n == 0) {
      return false;
    }
    // (1)
    int numOfEdge = edges.length;
    if (numOfEdge != n - 1) { // 判斷是否有n-1條邊
      return false;
    }

    Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);
    // bfs
    // (2)
    Queue<Integer> queue = new ArrayDeque<>();
    Set<Integer> hash = new HashSet<>();

    queue.offer(0);
    hash.add(0);

    while (!queue.isEmpty()) {
      int node = queue.poll();  // Integer是int的包裝類，Integer需要實例化後才能使用，int不用
      for (Integer neighbor : graph.get(node)) {
        if (hash.contains(neighbor)) {
          continue;
        }
        queue.offer(neighbor);
        hash.add(neighbor);
      }
    }
    return hash.size() == n;
  }

  private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int i = 0; i < n; i++) {
      graph.put(i, new HashSet<>());
    }

    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    return graph;
  }
}
