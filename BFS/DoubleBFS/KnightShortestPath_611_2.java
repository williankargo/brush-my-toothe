package leetcode.BFS.DoubleBFS;


import java.util.ArrayDeque;
import java.util.Queue;

public class KnightShortestPath_611_2 {

  public static final int[][] DIRECTIONS = {{1, 2}, {-1, 2}, {-1, -2}, {1, -2}, {2, 1}, {2, -1}, {-2, -1},
      {-2, 1}};

  static class Point {

    int x;
    int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }

  public int shortestPath(boolean[][] grid, Point source, Point destination) {

    if (grid == null || grid.length == 0) {
      return -1;
    }
    if (grid[0] == null || grid[0].length == 0) {
      return -1;
    }
    if (source.x == destination.x && source.y == destination.y) {
      return 0;
    }
    if (grid[source.x][source.y]) {
      return -1;
    }

    Queue<Point> forwardQueue = new ArrayDeque<>(); // 用連續空間比較快
    Queue<Point> backwardQueue = new ArrayDeque<>();

    int n = grid.length;
    int m = grid[0].length;

    boolean[][] forwardSet = new boolean[n][m];
    boolean[][] backwardSet = new boolean[n][m];

    forwardQueue.offer(new Point(source.x, source.y));
    backwardQueue.offer(new Point(destination.x, destination.y));

    forwardSet[source.x][source.y] = true;
    backwardSet[destination.x][destination.y] = true;

    int distance = 0;
    while (!forwardQueue.isEmpty() && !backwardQueue.isEmpty()) {

      distance++;
      if (extendQueue(forwardQueue, forwardSet, backwardSet, grid)) {
        return distance;
      }

      distance++;
      if (extendQueue(backwardQueue, backwardSet, forwardSet, grid)) {
        return distance;
      }

    }

    return -1;
  }

  private boolean extendQueue(Queue<Point> queue,
      boolean[][] visited,
      boolean[][] oppositeVisited,
      boolean[][] grid) {

    int queueLength = queue.size(); // 每次只遍歷一層
    for (int i = 0; i < queueLength; i++) {

      int x = queue.peek().x;
      int y = queue.poll().y;
      for (int j = 0; j < 8; j++) {
        int newX = x + DIRECTIONS[j][0];
        int newY = y + DIRECTIONS[j][1];
        if (!isValid(newX, newY, grid, visited)) {
          continue;
        }

        if (oppositeVisited[newX][newY]) { // 前後相交了
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
    if (y < 0 || y >= grid.length) {
      return false;
    }
    if (grid[x][y]) {
      return false;
    }
    if (visited[x][y]) {
      return false;
    }
    return true;
  }

}
