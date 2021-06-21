package leetcode.DFS.permutation;

import java.util.ArrayList;
import java.util.List;

public class permutations_15 {

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    if (nums == null) {
      return results;
    }
    if (nums.length == 0) {
      results.add(new ArrayList<Integer>());
      return results;
    }

    dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), results);

    return results;
  }


  private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, // 理解為以permutation[1,2]開頭的排列
      List<List<Integer>> results) {

    if (permutation.size() == nums.length) { // O(n!)個ans,且每個ans有n個數 => O(n * n!)
      results.add(new ArrayList<Integer>(permutation)); // deep copy新增不同位置的permutation
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
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
