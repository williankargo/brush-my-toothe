package leetcode.BinarySearch;

// 585.
public class MaxNumberInMountainSequence {

  public int mountainSequence(int[] nums){
    if (nums == null || nums.length == 0){
      return -1;
    }

    // 找到第一個符合條件nums[i] > nums[i+1]的i
    int start = 0, end = nums.length - 1;
    while (start + 1 < end){
      int mid = start + (end - start) / 2;
      if (nums[mid] > nums[mid + 1]){
        end = mid;
      }else{
        start = mid;
      }
    }
    // 較大的值為山頂
//    if (nums[start] > nums[end]){
//      return start;
//    }else {
//      return end;
//    }

    return Math.max(nums[start], nums[end]);
  }

}
