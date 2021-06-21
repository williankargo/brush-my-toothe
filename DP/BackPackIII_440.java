package leetcode.DP;

// 非01的多重背包
public class BackPackIII_440 {

  public int backPackIII(int[] A, int[] V, int m) {
    if (A == null || V == null) {
      return 0;
    }

    int n = A.length;

    // state & initialization : 前i種東西（單一種可選多個）加起來小於等於j的最大值
    int[][] dp = new int[n + 1][m + 1];

    // function
    for (int i = 1; i <= n; i++) {  // n
      for (int j = 0; j <= m; j++) { // m
        if (A[i - 1] <= j) {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - A[i - 1]] + V[i - 1]); // 經過轉換優化過的！
        } else {
          dp[i][j] = dp[i - 1][j];  // 這行有必要加上嗎？
        }
      }
    }

    // 本來的版本
    // 但這樣子 O(n*m^2) 會超時
//    for (int i = 1; i <= n; ++i) {
//      for (int j = 0; j <= m; ++j) {
//        if (j >= A[i - 1]) {
//          for (int count = 0; count <= j / A[i - 1]; count++) {
//            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - count * A[i - 1]] + count * V[i - 1]);
//          }
//        } else {
//          dp[i][j] = dp[i - 1][j];
//        }
//      }
//    }

    // answer
    return dp[n][m];
  }
}