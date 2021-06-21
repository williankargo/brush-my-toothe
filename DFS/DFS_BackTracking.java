package leetcode.DFS;

import java.util.ArrayList;
import java.util.List;

// 480.
public class DFS_BackTracking {

  // backtracking, DFS
  public List<String> binaryTreePaths(TreeNode1 root) {
    // write your code here
    if (root == null) {
      return new ArrayList<>();
    }

    List<String> paths = new ArrayList<>();
    List<String> path = new ArrayList<>();
    path.add(Integer.toString(root.val));
    findPaths(root, path, paths);
    return paths;
  }

  private void findPaths(TreeNode1 node, List<String> path, List<String> paths) {
    if (node == null) {
      return;
    }

    if (node.left == null && node.right == null) {
      paths.add(String.join("->", path));
    }

    if (node.left != null) {
      path.add(Integer.toString(node.left.val));
      findPaths(node.left, path, paths);
      path.remove(path.size() - 1); // stack要pop回來
    }

    if (node.right != null) {
      path.add(Integer.toString(node.right.val));
      findPaths(node.right, path, paths);
      path.remove(path.size() - 1); // stack要pop回來
    }
  }
}
