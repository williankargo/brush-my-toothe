package leetcode.DFS.BST;

import leetcode.BFS.TreeNode1;

// 沒有全局變量的版本
public class MinSubtree_596_2 {

  public TreeNode1 findSubtree(TreeNode1 root) {
    ResultType result = helper(root);
    return result.minSubtree;
  }

  private ResultType helper(TreeNode1 node) {

    // 出口
    if (node == null) {
      return new ResultType(null, Integer.MAX_VALUE, 0);
    }

    // 拆解
    ResultType leftResult = helper(node.left);
    ResultType rightResult = helper(node.right);

    // 合併
    TreeNode1 minSubtree = node;
    int sum = leftResult.sum + rightResult.sum + node.val;
    int minSum = sum;

   // 判斷
    if (leftResult.minSum <= minSum) {
      minSum = leftResult.minSum;
      minSubtree = leftResult.minSubtree;
    }

    if (rightResult.minSum <= minSum) {
      minSum = rightResult.minSum;
      minSubtree = rightResult.minSubtree;
    }

    return new ResultType(minSubtree, minSum, sum);
  }
}

class ResultType {

  public TreeNode1 minSubtree;
  public int sum, minSum;

  public ResultType(TreeNode1 minSubtree, int minSum, int sum) {
    this.minSubtree = minSubtree;
    this.sum = sum;
    this.minSum = minSum;
  }
}