package leetcode.DFS.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class permutationsII_16 {

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null) {
      return results;
    }
    if (nums.length == 0) {
      results.add(new ArrayList<Integer>());
      return results;
    }

    Arrays.sort(nums); // 先排列好，重複的會站在一起

    dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);

    return results;
  }


  private void dfs(int[] nums, boolean[] visited, List<Integer> permutation,
      // 理解為以permutation[1,2]開頭的排列
      List<List<Integer>> results) {

    if (nums.length == permutation.size()) { // O(n!)個ans,且每個ans有n個數 => O(n * n!)
      results.add(new ArrayList<Integer>(permutation)); // deep copy新增不同位置的permutation
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }

      // 保证如果有相同的数字，我一定从第一个开始选
      if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
        continue;
      }

      ///上對稱
      permutation.add(nums[i]);
      visited[i] = true;
      ///

      dfs(nums, visited, permutation, results); // 可以理解為n重循環

      ///下對稱
      visited[i] = false;
      permutation.remove(permutation.size() - 1);
      ///
    }
  }

}
