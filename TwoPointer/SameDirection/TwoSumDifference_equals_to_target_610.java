package leetcode.TwoPointer.SameDirection;

public class TwoSumDifference_equals_to_target_610 {

  // 數組默認有序 時O(N)
  public int[] twoSum7(int[] nums, int target) {
    if (nums == null || nums.length < 2) {
      return new int[]{-1, -1};
    }

    target = Math.abs(target);  // 如果target給的是負數，就必須正化，讓大數減小數的邏輯依然可以進行

    // 模板
    // i, j都不會再後退了
    int j = 1;
    for (int i = 0; i < nums.length; i++) {
      j = Math.max(j, i + 1); // 保證在i右側

      while (j < nums.length && nums[j] - nums[i] < target) {
        j++;
      }
      if (j >= nums.length) {
        break;
      }
      if (nums[j] - nums[i] == target) {
        return new int[]{nums[i], nums[j]};
      }
    }
    return new int[]{-1, -1};
  }
}
