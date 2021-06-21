package leetcode.BFS.Topological;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SequenceReconstruction_605 {

  public boolean sequenceReconstruction(int[] org, int[][] seqs) {
    Map<Integer, Set<Integer>> graph = buildGraph(seqs);
    List<Integer> topoOrder = getTopoOrder(graph);

    if (topoOrder == null || topoOrder.size() != org.length) {
      return false;
    }
    for (int i = 0; i < org.length; i++) {
      if (org[i] != topoOrder.get(i)) {
        return false;
      }
    }
    return true;
  }

  private Map<Integer, Set<Integer>> buildGraph(int[][] seqs) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    for (int[] seq : seqs) {
      for (int i = 0; i < seq.length; i++) {
        if (!graph.containsKey(seq[i])) {
          graph.put(seq[i], new HashSet<Integer>());
        }
      }
    }
    for (int[] seq : seqs) {
      for (int i = 1; i < seq.length; i++) {
        graph.get(seq[i - 1]).add(seq[i]);
      }
    }
    return graph;
  }

  private Map<Integer, Integer> getIndegrees(Map<Integer, Set<Integer>> graph) {
    Map<Integer, Integer> indegrees = new HashMap<>();
    for (Integer node : graph.keySet()) {
      indegrees.put(node, 0);
    }
    for (Integer node : graph.keySet()) {
      for (Integer neighbor : graph.get(node)) {
        indegrees.put(neighbor, indegrees.get(neighbor) + 1);
      }
    }
    return indegrees;
  }

  private List<Integer> getTopoOrder(Map<Integer, Set<Integer>> graph) {
    Map<Integer, Integer> indegrees = getIndegrees(graph);
    Queue<Integer> queue = new ArrayDeque<>();
    List<Integer> topoOrder = new ArrayList<>();

    for (Integer node : graph.keySet()) {
      if (indegrees.get(node) == 0) {
        // 下兩行同時出現！
        queue.offer(node);
        topoOrder.add(node);
      }
    }

    while (!queue.isEmpty()) {

      // 如果queue中出現一個以上入度為0的node，代表答案不只一種！
      if (queue.size() > 1) {
        return null;
      }

      Integer node = queue.poll();
      for (Integer neighbor : graph.get(node)) {
        indegrees.put(neighbor, indegrees.get(neighbor) - 1);
        if (indegrees.get(neighbor) == 0) {
          queue.offer(neighbor);
          topoOrder.add(neighbor);
        }
      }
    }

    if (graph.size() == topoOrder.size()) {
      return topoOrder;
    }
    return null;
  }
}
