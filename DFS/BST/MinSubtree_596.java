package leetcode.DFS.BST;

import leetcode.BFS.TreeNode1;

public class MinSubtree_596 {

  // 全局變量: 但不利於多線程化
  private int minSum;
  private TreeNode1 minRoot;

  // step 1: 定義
  public TreeNode1 findSubtree(TreeNode1 root) {
    minSum = Integer.MAX_VALUE; // 最小和初始值為正無窮
    minRoot = null; // 最小和子樹根節點
    getTreeSum(root);
    return minRoot;
  }

  private int getTreeSum(TreeNode1 root) {

    // step 3: 出口
    if (root == null) {
      return 0;
    }

    // step 2: 拆解
    // 左子樹之和
    int leftSum = getTreeSum(root.left);

    // 右子樹之和
    int rightSum = getTreeSum(root.right);

    // 當前樹之和
    int rootSum = leftSum + rightSum + root.val;

    // 判斷
    if (rootSum < minSum) {
      minSum = rootSum;
      minRoot = root;
    }

    // 返回當前和
    return rootSum;
  }
}
