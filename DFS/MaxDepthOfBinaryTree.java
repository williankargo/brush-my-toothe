package leetcode.DFS;

// 97.
//时间复杂度：O(n)，其中 n是节点的数量。我们每个节点只访问一次，因此时间复杂度为 O(n)。
//空间复杂度：考虑到递归使用调用栈（call stack）的情况。
//--最坏情况：树完全不平衡。例如每个节点都只有左节点，此时将递归n 次，需要保持调用栈的存储为O(n)
//--最好情况：树完全平衡。即树的高度为 log(n)，此时空间复杂度为 O(log(n))
public class MaxDepthOfBinaryTree {

  // sol1: DC
  public int maxDepth(TreeNode1 root) {
    if (root == null) {
      return 0;
    }

    int leftHeight = maxDepth(root.left);
    int rightHeight = maxDepth(root.right);

    int height = Math.max(leftHeight, rightHeight) + 1;
    return height;
  }

  // sol2: Traverse
  class Solution {

    private int depth; // 拿著小本本紀錄的人

    public int maxDepth2(TreeNode1 root) {
      depth = 0;
      helper(root, 1);

      return depth;
    }

    private void helper(TreeNode1 node, int curtDepth) {
      if (node == null) {
        return;
      }

      if (curtDepth > depth) {
        depth = curtDepth;
      }

      helper(node.left, curtDepth + 1);
      helper(node.right, curtDepth + 1);

    }
  }
}
