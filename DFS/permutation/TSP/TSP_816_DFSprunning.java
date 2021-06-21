package leetcode.DFS.permutation.TSP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TSP_816_DFSprunning {

  public int minCost(int n, int[][] roads) {
    int[][] graph = constructGraph(roads, n);
    Set<Integer> visited = new HashSet<>(); // set O(1) find
    List<Integer> path = new ArrayList<>(); // path
    Result result = new Result(); // 創造一個物件來傳遞

    visited.add(1);
    path.add(1);

    dfs(1, n, visited, path, 0, graph, result); // 先把node 1放進去

    return result.minCost;
  }

  void dfs(int city,
      int n,
      Set<Integer> visited,
      List<Integer> path,
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

      if (hasBetterPath(graph, path, i)) { // pruning簡單判斷（但仍暴力搜索），發現有更好的方案，就不把此city i放在path裡
        continue;
      }
      visited.add(i); // 用排列想
      path.add(i);

      dfs(i, n, visited, path, cost + graph[city][i], graph, result);

      visited.remove(i);
      path.remove(path.size() - 1); // 用i 會重複
    }
  }

  // 只是一種判斷方式
  private boolean hasBetterPath(int[][] graph, List<Integer> path, int city) {
    for (int i = 1; i < path.size(); i++) {

      // OOO...--(i-1)--(i)--(last)--(city)
      int A = path.get(i - 1);
      int B = path.get(i);
      int last = path.get(path.size() - 1);
      if (graph[A][B] + graph[last][city] > graph[A][last] + graph[B][city]) {
        return true;
      }
    }
    return false;
  }

  int[][] constructGraph(int[][] roads, int n) { // city有n個, ex: n = 3 -> 1,2,3
    int[][] graph = new int[n + 1][n + 1]; // 因為node從1開始
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < n + 1; j++) {
        graph[i][j] = Integer.MAX_VALUE >> 4;  // 先設為最大值，並右移4位防爆掉
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

