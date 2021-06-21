package leetcode.DFS.BST;

import leetcode.DFS.TreeNode1;

// lowerBound: < target的最大值
// upperBound: >= target的最小值
public class ClosestBinarySearchTreeValue_900 {

  public int closestValue(TreeNode1 root, double target) {
    if (root == null) {
      return 0;
    }

    TreeNode1 lowerNode = lowerBound(root, target);
    TreeNode1 upperNode = upperBound(root, target);

    if (lowerNode == null) {
      return upperNode.val;
    }

    if (upperNode == null) {
      return lowerNode.val;
    }

    if (target - lowerNode.val > upperNode.val - target) {
      return upperNode.val;
    }

    return lowerNode.val;

  }

  // lowerBound: < target的最大值
  private TreeNode1 lowerBound(TreeNode1 root, double target) {
    if (root == null) {
      return null;
    }
    if (root.val >= target) {
      return lowerBound(root.left, target);
    }

    // root.val < target
    TreeNode1 lowerNode = lowerBound(root.right, target);
    if (lowerNode != null) {
      return lowerNode;
    }

    return root;
  }

  // upperBound: >= target的最小值
  private TreeNode1 upperBound(TreeNode1 root, double target) {
    if (root == null) {
      return null;
    }
    if (root.val < target) {
      return upperBound(root.right, target);
    }

    // root.val >= target，root已經是一個upper bound，繼續在左子樹中尋找更接近的upper bound
    TreeNode1 upperNode = upperBound(root.left, target);

    // 如果找到了更接近的，就返回uppderNode，沒有就返回root
    if (upperNode != null) {
      return upperNode;
    }
    return root;
  }


  // sol2: 更容易理解的算法
  // O(h) time and O(1) space
  public int closestValue2(TreeNode1 root, double target) {

    if (root == null) {
      return 0;
    }

    TreeNode1 upper = root;
    TreeNode1 lower = root;

    while (root != null) {
      if (root.val > target) {
        upper = root;
        root = root.left;
      } else if (root.val < target) {
        lower = root;
        root = root.right;
      } else {
        return root.val;
      }
    }

    if (Math.abs(upper.val - target) > Math.abs(target - lower.val)) {
      return lower.val;
    }
    return upper.val;
  }
}
