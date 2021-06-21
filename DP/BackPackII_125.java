package leetcode.DP;

// 帶價值的01背包
public class BackPackII_125 {

  public int backPackII(int m, int[] A, int V[]) {
    if (A == null || V == null) {
      return 0;
    }

    int n = A.length;

    // state & initialization
    // dp[i][j] : 前i個物品挑出一些放到j的背包裡的MAX
    int[][] dp = new int[n + 1][m + 1];

    // function
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= m; j++) {  // 也可以寫 for (int j = 1; j <= m; j++) 因為 d[i][0] == 0
        if (A[i - 1] <= j) {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    // answer
    return dp[n][m]; // 第n+1個 第m+1個
  }
}