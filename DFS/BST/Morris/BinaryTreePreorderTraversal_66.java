package leetcode.DFS.BST.Morris;

import java.util.ArrayList;
import java.util.List;
import leetcode.DFS.TreeNode1;

public class BinaryTreePreorderTraversal_66 {

  public List<Integer> preorderTraversal(TreeNode1 root) {
    // morris traversal
    List<Integer> nums = new ArrayList<>();
    TreeNode1 cur = null;
    while (root != null) {
      if (root.left != null) {
        cur = root.left;
        // find the predecessor of root node
        while (cur.right != null && cur.right != root) {
          cur = cur.right;
        }
        if (cur.right == root) {
          cur.right = null;
          root = root.right;
        } else {
          nums.add(root.val);
          cur.right = root;
          root = root.left;
        }
      } else {
        nums.add(root.val);
        root = root.right;
      }
    }
    return nums;
  }
}


// (0) root是否存在? 存在就進行以下
//  (1) root有無左孩?
//       (1-1) 有：在root的左子樹中找最右邊的為curr，curr的右孩為?
//           (2-1) root : curr的右孩設為null，root向右移
//           (2-2) others : 輸出root，curr的右孩設為root，root向左移
//       (1-2) 無：輸出當前root，root向右移