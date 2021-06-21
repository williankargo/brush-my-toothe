package leetcode.DFS.permutation.TSP;

// 狀態壓縮動態規劃
// 時 O(statesize->2^n * n * n)
public class TSP_816_DP {

  public int minCost(int n, int[][] roads) {
    int inf = Integer.MAX_VALUE >> 4;
    int[][] graph = constructGraph(roads, n);
    int stateSize = 1 << n;                 // 2^n   1左移n位
    int[][] f = new int[stateSize][n + 1];
    // ex: 我走了 1 3 5 結尾在5，記成 f[2^0+2^2+2^4][5] = 距離 f[state][city] 目前的狀態且以city為結尾的路徑
    for (int i = 0; i < stateSize; i++) {
      for (int j = 0; j < n + 1; j++) {
        f[i][j] = inf;
      }
    }
    f[1][1] = 0; // 狀態在1且結尾在1，距離為0

    // state會到2^n - 1(也就是有n個1)
    for (int state = 0; state < stateSize; state++) {
      for (int i = 2; i < n + 1; i++) { // 結尾在i=2開始
        if ((state & (1 << (i - 1))) == 0) { // 用於檢查在state的i-1位是不是0 (state從index = 1開始)
          continue;
        }
        int prevState = state ^ (1 << (i - 1));
        // == state - 2^(i - 1) 為什麼-1? 因為第一位就是i=1(2^0) 也就是還原到上一個點的狀態
        for (int j = 1; j < n + 1; j++) {
          if ((prevState & (1 << (j - 1))) == 0) {
            continue;
          }
          f[state][i] = Math.min(f[state][i], f[prevState][j] + graph[j][i]);
          // 可以得到所有state下結尾為i的所有路徑
        }
      }
    }

    int minimalCost = inf;
    for (int i = 0; i < n + 1; i++) {
      minimalCost = Math.min(minimalCost, f[stateSize - 1][i]);
      // -1? 11...11所有點都遍過了，並且以任何一點i為結尾的路徑
    }

    return minimalCost;
  }

  int[][] constructGraph(int[][] roads, int n) {
    int[][] graph = new int[n + 1][n + 1];
    int inf = Integer.MAX_VALUE >> 4;
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

}