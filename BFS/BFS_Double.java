package leetcode.BFS;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreeNode;

public class BFS_Double {

  public List<List<Integer>> levelOrder(TreeNode1 root) {
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    if (root == null) {
      return results;
    }

    // queue 1 : 放root
    List<TreeNode1> queue = new ArrayList<>(); // 不用offer pull，用一般的arraylist就好
    queue.add(root);

    while (!queue.isEmpty()) {

      // queue 2 : 放子
      List<TreeNode1> next_queue = new ArrayList<>();
      results.add(toIntegerList(queue));

      for (TreeNode1 node : queue) {
        if (node.left != null) {
          next_queue.add(node.left);
        }
        if (node.right != null) {
          next_queue.add(node.right);
        }
      }
      queue = next_queue;
    }
    return results;

  }

  private List<Integer> toIntegerList(List<TreeNode1> queue) {
    List<Integer> level = new ArrayList<>();
    for (TreeNode1 node : queue) {
      level.add(node.val);
    }
    return level;
  }
}
