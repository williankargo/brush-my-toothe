package leetcode.DFS.BST.Morris;

import java.util.ArrayList;
import java.util.List;
import leetcode.DFS.TreeNode1;

// Morris : 使用right指針保存後面要訪問的節點的訊息，有些節點會訪問兩次，是用時間換空間的算法
// 時O(N), 空O(1)
public class BinaryTreeInorderTraversal_67 {

  public List<Integer> inorderTraversal(TreeNode1 root) {

    List<Integer> nums = new ArrayList<>();
    TreeNode1 curr = null;

    while (root != null) {
      if (root.left != null) {
        curr = root.left;
        while (curr.right != null && curr.right != root) {
          curr = curr.right;
        }
        if (curr.right == root) {
          nums.add(root.val);
          curr.right = null;
          root = root.right;
        } else {
          curr.right = root;
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
//           (2-1) root : 輸出root，curr的右孩設為null，root向右移
//           (2-2) others : curr的右孩設為root，root向左移
//       (1-2) 無：輸出當前root，root向右移