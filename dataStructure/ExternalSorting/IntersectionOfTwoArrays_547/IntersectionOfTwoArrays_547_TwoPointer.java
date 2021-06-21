package leetcode.dataStructure.ExternalSorting.IntersectionOfTwoArrays_547;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// 時 O( MlogM + NlonN )排序的時後， 雙指針 O(M + N)
// 空 O(numbers of ans)
public class IntersectionOfTwoArrays_547_TwoPointer {

  public int[] intersection(int[] nums1, int[] nums2) {

    // 記得要先sort一遍
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    int i = 0;
    int j = 0;

    Set<Integer> set = new HashSet<>();
    while (i < nums1.length && j < nums2.length) {

      if (nums1[i] < nums2[j]) {
        i++;
      } else if (nums2[j] < nums1[i]) {
        j++;
      } else {
        set.add(nums1[i]);
        i++;
        j++;
      }
    }

    int index = 0;
    int[] ans = new int[set.size()];
    for (int num : set) {
      ans[index++] = num;
    }
    return ans;
  }
}
