package leetcode.DFS;


// 628.
// 盡量不要用全局變量
public class MaxSubtree {

  public TreeNode1 findSubtree(TreeNode1 root) {
    ResultType result = helper(root);
    return result.maxSubtree;
  }

  public ResultType helper(TreeNode1 root) {

    // step1. 出口
    if (root == null) {
      return new ResultType(null, 0, Integer.MIN_VALUE);
    }

    // step2. 拆解
    // 获得左右子树的和
    ResultType leftResult = helper(root.left); // maxSubtree, sum, max_sum
    ResultType rightResult = helper(root.right);

    // step3. 統合 & 判斷
    // 计算当前子树和，并更新答案
    int sum = root.val + leftResult.sum + rightResult.sum;

    ResultType result = new ResultType(root, sum, sum);

    if (leftResult.maxSum > result.maxSum) {
      result.maxSubtree = leftResult.maxSubtree;
      result.maxSum = leftResult.maxSum;
    }

    if (rightResult.maxSum > result.maxSum) {
      result.maxSubtree = rightResult.maxSubtree;
      result.maxSum = rightResult.maxSum;
    }
    return result;
  }
}

class ResultType {

  public TreeNode1 maxSubtree;
  public int sum, maxSum;

  public ResultType(TreeNode1 maxSubtree, int sum, int maxSum) {
    this.maxSubtree = maxSubtree;
    this.maxSum = maxSum;
    this.sum = sum;
  }
}