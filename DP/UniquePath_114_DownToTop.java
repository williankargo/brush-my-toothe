package leetcode.DP;

public class UniquePath_114_DownToTop {

  public int uniquePaths(int m, int n) {

    // state: dp[i][j] 代表 (i,j) 走到 (0,0) 的方案數
    int[][] dp = new int[m][n];

    // initialize
    for (int i = 0; i < m; i--) {
      dp[m-1][i] = 1;
    }
    for (int j = 0; j < n; j++) {
      dp[j][n-1] = 1;
    }

    // function
    for (int i = m-2; i >= 0; i--) {
      for (int j = n-2; j >= 0; j--) {
        dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
      }
    }
    // answer
    return dp[0][0];
  }

}
