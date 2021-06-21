package leetcode.DFS.BST;

import java.util.ArrayList;
import java.util.List;
import leetcode.DFS.TreeNode1;

public class ClosestBinarySearchTreeValueII_901 {

  public List<Integer> closestKValues(TreeNode1 root, double target, int k) {
    List<Integer> values = new ArrayList<>();

    traverse(root, values);

    int i = 0, n = values.size();
    for (; i < n; i++) {
      if (values.get(i) >= target) {
        break;  // 找到大於target的就抓住那個i
      }
    }

    // target超出values時(不在範圍內)
    if (i >= n) {
      return values.subList(n - k, n);
      // from index(=), to index(!=)
      // ex: 12345 ,n = 5, k = 3, target = 6: 3(index = 5-3),4(index = 3),5(index = 4)
    }

    int left = i - 1, right = i;
    List<Integer> result = new ArrayList<>();
    for (i = 0; i < k; i++) {
      if (left >= 0 && (right >= n || target - values.get(left) < values.get(right) - target)) {
        result.add(values.get(left));
        left--;
      } else {
        result.add(values.get(right));
        right++;
      }
    }
    return result;
  }


  private void traverse(TreeNode1 root, List<Integer> values) {
    if (root == null) {
      return;
    }
    // in-order (左中右)
    traverse(root.left, values);
    values.add(root.val);
    traverse(root.right, values);

    // BST 經過in-order取就是依序拿取
    // https://www.youtube.com/watch?v=gm8DUJJhmY4
    // 每個函數呼叫是獨立的
  }


}
