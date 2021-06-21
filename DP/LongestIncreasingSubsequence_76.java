package leetcode.DP;

import java.util.ArrayList;

public class LongestIncreasingSubsequence_76 {

  public int longestIncreasingSubsequence(int[] nums) {

    if (nums == null || nums.length == 0) {
      return 0;
    }

    int n = nums.length;

    // 以X為結尾的數列長度
    int[] dp = new int[n];

    // 先把所有初始長度設成1
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    int max = 0;
    for (int end = 0; end < n; end++) {
      max = Math.max(max, dp[end]);
    }
    return max;
  }

  // follow-UP : 順便返回數列順序
  public int longestIncreasingSubsequence2(int[] nums) {

    if (nums == null || nums.length == 0) {
      return 0;
    }

    int n = nums.length;

    // 以X為結尾的數列長度
    int[] dp = new int[n];

    // dp[i] 的最優值是從哪個 dp[j] 來的
    int[] prev = new int[n];

    // 先把所有初始長度設成1
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      prev[i] = -1;
    }

    // 紀錄 prev[i] 和 dp[i] -> 考慮7的狀況，不能再用本來的方法
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }
    }

    // 找最大值和最後一點
    int longest = 0, last = -1;
    for (int i = 0; i < n; i++) {
      if (dp[i] > longest) {
        longest = dp[i];
        last = i; // 最後一點在哪
      }
    }

    // 倒推最優解，輸出
    ArrayList<Integer> path = new ArrayList();

    while (last != -1) {
      path.add(nums[last]);
      last = prev[last];
    }

    for (int i = path.size() - 1; i >= 0; i--) {
      System.out.println(path.get(i) + "-");
    }

    return longest;
  }
}
