package leetcode.TwoPointer.SameDirection;

public class WindowSum_604 {

  public int[] winSum(int[] nums, int k) {

    if (nums == null || nums.length < k) {
      return new int[]{};
    }
    if (k == 0) {
      return new int[]{};
    }

    int[] results = new int[nums.length - k + 1];
    int j = 0, sum = 0;
    for (int i = 0; i < nums.length; i++) {
      while (j < nums.length && j - i < k) { // j 在前面衝
        sum += nums[j];
        j++;
      }
      if (j - i == k) {
        results[i] = sum;
      }
      sum -= nums[i];
    }
    return results;
  }
}

