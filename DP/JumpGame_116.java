package leetcode.DP;

// (1) True/False可行性 (2) 方向性
public class JumpGame_116 {

  public boolean canJump(int[] A) {

    if (A == null || A.length == 0) {
      return false;
    }

    // state: dp[i] 表示是否能跳到座標i
    boolean[] dp = new boolean[A.length];

    // initialization: 0是初始站位
    dp[0] = true;

    // O(n^2)
    for (int i = 1; i < A.length; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] == true && A[j] + j >= i) { // 如果i的前面某點可以走到i
          dp[i] = true;
          break; // 記得break
        }
      }
    }

    // answer
    return dp[A.length - 1];
  }
}
