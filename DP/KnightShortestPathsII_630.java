package leetcode.DP;

// (1) 路徑數 (2) 有方向性無循環依賴(同大方向)
public class KnightShortestPathsII_630 {

  public static int[] deltaX = {1, -1, 2, -2};
  public static int[] deltaY = {-2, -2, -1, -1};

  public int shortestPath2(boolean[][] grid) {
    if (grid == null || grid.length == 0) {
      return -1;
    }

    int n = grid.length, m = grid[0].length;

    // state
    int[][] dp = new int[n][m];

    // initialize (shortest path -> 先都Integer.MAX_VALUE)
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        dp[i][j] = Integer.MAX_VALUE;
      }
    }
    dp[0][0] = 0;

    // function
    for (int j = 0; j < m; j++) {  // j 要放在 i 外層，因為方向始終朝右（看圖）
      for (int i = 0; i < n; i++) {
        if (grid[i][j]) {
          continue; // dp[i][j] 會是初值
        }
        for (int direction = 0; direction < 4; direction++) {
          int x = i + deltaX[direction];
          int y = j + deltaY[direction];
          if (x < 0 || x >= n || y < 0 || y >= m) {
            continue;
          }
          if (dp[x][y] == Integer.MAX_VALUE) {  // 防止下面+1越界
            continue;
          }
          dp[i][j] = Math.min(dp[i][j], dp[x][y] + 1); // +1 : (x,y)走到(i,j)花了一步
        }
      }
    }

    // answer
    if (dp[n - 1][m - 1] == Integer.MAX_VALUE) {
      return -1;
    }
    return dp[n - 1][m - 1];
  }

  // rolling method
  public int shortestPath2_2(boolean[][] grid) {
    if (grid == null || grid.length == 0) {
      return -1;
    }

    int n = grid.length, m = grid[0].length;

    // state
    int[][] dp = new int[n][3];

    // initialize (shortest path -> 先都Integer.MAX_VALUE)
    for (int i = 0; i < n; i++) {  // 因為每個(x,y)都是(i,j%3)變來的
      dp[i][0] = Integer.MAX_VALUE; // (1) 不用全部設為MAX，反正之後第75行還是會設
    }
    dp[0][0] = 0; // 從 (0,0) 出發

    // function
    for (int j = 1; j < m; j++) {  // j 要放在 i 外層，因為方向始終朝右（看圖）
      for (int i = 0; i < n; i++) { // (2) j = 1開始，不然dp[0][0]會被 Integer.MAX_VALUE 覆蓋掉

        dp[i][j % 3] = Integer.MAX_VALUE;  // 待更新的點，先到初始化狀態

        if (grid[i][j]) { // 障礙物
          continue; // dp[i][j] 會是初值
        }
        for (int direction = 0; direction < 4; direction++) {
          int x = i + deltaX[direction];
          int y = j + deltaY[direction];
          if (x < 0 || x >= n || y < 0 || y >= m) {
            continue;
          }
          if (dp[x][y % 3] == Integer.MAX_VALUE) {  // 防止下面+1越界
            continue;
          }
          dp[i][j % 3] = Math.min(dp[i][j % 3], dp[x][y % 3] + 1); // +1 : (x,y)走到(i,j)花了一步
        }
      }
    }

    // answer
    if (dp[n - 1][(m - 1) % 3] == Integer.MAX_VALUE) {
      return -1;
    }
    return dp[n - 1][(m - 1) % 3];
  }
}
