package leetcode.DFS.permutation;

import java.util.ArrayList;
import java.util.List;

public class NQueensII_34 {

  public static int sum;  // 全局變量
  public int totalNQueens(int n) {

    if (n <= 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }

    int sum = 0;

    DFSSearch(new ArrayList<>(), n);

    return sum;
  }

  private void DFSSearch(List<Integer> cols, int n) {

    // 出口
    if (cols.size() == n) {
      sum++;
      return;
    }

    for (int colIndex = 0; colIndex < n; colIndex++) {
      if (!isValid(colIndex, cols)) {
        continue;
      }

      cols.add(colIndex);
      DFSSearch(cols, n);
      cols.remove(cols.size() - 1);
    }
  }

  private boolean isValid(int col, List<Integer> cols) {

    // row safe safe // column out out
    int row = cols.size(); // 可以得到此點的row index
    for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {

      // 直的方向
      if (col == cols.get(rowIndex)) {
        return false;
      }

      // 右上左下
      if (col + row == cols.get(rowIndex) + rowIndex) { // cols裡面放columnIndex, 用i遍歷可以拿出column
        return false;
      }

      // 左上右下
      if (col - row == cols.get(rowIndex) - rowIndex) {
        return false;
      }
    }

    return true;
  }
}
