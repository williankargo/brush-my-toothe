package leetcode.DP;


// 座標型
public class UniquePathII_115 {

  public int uniquePathWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid == null || obstacleGrid.length == 0) {
      return 0;
    }

    int n = obstacleGrid.length, m = obstacleGrid[0].length;

    // state
    int[][] dp = new int[n][m]; // 初始int[][]默認0

    // initialize
    for (int i = 0; i < n; i++) {
      if (obstacleGrid[i][0] == 1) {
        break;  // 後面都會是0
      }
      dp[i][0] = 1;
    }

    for (int j = 0; j < m; j++) {
      if (obstacleGrid[0][j] == 1) {
        break;
      }
      dp[0][j] = 1;
    }

    // function
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if (obstacleGrid[i][j] == 1) {
          continue;  // dp[i][j] = 0
        }
        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
      }
    }
    return dp[n - 1][m - 1];
  }
}
