package leetcode.TwoPointer.SameDirection;

import java.util.Arrays;

public class RemoveDuplicateNumbersInArray_521 {

  // 0,1,1,1,2,2,3
  public int deduplication(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    Arrays.sort(nums); // 先用 O(nlongn) quicksort inplace排序

    int i, j = 1;
    for (i = 0; i < nums.length; i++) {
      while (j < nums.length && nums[i] == nums[j]) {
        j++;
      }
      if (j >= nums.length) {
        break;
      }
      nums[i + 1] = nums[j]; // 交換
    }
    return i + 1;
  }
}
