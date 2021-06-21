package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 可以了解 nums.get(mid) >= target or nums.get(mid) <= target 的意義
public class FindFirstAndLastPositionOfElementInSortedArray_1536 {

  public List<Integer> searchRange(List<Integer> nums, int target) {

    if (nums == null || nums.size() < 1) {
      return Arrays.asList(-1, -1);
    }

    // (1) 找左邊界
    int start = 0;
    int end = nums.size() - 1; // 記得-1
    List<Integer> result = new ArrayList<>();  // List是interface不能直接new!

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums.get(mid) < target) {
        start = mid;
      } else if (nums.get(mid) > target){
        end = mid;
      }else{ // nums.get(mid) == target
        end = mid; // 因為左邊可能還有，要找左邊界
      }
    }

    if (nums.get(start) != target && nums.get(end) != target) {
      return Arrays.asList(-1, -1);
    }
    if (nums.get(start) == target) {
      result.add(0, start);
    } else {
      result.add(0, end);
    }

    // (2) 找右邊界
    start = 0;
    end = nums.size() - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums.get(mid) < target) {
        start = mid;
      } else if (nums.get(mid) > target){
        end = mid;
      }else{ // nums.get(mid) == target
        start = mid;  // 因為右邊可能還有，要找右邊界
      }
    }

    if (nums.get(start) != target && nums.get(end) != target) {
      return Arrays.asList(-1, -1);
    }
    if (nums.get(end) == target) {
      result.add(1, end);
    } else {
      result.add(1, start);
    }

    return result;

  }
}