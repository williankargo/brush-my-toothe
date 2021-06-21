package leetcode.Serialization;

import java.util.ArrayList;
import java.util.List;
import leetcode.BFS.TreeNode1;

public class SerializeAndDeserializeBinaryTree_7 {

  // object -> string
  public String serialize(TreeNode1 root) {

    if (root == null) {
      return "{}";
    }

    List<TreeNode1> queue = new ArrayList<>();
    queue.add(root);

    for (int i = 0; i < queue.size(); i++) {
      TreeNode1 node = queue.get(i);
      if (node == null) {
        continue;
      }
      queue.add(node.left); // 還是可能把null加進去
      queue.add(node.right);
    }
    return queueToString(queue);
  }

  private String queueToString(List<TreeNode1> queue) {

    // 把尾部的null去掉
    while (queue.get(queue.size() - 1) == null) {
      queue.remove(queue.size() - 1);
    }

    List<String> items = new ArrayList<>();
    for (TreeNode1 node : queue) {
      if (node == null) {
        items.add("#");
      } else {
        items.add("" + node.val);
      }
    }

    return "{" + String.join(",", items) + "}"; // 語法！
  }


  // string -> object
  public TreeNode1 deserialize(String data) {

    // == : 比較存放記憶體的位置，或是相同的primitive value(基本型別)
    // equals : 在string,integer,date中默認是比較值 ,but本身是比較存放記憶體的位置
    if (data == "{}") { // 這裡也可以用equals
      return null;
    }

    String[] vals = data.substring(1, data.length() - 1).split(","); // 去掉{}
    ArrayList<TreeNode1> queue = new ArrayList<>();
    TreeNode1 root = new TreeNode1(Integer.parseInt(vals[0]));
    queue.add(root);

    int index = 0;
    boolean isLeftChild = true; // 先左後右

    for (int i = 1; i < vals.length; i++) {
      if (!vals[i].equals("#")) {
        TreeNode1 node = new TreeNode1(Integer.parseInt(vals[i]));
        if (isLeftChild) {
          queue.get(index).left = node; // 『上一個node』連『現在的node』
        } else {
          queue.get(index).right = node;
        }
        queue.add(node);
      }
      if (!isLeftChild) {
        index++;
      }
      isLeftChild = !isLeftChild;
    }
    return root;
  }
}
