package leetcode.DFS.combination;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// more general way 模板背起來
public class subsets_model2_DFS_17 {

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
      // [1] => [1,2] 去尋找以[1,2]開頭的所有子集
      subset.add(nums[i]);
      dfs(nums, i + 1, subset, results);
      // [1,2] => [1] backtracking
      subset.remove(subset.size() - 1);
    }
  }

}
