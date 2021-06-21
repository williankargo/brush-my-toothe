package leetcode.BFS.Model;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import leetcode.BFS.TreeNode1;

public class BFS_EX {

  // 圖上的BFS時O(N+M), N點 M邊
  public static void main(String[] args) {
    // 這裡用TreeNode1代替Node
    TreeNode1 node = new TreeNode1();

    // 從以下看起
    Queue<TreeNode1> queue = new ArrayDeque<>(); // 建議用ArrayDeque因為連續空間比較快
    HashMap<TreeNode1, Integer> distance = new HashMap<>();

    // step 1: 初始化，把初始節點放在queue裡，如果有多個就都放進去
    // 並標記初始節點的距離為0，紀錄在distance的hashmap裡
    // distance有兩個作用，1.判斷是否已經訪問過 2.紀錄離起點的距離
    queue.offer(node);
    distance.put(node, 0);

    // step 2: 不斷訪問queue，每次pop出一個點
    while (!queue.isEmpty()) {
      node = queue.poll();
      // step 3: 拓展相鄰節點，pop出的節點的相鄰節點，加入queue並在distance中存儲距離
      for (TreeNode1 neighbor : node.getNeighbots()) {
        // 走過了就不再BFS
        if (distance.containsKey(neighbor)) {
          continue;
        }
        distance.put(neighbor, distance.get(node) + 1);
        queue.offer(neighbor);
      }

    }
  }
}
