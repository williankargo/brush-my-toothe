package leetcode.DFS.permutation.TSP;

import java.util.Random;

// 随机化生成了一条链 然后依靠交换来逐渐逼近正确值
public class TSP_816_RandomI {

  public int RANDOM_TIMES = 1000;
  public int inf = 1000000;
  public Random rand = new Random(10000);

  public int minCost(int n, int[][] roads) {
    int[][] graph = constructGraph(roads, n);
    int minimalCost = inf;
    for (int i = 0; i < RANDOM_TIMES; i++) {
      int[] path = getRandomPath(n); // 初始化
      int cost = adjustPath(path, graph); // 調整
      minimalCost = Math.min(minimalCost, cost); // 打擂台
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
    // 產生的每個排列都是 1/n! 的等概率  1, C[1,2], C[1,2,3], C[1,2,3,4], ... C[1,2,3,4,...n]
    int[] path = new int[n];
    for (int i = 0; i < n; i++) {
      path[i] = i + 1; // i = 1開始 [1,2,3,4,5...]
    }
    for (int i = 2; i < n; i++) {
      int j = rand.nextInt(10000) % i + 1; // %i -> [0,i)閉  +1 -> [1,i]開
      int tmp = path[i];
      path[i] = path[j];
      path[j] = tmp;
    }

    return path; //[1,3,4,5,2,6,9,8,7...n]
  }

  int adjustPath(int[] path, int[][] graph) {
    int n = graph.length - 1;
    boolean adjusted = true;
    while (adjusted) {
      adjusted = false;
      for (int i = 1; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          if (canSwap(path, i, j, graph)) {
            int tmp = path[i];
            path[i] = path[j];
            path[j] = tmp;
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

  // 1,-[3]-, 5, 7, 9,-[11]-, 13 -> 1,-[11]-, 5, 7, 9,-[3]-, 13  交換3,11兩點會更動四條路徑
  boolean canSwap(int[] path, int i, int j, int[][] graph) { // 交換i ,j兩點
    int before = adjcentCost(path, i, path[i], graph);
    before += adjcentCost(path, j, path[j], graph);

    int after = adjcentCost(path, i, path[j], graph);
    after += adjcentCost(path, j, path[i], graph);

    return before > after;
  }

  int adjcentCost(int[] path, int i, int city, int[][] graph) {
    int cost = graph[path[i - 1]][city];
    if (i + 1 < path.length) {
      cost += graph[city][path[i + 1]];  // i-1到i到i+1的路徑 or j-1到j到j+1的路徑
    }
    return cost;
  }
}