package leetcode.DP.TwoSequencesDP;

public class LCS_77 {

  public int LCS_77(String A, String B) {

    if (A == null || B == null) {
      return 0;
    }

    int n = A.length(), m = B.length();

    // state: 前i個與前j個的LCS
    // initialization: 0 = dp[i][0] = dp[0][j]
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // 最後一個互不相等
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1); // 最後一個相等，保險起見無腦把第一種狀況也加進來比較
        }
      }
    }

    // answer
    return dp[n][m];
  }

  // 滾動數組
  public int LCS_77_2(String A, String B) {

    if (A == null || B == null) {
      return 0;
    }

    int n = A.length(), m = B.length();

    // state: 前i個與前j個的LCS
    // initialization: 0 = dp[i][0] = dp[0][j]
    int[][] dp = new int[2][m + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0) {
          dp[i % 2][j] = 0;
        } else if (j == 0) {
          dp[i % 2][j] = 0;
        } else {
          dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]); // 最後一個互不相等
          if (A.charAt(i - 1) == B.charAt(j - 1)) {
            dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][j - 1] + 1); // 最後一個相等，保險起見無腦把第一種狀況也加進來比較
          }
        }
      }
    }

    // answer
    return dp[n % 2][m];
  }

}
