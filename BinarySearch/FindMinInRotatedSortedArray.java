package leetcode.BinarySearch;

// 159.
public class FindMinInRotatedSortedArray {
  public int findMin(int[] nums){
    if (nums == null || nums.length == 0){
      return -1;
    }
    int start = 0, end = nums.length - 1;
    while (start + 1 < end){
      int mid = start + (end - start) / 2;

      // 畫圖看三角形判斷
      if (nums[mid] > nums[end]){
        start = mid;
      }else {
        end = mid;
      }
    }
    return Math.min(nums[start], nums[end]);
  }

}
