package leetcode.DFS;

import java.util.ArrayList;
import java.util.List;

// 480.
public class DFS_Traverse {

  public List<String> binaryTreePaths(TreeNode1 root) {
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    helper(root, String.valueOf(root.val), result);
    return result;
  }

  private void helper(TreeNode1 root, String path, List<String> result) {
    if (root == null) {
      return;
    }

    if (root.left == null && root.right == null) {
      result.add(path);
      return;
    }

    if (root.left != null) {
      helper(root.left, path + "->" + root.left.val, result);
    }

    if (root.right != null) {
      helper(root.right, path + "->" + root.right.val, result);
    }
  }
}
