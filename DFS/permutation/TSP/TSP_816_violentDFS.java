package leetcode.DFS.permutation.TSP;

import java.util.HashSet;
import java.util.Set;

public class TSP_816_violentDFS {

  public int minCost(int n, int[][] roads) {
    int[][] graph = constructGraph(roads, n);
    Set<Integer> visited = new HashSet<>(); // set O(1) find
    Result result = new Result(); // 創造一個物件來傳遞
    visited.add(1);

    dfs(1, n, visited, 0, graph, result); // 先把node 1放進去

    return result.minCost;
  }

  void dfs(int city,
      int n,
      Set<Integer> visited,
      int cost,
      int[][] graph,
      Result result) {
    // 出口，走完了
    if (visited.size() == n) {
      result.minCost = Math.min(result.minCost, cost);
      return;
    }

    for (int i = 1; i < graph[city].length; i++) { // graph[city]中有n+1個元素
      if (visited.contains(i)) {
        continue;
      }
      visited.add(i); // 用排列想
      dfs(i, n, visited, cost + graph[city][i], graph, result);
      visited.remove(i);
    }
  }

  int[][] constructGraph(int[][] roads, int n) { // city有n個, ex: n = 3 -> 1,2,3
    int[][] graph = new int[n + 1][n + 1]; // 因為node從1開始
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < n + 1; j++) {
        graph[i][j] = Integer.MAX_VALUE;  // 先設為最大值
      }
    }
    int roadsTupleLength = roads.length;
    for (int i = 0; i < roadsTupleLength; i++) {
      int a = roads[i][0], b = roads[i][1], c = roads[i][2];
      graph[a][b] = Math.min(graph[a][b], c); // 蒐集兩點最小的邊長
      graph[b][a] = Math.min(graph[b][a], c);
    }
    return graph;
  }
}

class Result {

  int minCost;

  public Result() {
    this.minCost = Integer.MAX_VALUE;
  }
}
