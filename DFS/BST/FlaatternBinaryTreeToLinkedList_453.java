package leetcode.DFS.BST;


import leetcode.BFS.TreeNode1;

// 翻譯：DFS先序遍歷這棵樹，然後把結果一路向右串連起來
public class FlaatternBinaryTreeToLinkedList_453 {

  public void flatten(TreeNode1 root) {
    flattenAndReturnLastNode(root);
  }

  private TreeNode1 flattenAndReturnLastNode(TreeNode1 root) {

    // 出口
    if (root == null) {
      return null;
    }

    // 分解
    TreeNode1 leftLast = flattenAndReturnLastNode(root.left);
    TreeNode1 rightLast = flattenAndReturnLastNode(root.right);

    // 合併
    if (root.left != null) {
      leftLast.right = root.right;
      root.right = root.left;
      root.left = null;
    }

    // 判斷
    // flattenAndReturnLastNode函数需要return的是当前子树的最后一个节点。
    // 在选择return值的时候应该考虑三种基本情况：
    // 1)叶子节点; 2)只有右子树或左右都有（比如[1,null,2]，或[1,2,3]); 3)只有左子树（比如[1,2])。
    // 情况1最后一个节点就是自己(root)，情况2是lastRight,情况3是lastLeft.
    if (rightLast != null) {
      return rightLast;
    } else if (leftLast != null) {
      return leftLast;
    }
    return root;
  }
}
