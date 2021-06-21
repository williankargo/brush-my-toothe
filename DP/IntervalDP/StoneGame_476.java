package leetcode.DP.IntervalDP;

public class StoneGame_476 {

  public int stoneGame(int[] A) {

    if (A == null || A.length == 0) {
      return 0;
    }

    int n = A.length;
    int[] preSum = new int[n + 1]; // 要算入前0個
    for (int i = 0; i < n; i++) {
      preSum[i + 1] = preSum[i] + A[i];
    }

    // 下標i合併到下標j的最少cost
    int[][] dp = new int[n][n]; // dp[i][i] = 0 自己和自己合併沒有cost

    // function
    // way1 : i倒過來
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i + 1; j < n; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        int sum = preSum[j + 1] - preSum[i]; // sum: i -> j
        for (int k = i; k < j; k++) {
          dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum);
        }
      }
    }

    return dp[0][n - 1];
  }
}
