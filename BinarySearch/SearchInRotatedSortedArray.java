package leetcode.BinarySearch;

//62.
public class SearchInRotatedSortedArray {

  // sol.1: 兩次BS
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int minLoc = findMin(nums);
    if (target > nums[nums.length - 1]) {
      // 如果在梯形的一方(左邊)，找左邊
      return findIndex(nums, 0, minLoc - 1, target);
    } else {
      // 如果在三角形的一方(右邊)或整個是正常三角形，找右邊
      return findIndex(nums, minLoc, nums.length - 1, target);
    }
  }

  private int findMin(int[] nums) {
    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] > nums[end]) {
        start = mid;
      } else {
        end = mid;
      }
    }
    if (nums[start] < nums[end]) {
      return start;
    } else {
      return end;
    }
  }

  private int findIndex(int[] nums, int start, int end, int target) {
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }
    if (nums[start] == target) {
      return start;
    }
    if (nums[end] == target) {
      return end;
    }
    return -1;
  }

  // sol.2: 一次BS
  public int search2(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;

      // mid 在左上面的線
      if (nums[mid] > nums[end]) {
        // 中
        if (target >= nums[start] && target <= nums[mid]) {
          end = mid;
        } else {
          // 頭尾
          start = mid;
        }
        // mid 在右下面的線
      } else {
        // 中
        if (target <= nums[end] && nums[mid] <= target) {
          start = mid;
        } else {
          // 頭尾
          end = mid;
        }
      }

      if (nums[start] == target) {
        return start;
      }
      if (nums[end] == target) {
        return end;
      }
    }
    return -1;
  }
}
