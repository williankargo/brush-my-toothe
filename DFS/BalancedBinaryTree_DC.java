package leetcode.DFS;

// 93.
public class BalancedBinaryTree_DC {

  public boolean isBalanced(TreeNode1 root) {
    return divideConquer(root).isBalanced;
  }

  // 求出root為根的子樹是否是平衡樹並返回高度
  private Result divideConquer(TreeNode1 root) {

    // 出口：空樹的時候，返回isBalanced = true, height = 0
    if (root == null) {
      return new Result(true, 0);
    }

    // 拆解：拆解到左右兩邊得到左右子樹的平衡信息和高度信息
    Result leftResult = divideConquer(root.left);
    Result rightResult = divideConquer(root.right);

    int height = Math.max(leftResult.height, rightResult.height) + 1; // +1->root
    boolean isBalanced = true; // 先給true, 經下面檢測如果不是再給false

    if (!leftResult.isBalanced || !rightResult.isBalanced) {
      isBalanced = false;
    }
    if (Math.abs(leftResult.height - rightResult.height) > 1) {
      isBalanced = false;
    }
    return new Result(isBalanced, height);
  }
}

class Result {

  boolean isBalanced;
  int height;

  Result(boolean isBalanced, int height) {
    this.isBalanced = isBalanced;
    this.height = height;
  }
}
