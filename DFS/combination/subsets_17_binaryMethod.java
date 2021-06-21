package leetcode.DFS.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsets_17_binaryMethod {

  public List<List<Integer>> subsets(int[] nums) {

    List<List<Integer>> results = new ArrayList<>();

    int n = nums.length;
    Arrays.sort(nums);
    for (int i = 0; i < (1 << n); i++) { // 1<<n -> 1 * 2^n

      List<Integer> subset = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) != 0) { // 000 的第零位(000 & 001)第一位(000 & 010)第二位(000 & 100) 是0嗎？
          subset.add(nums[j]); // 如果是就不加入
        }
      }
      results.add(subset);
    }
    return results;
  }
}

//    每個數字選或不選兩種
//    0 -> 000 -> {}
//    1 -> 001 -> {3}
//    2 -> 010 -> {2}
//    3 -> 011 -> {2,3}
//    4 -> 100 -> {1}
//    5 -> 101 -> {1,3}
//    6 -> 110 -> {1,2}
//    7 -> 111 -> {1,2,3}
