package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph_137 {

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if (node == null) {
      return null;
    }

    // step 1. 找到所有的點
    List<UndirectedGraphNode> nodes = findNodesByBFS(node);

    // step 2. 複製所有點
    Map<UndirectedGraphNode, UndirectedGraphNode> mapping = copyNodes(nodes);

    // step 3. 複製所有邊
    copyEdges(nodes, mapping);

    // 返回與給定node具有相同label的那個node
    return mapping.get(node);
  }


  // step 1. BFS找到所有的點
  private List<UndirectedGraphNode> findNodesByBFS(UndirectedGraphNode node) {
    Queue<UndirectedGraphNode> queue = new ArrayDeque<>(); // 用連續空間比較快
    Set<UndirectedGraphNode> visited = new HashSet<>(); // 用於保存所有點，不重不漏
    queue.offer(node);
    visited.add(node);

    while (!queue.isEmpty()) {
      UndirectedGraphNode curNode = queue.poll();
      for (UndirectedGraphNode neighbor : curNode.neighbors) {
        if (visited.contains(neighbor)) {
          continue;
        }
        visited.add(neighbor); // 要和queue寫在一起！
        queue.offer(neighbor);
      }
    }
    return new LinkedList<>(visited);
  }

  // step 2. 複製所有點
  private Map<UndirectedGraphNode, UndirectedGraphNode> copyNodes(List<UndirectedGraphNode> nodes) {
    // (舊點 -> 新點)的映射
    Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();

    for (UndirectedGraphNode node : nodes) {
      mapping.put(node, new UndirectedGraphNode(node.label)); // deep copy
    }

    return mapping;
  }

  // step 3. 複製所有邊
  private void copyEdges(List<UndirectedGraphNode> nodes,
      Map<UndirectedGraphNode, UndirectedGraphNode> mapping) {
    for (UndirectedGraphNode node : nodes) {
      UndirectedGraphNode newNode = mapping.get(node);
      // 舊點有哪些鄰居，新點就要有哪些鄰居
      for (UndirectedGraphNode neighbor : node.neighbors) {
        UndirectedGraphNode newNeighbor = mapping.get(neighbor);
        newNode.neighbors.add(newNeighbor);
      }

    }
  }
}


class UndirectedGraphNode {

  int label;
  List<UndirectedGraphNode> neighbors;

  UndirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<UndirectedGraphNode>();
  }
}