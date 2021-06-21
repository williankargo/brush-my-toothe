package leetcode.DFS.BST;

import java.util.ArrayList;
import java.util.List;
import leetcode.DFS.TreeNode1;

public class TwoSumIVInputIsABST_689 {


  public int[] twoSum(TreeNode1 root, int n) {

    List<Integer> array = new ArrayList<>();
    createArray(root, array);

    // 記得做意外處理啦！
    if (array.size() < 2) {
      return null;
    }

    int start = 0, end = array.size() - 1;
    int sum = array.get(start) + array.get(end);

    while (sum != n) {
      sum = array.get(start) + array.get(end);
      if (sum < n) {
        start++;
      } else if (sum > n) {
        end--;
      }
    }

    return new int[]{array.get(start), array.get(end)};
  }

  private void createArray(TreeNode1 root, List<Integer> array) {

    // 出口
    if (root == null) {
      return;
    }

    createArray(root.left, array);
    array.add(root.val);
    createArray(root.right, array);
  }
}

