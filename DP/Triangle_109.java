package leetcode.DP;

public class Triangle_109 {

  // top -> down (rolling)
  // 空O(2N) 時O(N^2) -> 兩層for
  public int minimumTotal3(int[][] triangle) {

    if (triangle == null || triangle.length == 0) {
      return -1;
    }
    if (triangle[0] == null || triangle[0].length == 0) {
      return -1;
    }

    // state : top->down
    int n = triangle.length;
    int[][] dp = new int[2][n];

    // initialize
    dp[0][0] = triangle[0][0]; // 必須先給這個數
    for (int i = 1; i < n; i++) {
      dp[i % 2][0] = dp[(i - 1) % 2][0] + triangle[i][0];
      dp[i % 2][i] = dp[(i - 1) % 2][i - 1] + triangle[i][i];
    }

    // top down
    for (int i = 2; i < n; i++) {
      for (int j = 1; j < i; j++) {
        dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], dp[(i - 1) % 2][j]) + triangle[i][j];
      }
    }

    // answer
    int best = dp[(n - 1) % 2][0];
    for (int i = 1; i < n; i++) {
      best = Math.min(best, dp[(n - 1) % 2][i]);
    }
    return best;
  }

  // top -> down
  public int minimumTotal(int[][] triangle) {

    if (triangle == null || triangle.length == 0) {
      return -1;
    }
    if (triangle[0] == null || triangle[0].length == 0) {
      return -1;
    }

    // state : top->down
    int n = triangle.length;
    int[][] dp = new int[n][n];

    // initialize
    dp[0][0] = triangle[0][0]; // 必須先給這個數
    for (int i = 1; i < n; i++) {
      dp[i][0] = dp[i - 1][0] + triangle[i][0];
      dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
    }

    // top down
    for (int i = 2; i < n; i++) {
      for (int j = 1; j < i; j++) {
        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
      }
    }

    // answer
    int best = dp[n - 1][0];
    for (int i = 1; i < n; i++) {
      best = Math.min(best, dp[n - 1][i]);
    }
    return best;
  }

  // down -> top
  public int minimumTotal_2(int[][] triangle) {

    if (triangle == null || triangle.length == 0) {
      return -1;
    }
    if (triangle[0] == null || triangle[0].length == 0) {
      return -1;
    }

    // state
    int n = triangle.length;
    int[][] dp = new int[n][n];

    // initialize
    for (int i = 0; i < n; i++) {
      dp[n - 1][i] = triangle[n - 1][i];
    }

    // bottom up
    for (int i = n - 2; i <= 0; i--) {
      for (int j = 0; j <= i; i++) {
        dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
      }
    }

    // answer
    return dp[0][0];
  }
}
