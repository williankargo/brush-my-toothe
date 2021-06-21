package leetcode.DP;

// 01 背包問題
public class Backpack_92 {

  public int backPack(int m, int[] A) {

    int n = A.length;

    // state : dp[i][j] -> 前i個數裡是否可挑出和為j
    boolean[][] dp = new boolean[n + 1][m + 1];
    // ex. 前3個數是指非0的前3個數，但這裡要考慮「前0->[0]」，所以要多建一個空間

    // initialize : 前0個數裡挑和為0是true，其他都是false
    dp[0][0] = true;

    // function
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (j >= A[i - 1]) {  // 第i個數的下標是i-1
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    // answer : 找 <= m 的最大值
    for (int v = m; v >= 0; v--) {
      if (dp[n][v]) {
        return v;
      }
    }
    return -1;
  }
}
