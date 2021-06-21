package leetcode.BFS.DoubleBFS;

import java.util.ArrayDeque;
import java.util.Queue;
import leetcode.BFS.DoubleBFS.KnightShortestPath_611_2.Point;

public class KnightShortestPath_630_2 {

  public static final int[][] FORWARD_DIRECTIONS = {{1, 2}, {-1, 2}, {2, 1}, {-2, 1}};
  public static final int[][] BACKWARD_DIRECTIONS = {{-1, -2}, {1, -2}, {-2, -1}, {2, -1}};

  public int shortestPath2(boolean[][] grid) {

    if (grid == null || grid.length == 0) {
      return -1;
    }
    if (grid[0] == null || grid[0].length == 0) {
      return -1;
    }

    int n = grid.length;
    int m = grid[0].length;
    if (grid[n - 1][m - 1] == true) {
      return -1;
    }
    if (n * m == 1) {
      return 0;
    }

    Queue<Point> forwardQueue = new ArrayDeque<>();
    Queue<Point> backwardQueue = new ArrayDeque<>();

    boolean[][] forwardSet = new boolean[n][m];
    boolean[][] backwardSet = new boolean[n][m];

    // extra: 須先new KnightShortestPath_611_2()才能使用裡面的class Point，或是直接把Point class static
    forwardQueue.offer(new Point(0, 0));
    backwardQueue.offer(new Point(n - 1, m - 1));
    forwardSet[0][0] = true;
    backwardSet[n - 1][m - 1] = true;

    int distance = 0;
    while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {

      distance++;
      if (extendQueue(forwardQueue, FORWARD_DIRECTIONS, forwardSet, backwardSet, grid)) {
        return distance;
      }

      distance++;
      if (extendQueue(backwardQueue, BACKWARD_DIRECTIONS, backwardSet, forwardSet, grid)) {
        return distance;
      }

    }
    return -1;
  }

  private boolean extendQueue(Queue<Point> queue,
      int[][] directions,
      boolean[][] visited,
      boolean[][] oppositeVisited,
      boolean[][] grid) {

    int queueLength = queue.size(); // 每次只遍歷一層
    for (int i = 0; i < queueLength; i++) {
      Point head = queue.poll();
      int x = head.x;
      int y = head.y;

      for (int j = 0; j < 4; j++) {
        int newX = x + directions[j][0];
        int newY = y + directions[j][1];
        if (!isValid(newX, newY, grid, visited)) {
          continue;
        }
        if (oppositeVisited[newX][newY]) {
          return true;
        }
        queue.offer(new Point(newX, newY));
        visited[newX][newY] = true;
      }
    }
    return false;
  }

  private boolean isValid(int x, int y, boolean[][] grid, boolean[][] visited) {

    if (x < 0 || x >= grid.length) {
      return false;
    }
    if (y < 0 || y > grid[0].length) {
      return false;
    }
    if (visited[x][y]) {
      return false;
    }
    if (grid[x][y]) {
      return false;
    }

    return true;
  }

}
