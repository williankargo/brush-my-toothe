package leetcode.DFS.combination;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsets_model1_DFS_17 {

  public List<List<Integer>> subsets(int[] nums) {

    List<List<Integer>> results = new ArrayList<>();
    if (nums == null) {
      return results;
    }
    Arrays.sort(nums);
    dfs(nums, 0, new ArrayList<Integer>(), results);  // deep copy
    return results;
  }

//    if (nums.length == 3){
//      for (int i = 0; i <= 1; i++){ 選或不選
//        for (int j = 0; j <= 1; j++){
//          for (int k = 0; k <= 1; k++){
//            i,j,k...
//          }
//        }
//      }
//    }

  private void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> results) {
    // 出口
    if (index == nums.length){
      results.add(new ArrayList<Integer>(subset));
      return;
    }

    // 拆解
    // 選nums[index]
    subset.add(nums[index]);
    dfs(nums, index + 1, subset, results);

    // 不選nums[index]
    subset.remove(subset.size() - 1);
    dfs(nums, index + 1, subset, results);
  }
}