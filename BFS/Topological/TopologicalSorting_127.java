package leetcode.BFS.Topological;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSorting_127 {

  public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    // step 1.
    Map<DirectedGraphNode, Integer> indegree = getIndegree(graph);

    // step 2.
    Queue<DirectedGraphNode> queue = new ArrayDeque<>();
    for (DirectedGraphNode node : graph) {
      if (!indegree.containsKey(node)) {
        queue.offer(node);
      }
    }

    ArrayList<DirectedGraphNode> order = new ArrayList<>();

    // step 3.
    while (!queue.isEmpty()) {
      DirectedGraphNode node = queue.poll();
      order.add(node);
      for (DirectedGraphNode neighbor : node.neighbors) {
        indegree.put(neighbor, indegree.get(neighbor) - 1);
        // step 4.
        if (indegree.get(neighbor) == 0) {
          queue.offer(neighbor);
        }
      }
    }
    return order;
  }


  private Map<DirectedGraphNode, Integer> getIndegree(ArrayList<DirectedGraphNode> graph) {
    Map<DirectedGraphNode, Integer> indegree = new HashMap<>();
    for (DirectedGraphNode node : graph) {
      for (DirectedGraphNode neighbor : node.neighbors) {
        indegree.put(neighbor, indegree.getOrDefault(neighbor, 0) + 1);
      }
    }
    return indegree;
  }
}


class DirectedGraphNode {

  int label;
  List<DirectedGraphNode> neighbors;

  DirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<DirectedGraphNode>();
  }
}
