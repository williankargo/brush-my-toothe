package leetcode.DFS.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSum_90 {

  public static void main(String[] args) {
    KSum_90 demo = new KSum_90();
    demo.kSumII(new int[]{1, 2, 3, 4}, 2, 5);
  }

  public List<List<Integer>> kSumII(int[] A, int k, int target) {

    // 這裡其實不需要sort
    // sort? (1) 須按照字母序得到結果 (2) 相同字母排在一起，方便去重
    Arrays.sort(A);
    List<List<Integer>> subsets = new ArrayList();

    dfs(A, 0, k, target, new ArrayList<Integer>(), subsets);

    return subsets;
  }

  // [1,2,3,4] k=2 target = 5  -> ans: [[1,4],[2,3]]
  // int[] A -> A.length  List<Integer> subset -> subset.size()
  private void dfs(int[] A, int index, int k, int target, List<Integer> subset,
      List<List<Integer>> subsets) {
    // 出口
    // 找到答案
    if (k == 0 && target == 0) {
      subsets.add(new ArrayList<Integer>(subset));  // deep copy
      return;
    }
    // 沒有找到答案
    if (k == 0 || target <= 0) {
      return;
    }

    // 拆解
    for (int i = index; i < A.length; i++) { // 區分外層for和內層for
      subset.add(A[i]);
      dfs(A, i + 1, k - 1, target - A[i], subset, subsets);
      subset.remove(subset.size() - 1);  // not subset.remove(A[i])
    }
  }
}

// for (1,2,3,4)
//    for (2,3,4)
// [1] -> [1,2] -> [1] -> [1,3] -> [1] -> [1,4] -> [1] -> [] -> [2,2] -> [2] -> [2,3] -> [2,4] ->...
