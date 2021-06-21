package leetcode.BFS;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


// 時O(N*M)
public class KnightShortestPath_611 {

  // 8個方向的偏移量
  public static final int[] X_OFFSET = {1, 1, -1, -1, 2, 2, -2, -2};
  public static final int[] Y_OFFSET = {2, -2, 2, -2, 1, -1, 1, -1};

  public int shortestPath(boolean[][] grid, Point1 source, Point1 destination) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return -1;
    }
    Queue<Point1> queue = new ArrayDeque<>();
    Map<Integer, Integer> disToSrcMap = new HashMap<>();

    int colCnt = grid[0].length;
    queue.offer(source);
    disToSrcMap.put(source.x * colCnt + source.y, 0);

    while (!queue.isEmpty()) {
      Point1 point = queue.poll();
      int currPointKey = point.x * colCnt + point.y;
      if (point.x == destination.x && point.y == destination.y) {
        return disToSrcMap.get(currPointKey);
      }

      // 遍歷8個方向
      for (int i = 0; i < 8; i++) {
        // 新點座標
        int adjX = point.x + X_OFFSET[i];
        int adjY = point.y + Y_OFFSET[i];
        if (!isValid(adjX, adjY, grid)) {
          continue;
        }
        int newPointKey = adjX * colCnt + adjY;
        // 找過了就不用再BFS
        if (disToSrcMap.containsKey(newPointKey)) {
          continue;
        }
        queue.offer(new Point1(adjX, adjY));
        disToSrcMap.put(newPointKey, disToSrcMap.get(currPointKey) + 1);
      }

    }
    return -1;
  }

  private boolean isValid(int x, int y, boolean[][] grid) {
    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
      return false;
    }
    // 值1表示障礙物，返回false
    return !grid[x][y];
  }
}

class Point1 {

  int x;
  int y;

  Point1() {
    x = 0;
    y = 0;
  }

  Point1(int a, int b) {
    x = a;
    y = b;
  }
}
