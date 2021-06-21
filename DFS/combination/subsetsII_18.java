package leetcode.DFS.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsetsII_18 {

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null) {
      return results;
    }
    Arrays.sort(nums);
    dfs(nums, 0, new ArrayList<Integer>(), results);
    return results;
  }

  private void dfs(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> results) {
    // results.add(subset)
    results.add(new ArrayList<Integer>(subset));

    for (int i = startIndex; i < nums.length; i++) {

      // 加上這裡
      if (i != 0 && nums[i] == nums[i - 1] && i > startIndex) { // 保证如果有相同的数字，我一定从第一个开始选
        continue;
      }
      // [1] => [1,2] 去尋找以[1,2]開頭的所有子集
      subset.add(nums[i]);
      dfs(nums, i + 1, subset, results);
      // [1,2] => [1] backtracking
      subset.remove(subset.size() - 1);
    }
  }

}