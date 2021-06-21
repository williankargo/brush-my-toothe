package leetcode.DP;


// 時 O(N) 一個for循環 空 O(N) N個空間存醜數
public class UglyNumberII_4_DP {

  public int nthUglyNumber(int n) {

    if (n < 0) {
      return -1;
    }

    int[] dp = new int[n];
    dp[0] = 1;

    // 三個指針，挑最小的往後移
    int l2 = 0, l3 = 0, l5 = 0;

    for (int i = 1; i < n; i++) {
      dp[i] = Math.min(Math.min(dp[l2] * 2, dp[l3] * 3), dp[l5] * 5);

      if (dp[i] == dp[l2] * 2) {
        l2++;
      }
      if (dp[i] == dp[l3] * 3) {
        l3++;
      }
      if (dp[i] == dp[l5] * 5) {
        l5++;
      }
    }

    return dp[n - 1]; // 第n個數
  }
}