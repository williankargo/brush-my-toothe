package leetcode.DFS.BST.Morris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import leetcode.DFS.TreeNode1;

public class BinaryTreePostorderTraversal_68 {

  public List<Integer> postorderTraversal(TreeNode1 root) {
    List<Integer> nums = new ArrayList<>();
    TreeNode1 cur = null;

    while (root != null) {
      if (root.right != null) {
        cur = root.right;
        while (cur.left != null && cur.left != root) {
          cur = cur.left;
        }
        if (cur.left == root) {
          cur.left = null;
          root = root.left;
        } else {
          nums.add(root.val);
          cur.left = root;
          root = root.right;
        }
      } else {
        nums.add(root.val);
        root = root.left;
      }
    }
    Collections.reverse(nums);
    return nums;
  }
}

// (0) root是否存在? 存在就進行以下
//    (2) root有無左孩?
//        (2-1) 有：在root的右子樹中找最左邊的為curr，curr的左孩為?
//            (3-1) root : curr的左孩設為null，root向左移
//            (3-2) others : 輸出root，curr的左孩設為root，root向右移
//        (2-2) 無：輸出當前root，root向左移
// (1) root不存在，反轉結果輸出

// preorder的左右反轉，最後再反轉
