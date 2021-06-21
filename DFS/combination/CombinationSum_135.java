package leetcode.DFS.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum_135 {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<>();
    if (candidates == null || candidates.length == 0) {
      return results;
    }

    // 記得去重
    int[] candidatesNew = removeDuplicateAndSort(candidates);

    dfs(candidatesNew, target, 0, new ArrayList<Integer>(), results);
    return results;
  }

  private int[] removeDuplicateAndSort(int[] candidates) {
    Set<Integer> set = new HashSet<>();
    for (int num : candidates) {
      set.add(num);
    }

    int[] uniqueSortedNumbers = new int[set.size()];
    int i = 0;
    for (int num : set) {
      uniqueSortedNumbers[i++] = num;
    }
    // 題目說返回的東西必須是非降序的
    Arrays.sort(uniqueSortedNumbers);

    return uniqueSortedNumbers;
  }

  private void dfs(int[] candidatesNew, int target, int index, List<Integer> current,
      List<List<Integer>> results) {

    if (target == 0){
      results.add(new ArrayList<Integer>(current));
      return;
    }

    // ex: [1,2,3] target = 3
    // 這裡i起點為i 非 i+1，所以可以重複使用數字
    for (int i = index; i < candidatesNew.length; i++){

      // 剪枝：如果剩餘和比當前數字小，不考慮當前數字和之後的更大的數字（升序排列）
      if (candidatesNew[i] > target){
        break;  // not return!
      }

      current.add(candidatesNew[i]);

      dfs(candidatesNew, target - candidatesNew[i], i, current, results);  // 如果i = 0會重複答案

      current.remove(current.size() - 1);
    }
  }
}
