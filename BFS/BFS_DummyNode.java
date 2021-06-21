package leetcode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// [1,#,2,3,#,4,5,6,7...]
public class BFS_DummyNode {

  public List<List<Integer>> levelOrder(TreeNode1 root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<TreeNode1> Q = new LinkedList<TreeNode1>();
    Q.offer(root);
    Q.offer(null);

    List<Integer> level = new ArrayList<>();
    while (!Q.isEmpty()) {
      TreeNode1 node = Q.poll();
      if (node == null) {
        if (level.size() == 0) {
          break;
        }
        result.add(level);
        level = new ArrayList<Integer>();
        Q.offer(null);
        continue;
      }

      level.add(node.val);
      if (node.left != null) {
        Q.offer(node.left);
      }
      if (node.right != null) {
        Q.offer(node.right);
      }
    }
    return result;
  }
}

