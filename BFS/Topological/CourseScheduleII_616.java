package leetcode.BFS.Topological;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 616.
public class CourseScheduleII_616 {

  public int[] findOrder(int numCourses, int[][] prerequisites) {

    // (先修課->多個後修課)的映射
    List[] graph = new ArrayList[numCourses];

    // 每個先修課->空後修課List
    for (int i = 0; i < numCourses; i++) {
      graph[i] = new ArrayList<Integer>();
    }

    // step 1. 統計每個點的入度，並構建圖
    int[] inDegree = new int[numCourses];
    for (int[] edge : prerequisites) {
      graph[edge[1]].add(edge[0]);
      inDegree[edge[0]]++;
    }

    Queue queue = new ArrayDeque();

    // step 2. 將每個入度為0的點放入queue中作為起始節點
    for (int i = 0; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    int numChoose = 0; // 紀錄已修課程的數量
    int[] topoOrder = new int[numCourses];

    // step 3. 不斷從queue中拿出一個點，去掉這個點的所有連邊，其他點相應入度-1
    while (!queue.isEmpty()) {
      int nowPos = (int) queue.poll();
      topoOrder[numChoose] = nowPos;
      numChoose++;
      for (int i = 0; i < graph[nowPos].size(); i++) {
        int nextPos = (int) graph[nowPos].get(i);
        // 當前點的鄰居入度-1，表示每個後修課的一門先修課已經完成
        inDegree[nextPos]--;
        // step 4. 一旦發現新的入度為0的點，丟回queue中
        // 表示一門後修課的所有先修課已經完成，可以被修了
        if (inDegree[nextPos] == 0) {
          queue.offer(nextPos);
        }
      }
    }

    // 如果全部課程已經被修過了，那麼返回拓樸排序，否則返回空
    return (numChoose == numCourses) ? topoOrder : new int[0];
  }
}
