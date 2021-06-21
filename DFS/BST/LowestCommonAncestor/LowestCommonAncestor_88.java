package leetcode.DFS.BST.LowestCommonAncestor;



// 假設給出的兩個節點一定都在樹中存在
public class LowestCommonAncestor_88 {

  public TreeNode2 lowestCommonAncestor(TreeNode2 root, TreeNode2 A, TreeNode2 B) {

    // 出口 (從分支的小角度看)
    if (root == null) {
      return null;
    }

    // 分解
    TreeNode2 left = lowestCommonAncestor(root.left, A, B);
    TreeNode2 right = lowestCommonAncestor(root.right, A, B);

    // 判斷 (從整體的大角度看)

    if (root == A || root == B) {
      return root;
    }

    if (left != null && right != null) {
      return root;
    }
    if (left != null) {
      return left;
    }
    if (right != null) {
      return right;
    }
    return null;
  }

}

// 題目給的
class TreeNode2 {

  int val;
  TreeNode2 left, right;

  TreeNode2(int val) {
    this.val = val;
    this.left = this.right = null;
  }
}