package leetcode.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class subsets_model1_BFS_17 {

  public List<List<Integer>> subsets(int[] nums) {

    if (nums == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> queue = new ArrayList<>();
    int index = 0;

    Arrays.sort(nums);

    // 先把空的加進去
    queue.add(new ArrayList<Integer>()); // 這裡不能用LinkedList，因為後面會用到get()，LL如果要get要從頭開始

    while (index < queue.size()) { // 當queue沒有頭可以pop時就退出
      List<Integer> subset = queue.get(index++); // pop頭
      for (int i = 0; i < nums.length; i++) { // neighbors

        // 加進來的數必須大於前個數，不然會重複
        if (subset.size() != 0 && !(subset.get(subset.size() - 1) < nums[i])) {
          continue;
        }
        List<Integer> newSubset = new ArrayList<>(subset); // 重新造一個subset
        newSubset.add(nums[i]);
        queue.add(newSubset);
      }
    }
    return queue;
  }
}

// ex. {1,2,3}
// {{}} ->
// {{},{1},{2},{3}} ->
// {{},{1},{2},{3},{1,2},{1,3}} ->
// {{},{1},{2},{3},{1,2},{1,3},{2,3}} ->
// {{},{1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}} #