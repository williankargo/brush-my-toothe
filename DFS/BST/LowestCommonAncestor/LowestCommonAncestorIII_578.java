package leetcode.DFS.BST.LowestCommonAncestor;


import leetcode.DFS.TreeNode1;

// 這兩個節點未必都在這樹上出現
public class LowestCommonAncestorIII_578 {

  public TreeNode1 lowestCommonAncestor3(TreeNode1 root, TreeNode1 A, TreeNode1 B) {
    ResultType rt = helper(root, A, B);

    // 如果AB都存在，才返回
    return (rt.a_exist && rt.b_exist) ? rt.node : null;
  }

  private ResultType helper(TreeNode1 root, TreeNode1 A, TreeNode1 B) {

    // 出口（小）
    if (root == null) {
      return new ResultType(false, false, null);
    }

    // 分解
    ResultType left_rt = helper(root.left, A, B);
    ResultType right_rt = helper(root.right, A, B);

    // 判斷（大）
    // 如果左邊有A，或右邊有A，或root本身就是A，那root這顆樹有A
    boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
    boolean b_exist = left_rt.b_exist || left_rt.b_exist || root == B;

    if (root == A || root == B) {
      return new ResultType(a_exist, b_exist, root);
    }

    if (left_rt.node != null && right_rt.node != null) {
      return new ResultType(a_exist, b_exist, root);
    }
    if (left_rt.node != null) {
      return new ResultType(a_exist, b_exist, left_rt.node);
    }
    if (right_rt.node != null) {
      return new ResultType(a_exist, b_exist, right_rt.node);
    }
    return new ResultType(a_exist, b_exist, null);
  }
}

class ResultType {

  public boolean a_exist, b_exist;
  public TreeNode1 node;

  ResultType(boolean a, boolean b, TreeNode1 n) {
    a_exist = a;
    b_exist = b;
    node = n;
  }
}