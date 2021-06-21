package leetcode.DFS;

public class SudokuSolver_802 {

  public void solveSudoku(int[][] board) {

    dfs(board, 0);
  }

  private boolean dfs(int[][] board, int index) {

    // 出口
    if (index == 81) { // 會有0-80共81個
      return true;
    }

    int x = index / 9, y = index % 9;

    if (board[x][y] != 0) { // 遇到的空格有數字了
      return dfs(board, index + 1);
    }

    for (int value = 1; value <= 9; value++) {

      if (!isValid(x, y, value, board)) { // 就目前的狀況，把該格設為value合不合理
        continue;
      }

      board[x][y] = value; // 先把該格設為該value
      if (dfs(board, index + 1)) { // 然後找下去，看正不正確
        return true; // 就不用到下面backtracking了
      }

      board[x][y] = 0; // 不正確就重設value

    }

    return false; // false??? 代表前面的board[x][y]代錯了，需要回去重代

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
