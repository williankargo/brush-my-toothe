package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

// 433.
public class NumberOfIslands_433 {

  // 四個方向的偏移量
  int[] deltaX = {0, 1, -1, 0};
  int[] deltaY = {1, 0, 0, -1};

  public int numIslands(boolean[][] grid) {
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return 0;
    }

    int islands = 0;
    int row = grid.length, col = grid[0].length;
    boolean[][] visited = new boolean[row][col]; // 紀錄某點是否已被BFS過

    // 遍歷矩陣中的每一點
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        // 如果是海洋或已被visited過的，跳過
        if (grid[i][j] && !visited[i][j]) {
          bfs(grid, i, j, visited);
          islands++;
        }
      }
    }
    return islands;
  }

  private void bfs(boolean[][] grid, int x, int y, boolean[][] visited) {
    Queue<Coordinate> queue = new ArrayDeque<>();
    queue.offer(new Coordinate(x, y));
    visited[x][y] = true;

    while (!queue.isEmpty()) {
      Coordinate coor = queue.poll();
      // 遍歷上右左下四個方向
      for (int direction = 0; direction < 4; direction++) {
        int newX = coor.x + deltaX[direction];
        int newY = coor.y + deltaY[direction];
        if (!isValid(grid, newX, newY, visited)) {
          continue;
        }
        queue.offer(new Coordinate(newX, newY));
        visited[newX][newY] = true;
      }
    }
  }

  private boolean isValid(boolean[][] grid, int x, int y, boolean[][] visited) {
    int row = grid.length, column = grid[0].length;
    // 判斷是否出界
    if (x < 0 || x >= row || y < 0 || y >= column) {
      return false;
    }
    // 已經BFS過的就不要再BFS
    if (visited[x][y]) {
      return false;
    }
    // 如果是1，則為true 如果是0，則為false
    return grid[x][y];
  }
}

class Coordinate { // pair

  int x, y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
