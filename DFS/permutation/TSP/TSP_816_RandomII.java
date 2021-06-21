package leetcode.DFS.permutation.TSP;

import java.util.Random;

public class TSP_816_RandomII {

  public int RANDOM_TIMES = 2000;
  public int inf = 1000000;
  public Random rand = new Random(10000);

  public int minCost(int n, int[][] roads) {
    int[][] graph = constructGraph(roads, n);
    int minimalCost = inf;
    for (int i = 0; i < RANDOM_TIMES; i++) {
      int[] path = getRandomPath(n);
      int cost = adjustPath(path, graph);
      minimalCost = Math.min(minimalCost, cost);
    }

    return minimalCost;
  }

  int[][] constructGraph(int[][] roads, int n) {
    int[][] graph = new int[n + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < n + 1; j++) {
        graph[i][j] = inf;
      }
    }
    int roadsLength = roads.length;
    for (int i = 0; i < roadsLength; i++) {
      int a = roads[i][0], b = roads[i][1], c = roads[i][2];
      graph[a][b] = Math.min(graph[a][b], c);
      graph[b][a] = Math.min(graph[b][a], c);
    }

    return graph;
  }

  int[] getRandomPath(int n) {
    int[] path = new int[n];
    for (int i = 0; i < n; i++) {
      path[i] = i + 1;
    }
    for (int i = 2; i < n; i++) {
      int j = rand.nextInt(10000) % i + 1;
      int tmp = path[i];
      path[i] = path[j];
      path[j] = tmp;
    }

    return path;
  }

  int adjustPath(int[] path, int[][] graph) {
    int n = graph.length - 1;
    boolean adjusted = true;
    while (adjusted) {
      adjusted = false;
      for (int i = 1; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          if (canReverse(path, i, j, graph)) {
            reverse(path, i, j);
            adjusted = true;
          }

        }
      }
    }
    int cost = 0;
    for (int i = 1; i < n; i++) {
      cost += graph[path[i - 1]][path[i]];
    }

    return cost;
  }

  // 1,[2]-,[3],4,[5]-,[6]  2-3 & 5-6 -> 1,[2]-,[5],4,[3]-,[6]  2-5 & 3-6
  boolean canReverse(int[] path, int i, int j, int[][] graph) {
    int before = graph[path[i - 1]][path[i]];
    if (j + 1 < path.length) {
      before += graph[path[j]][path[j + 1]];
    }
    int after = graph[path[i - 1]][path[j]];
    if (j + 1 < path.length) {
      after += graph[path[i]][path[j + 1]];
    }

    return before > after;
  }

  void reverse(int[] path, int i, int j) {
    while (i < j) {
      int tmp = path[i];
      path[i] = path[j];
      path[j] = tmp;
      i++;
      j--;
    }
  }

}