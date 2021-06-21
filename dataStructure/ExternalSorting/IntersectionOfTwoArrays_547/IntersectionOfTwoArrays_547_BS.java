package leetcode.dataStructure.ExternalSorting.IntersectionOfTwoArrays_547;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 時 O(mlogm + nlogm) , mlogm -> Arrays.sort(nums1), nlogm -> 在nums1中搜索nums2的總數，每次BS都是O(lonm)
public class IntersectionOfTwoArrays_547_BS {

  public class IntersectionOfTwoArrays_547_HashSet {

    public int[] intersection(int[] nums1, int[] nums2) {

      if (nums1.length == 0 || nums2.length == 0) {
        return new int[0];
      }

      Arrays.sort(nums1);

      Set<Integer> set = new HashSet<>();

      for (int num : nums2) {
        if (set.contains(num)) {
          continue;
        }
        if (binarySearch(nums1, num)) {
          set.add(num);
        }
      }

      int[] ans = new int[set.size()];
      int i = 0;
      for (int num : set) {
        ans[i++] = num;
      }

      return ans;
    }
  }

  private boolean binarySearch(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (nums[start] == target) {
      return true;
    }
    if (nums[end] == target) {
      return true;
    }
    return false;
  }
}