package leetcode.DFS;

import java.util.ArrayList;
import java.util.List;

// 480.
public class DFS_DivideConquer {

  public List<String> binaryTreePaths(TreeNode1 root) {
    List<String> paths = new ArrayList<>();
    if (root == null) {
      return paths;
    }
    if (root.left == null && root.right == null) {
      paths.add("" + root.val);
      return paths;
    }

    List<String> leftPaths = binaryTreePaths(root.left);
    List<String> rightPaths = binaryTreePaths(root.right);
    for (String leftpath : leftPaths) {
      paths.add(root.val + "->" + leftpath);
    }
    for (String rightpath : rightPaths) {
      paths.add(root.val + "->" + rightpath);
    }

    return paths;

  }
}
