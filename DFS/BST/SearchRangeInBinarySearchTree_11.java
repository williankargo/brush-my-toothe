package leetcode.DFS.BST;

import java.util.ArrayList;
import java.util.List;
import leetcode.DFS.TreeNode1;

// BST總節點數 N
// 時 O(N), 遞歸深度 logN
// 空 O(N)
public class SearchRangeInBinarySearchTree_11 {

  public List<Integer> searchRange(TreeNode1 root, int k1, int k2) {

    List<Integer> result = new ArrayList<>();
    travel(root, k1, k2, result);
    return result;
  }

  private void travel(TreeNode1 root, int k1, int k2, List<Integer> result) {

    // 記得寫出口
    if (root == null) {
      return;
    }

    if (root.val > k1) {
      travel(root.left, k1, k2, result);
    }
    if (root.val >= k1 && root.val <= k2) {
      result.add(root.val);
    }
    if (root.val < k2) {
      travel(root.right, k1, k2, result);
    }
  }
}

// code model:
// pre-order : print -> left -> right
// in-order : left -> print -> right (BST的inorder會是遞增數列)
// post-order : left -> right -> print