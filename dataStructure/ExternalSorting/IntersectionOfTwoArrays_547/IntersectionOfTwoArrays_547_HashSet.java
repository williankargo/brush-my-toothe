package leetcode.dataStructure.ExternalSorting.IntersectionOfTwoArrays_547;

import java.util.HashSet;
import java.util.Set;

// 時 O(N + M) , N,M 分別為 nums1, nums2的長度
// 空 O(Max(N,M))
public class IntersectionOfTwoArrays_547_HashSet {

  public int[] intersection(int[] nums1, int[] nums2) {

    Set<Integer> set1 = new HashSet<>();
    for (int num : nums1) {
      set1.add(num);  // int可以放到Integer的規範中，反之不行
    }

    Set<Integer> set2 = new HashSet<>();
    for (int num : nums2) {
      if (set1.contains(num)) {
        set2.add(num);
      }
    }

    int[] ans = new int[set2.size()];
    int i = 0;
    for (int num : set2) {
      ans[i++] = num;
    }

    return ans;
  }
}
