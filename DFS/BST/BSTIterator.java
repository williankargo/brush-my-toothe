package leetcode.DFS.BST;

import java.util.Stack;
import leetcode.DFS.TreeNode1;


// 要背起來
public class BSTIterator {

  private Stack<TreeNode1> stack = new Stack<>();

  // 先往左一直走，並紀錄在stack中
  public BSTIterator(TreeNode1 root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  // 如果stack沒有空，就代表還可以找node下去
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public TreeNode1 next() {
    TreeNode1 curt = stack.peek();
    TreeNode1 node = curt;

    if (node.right == null) { // 如果node右邊沒有子樹
      node = stack.pop(); // pop掉『被pop點』
      while (!stack.isEmpty() && stack.peek().right == node) { // 且『被pop點』為『上一點』的右子樹
        node = stack.pop(); // 也pop掉『上一點』
      }
    } else { // 如果node右邊有子樹
      node = node.right; // 進去到右子樹中
      while (node != null) {  // 進去後往左一直走，並紀錄在stack中
        stack.push(node);
        node = node.left;
      }
    }
    return curt;
  }

  // 如果要previous() 就把以上的right/left都對換
}
