package leetcode.DFS.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.tree.TreeNode;
import leetcode.DFS.TreeNode1;

// todo
public class ClosestBinaySearchTreeValueII_901_2 {

  public List<Integer> cloestValues(TreeNode1 root, double target, int k){
    List<Integer> values = new ArrayList<>();

    if (k == 0 || root == null){
      return values;
    }
    return null;

  }

  private Stack<TreeNode1> getStack(TreeNode1 root, double target){
    Stack<TreeNode1> stack = new Stack<>();

    while (root != null){

    }
    return null;
  }

  private void moveUpper(Stack<TreeNode1> stack){
    TreeNode1 node = stack.peek();
    if (node.right == null){

    }

    node = node.right;
    while (node != null){
      stack.push(node);
      node = node.left;
    }
  }

  private void moveLower(Stack<TreeNode1> stack){
    TreeNode1 node = stack.peek();
    if (node.right == null){

    }

    node = node.right;
    while (node != null){
      stack.push(node);
      node = node.left;
    }
  }



}
