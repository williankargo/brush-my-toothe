package leetcode.DFS.BST.LowestCommonAncestor;

import java.util.HashSet;
import java.util.Set;


// 題目提供了指向父親的指針，我們不需要搜索可以直接反向順藤摸瓜
public class LowestCommonAncestorII_474 {

  public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A,
      ParentTreeNode B) {
    Set<ParentTreeNode> parentSet = new HashSet<>(); // 時O(1), list時O(N)

    // 把A的祖先節點都加到哈希表中
    ParentTreeNode curr = A;
    while (curr != null) {
      parentSet.add(curr);
      curr = curr.parent;
    }

    // 遍歷B的祖先節點，第一個出現在哈希表的就是答案
    curr = B;
    while (curr != null) {
      if (parentSet.contains(curr)) {
        return curr;
      }
      curr = curr.parent;
    }
    return null;
  }


}


// 題目給的
class ParentTreeNode {

  public ParentTreeNode parent, left, right;
}