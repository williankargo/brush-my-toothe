package leetcode.DFS;

import java.util.ArrayList;
import java.util.List;

// 搜索順序優化(理想下，可是實際能沒有優化)
public class SudokuSolver_802_2 {

  public void solveSudoku(int[][] board) {
    dfs(board);
  }

  class Position {

    int x;
    int y;
    int[] choices;

    Position(int x, int y, int[] choices) {
      this.x = x;
      this.y = y;
      this.choices = choices;
    }
  }

  private boolean dfs(int[][] board) {

    // 把數字選擇最少的那個格子拿出來，看它的座標是啥，可以選的value有哪些
    Position result = getLeastChoicesGrid(board);

    if (result.x == -1) { // 如果找不到，說明所有格子都填滿了
      return true;
    }

    for (int value : result.choices) {

      board[result.x][result.y] = value;

      if (dfs(board)) {
        return true;
      }

      board[result.x][result.y] = 0; // backtracking
    }

    // 如果有一位置一個數都填不了，可能性是0個，上面的for會進不去，在這裡return false
    return false;
  }

  // 把數字選擇最少的那個格子拿出來，看它的座標是啥，可以選的value有哪些
  private Position getLeastChoicesGrid(int[][] board) {

    // 初始化設(-1,-1) 不屬於任何一格，並默認一個比9大的空間，為了如果xy有找到一定可以和初始值交換
    Position result = new Position(-1, -1, new int[10]);

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != 0) { // 要找空白的
          continue;
        }
        List<Integer> vals = new ArrayList<>();
        for (int value = 1; value < 10; value++) {
          if (isValid(i, j, value, board)) {
            vals.add(value);
          }
        }
        if (vals.size() < result.choices.length) {
          result.x = i;
          result.y = j;
          result.choices = vals.stream().mapToInt(n -> n).toArray(); // ArrayList -> int[]
        }
      }
    }
    return result;
  }


  private boolean isValid(int x, int y, int value, int[][] board) {

    for (int i = 0; i < 9; i++) {
      if (board[x][i] == value) {
        return false;
      }

      if (board[i][y] == value) {
        return false;
      }

      if (board[(x / 3) * 3 + i / 3][(y / 3) * 3 + i % 3] == value) { // 看備忘錄
        return false;
      }
    }
    return true;
  }

}
