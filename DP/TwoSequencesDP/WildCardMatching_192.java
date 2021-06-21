package leetcode.DP.TwoSequencesDP;


public class WildCardMatching_192 {

  // 時,空O(n^2)
  public boolean WildCardMatching_192(String s, String p) {
    if (s == null || p == null) {
      return false;
    }

    int n = s.length(), m = p.length();

    // state
    boolean[][] dp = new boolean[n + 1][m + 1];

    // initialization
    // dp[i][0] = false (i > 0) 默認
    // dp[0][i] = true (前i個都是*)
    dp[0][0] = true;
    for (int i = 1; i <= m; i++) {
      dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*'; // 第i個
    }

    // function
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (p.charAt(j - 1) == '*') { // 最後一個有*
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // j == * 配1個或配0個
        } else { // 最後一個沒有*
          dp[i][j] = dp[i - 1][j - 1] &&
              (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
        }
      }
    }

    return dp[n][m];
  }

  // 滾動數組：
  // 因為 i 只依賴於 i-1 所以可以用
  // 時O(N^2) 空O(2N)
  public boolean WildCardMatching_192_2(String s, String p) {
    if (s == null || p == null) {
      return false;
    }

    int n = s.length(), m = p.length();

    // state
    boolean[][] dp = new boolean[2][m + 1];

    // initialization
    // dp[i][0] = false (i > 0) 默認
    // dp[0][i] = true (前i個都是*)
    dp[0][0] = true;
    for (int i = 1; i <= m; i++) {
      dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*'; // 第i個
    }

    // function
    for (int i = 1; i <= n; i++) {
      dp[i % 2][0] = false; // 初始化寫在每一行，不然會和上面的dp[0][0]搞混
      for (int j = 1; j <= m; j++) {
        if (p.charAt(j - 1) == '*') { // 最後一個有*
          dp[i % 2][j] = dp[(i - 1) % 2][j] || dp[i % 2][j - 1]; // j == * 配1個或配0個
        } else { // 最後一個沒有*
          dp[i % 2][j] = dp[(i - 1) % 2][j - 1] &&
              (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
        }
      }
    }

    return dp[n % 2][m];
  }

}
