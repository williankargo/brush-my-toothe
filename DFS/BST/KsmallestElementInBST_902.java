package leetcode.DFS.BST;

import java.util.Stack;
import leetcode.DFS.TreeNode1;

public class KsmallestElementInBST_902 {

  public int kthSmallest(TreeNode1 root, int k) {
    Stack<TreeNode1> stack = new Stack<>();

    // 先一路往左
    while (root != null) {
      stack.push(root);
      root = root.left;
    }

    // 有右就往右，再往左
    for (int i = 0; i < k - 1; i++) {
      TreeNode1 node = stack.peek();

      if (node.right == null) {
        node = stack.pop();  // pop掉『被pop點』
        while (!stack.isEmpty() && stack.peek().right == node) {  // 且『被pop點』為『上一點』的右子樹
          node = stack.pop();  // 也pop掉『上一點』
        }
      } else {
        node = node.right;
        while (node != null) {
          stack.push(node);
          node = node.left;
        }
      }
    }
    return stack.peek().val;
  }
}

// 如果要previous() 就把以上的right/left都對換
