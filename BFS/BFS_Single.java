package leetcode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// the most important!!!
public class BFS_Single {

  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode1 root) {
    ArrayList result = new ArrayList();

    if (root == null) {
      return result;
    }

    // FIFO
    Queue<TreeNode1> queue = new LinkedList<>();

    // step1: 把第一層的節點放到隊列當中
    queue.offer(root);

    // step2: while隊列非空
    while (!queue.isEmpty()) {
      ArrayList<Integer> level = new ArrayList<>();
      int size = queue.size();
      // step3: 把上一層的節點，拓展出下一層的節點
      for (int i = 0; i < size; i++) {
        TreeNode1 head = queue.poll();
        level.add(head.val);
        if (head.left != null) {
          queue.offer(head.left);
        }
        if (head.right != null) {
          queue.offer(head.right);
        }
      }
      result.add(level);
    }
    return result;
  }
}

