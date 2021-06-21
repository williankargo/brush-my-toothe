package leetcode.DP.IntervalDP;

public class BurstBalloons_168 {

  public int maxCoins(int[] nums) {

    if (nums == null || nums.length == 0) {
      return 0;
    }

    // 頭尾加上1
    int[] newNums = new int[nums.length + 2];
    int newLength = newNums.length;
    for (int i = 0; i < nums.length; i++) {
      newNums[i + 1] = nums[i];
    }
    newNums[0] = 1;
    newNums[newLength - 1] = 1;

    // dp[i][j] 代表從i到j的最大cost, 不包含 i, j
    int[][] dp = new int[newLength][newLength];

    for (int length = 3; length <= newLength; length++) { // 有效區間從3開始
      for (int i = 0; i < newLength - length + 1; i++) { // 在 i, j這個區間內
        int j = i + length - 1;

        for (int k = i + 1; k < j; k++) { // k在i,j之間劃分
          dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
        }
      }
    }

    return dp[0][newLength - 1];
  }
}