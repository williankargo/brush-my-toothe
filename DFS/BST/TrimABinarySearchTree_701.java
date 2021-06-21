package leetcode.DFS.BST;


import leetcode.DFS.TreeNode1;

public class TrimABinarySearchTree_701 {

  public TreeNode1 trimBST(TreeNode1 root, int minimum, int maximum) {

    // 出口
    if (root == null) {
      return null;
    }

    if (root.val < minimum) {
      return trimBST(root.right, minimum, maximum);
    }

    if (root.val > maximum) {
      return trimBST(root.left, minimum, maximum);
    }

    // 分解
    root.left = trimBST(root.left, minimum, maximum);
    root.right = trimBST(root.right, minimum, maximum);
    return root;
  }
}