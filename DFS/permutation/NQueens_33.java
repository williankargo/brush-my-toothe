package leetcode.DFS.permutation;

import java.util.ArrayList;
import java.util.List;

// 時 O(Q方案個數 * 構造每個方案的時間) = O(S * N^2)
public class NQueens_33 {

  public List<List<String>> solveNQueens(int n) {

    List<List<String>> results = new ArrayList<>();
    if (n <= 0) {
      return results;
    }
    DFSSearch(results, new ArrayList<>(), n);

    return results;
  }

  private void DFSSearch(List<List<String>> results, List<Integer> cols, int n) {

    // 出口
    if (cols.size() == n) {
      results.add(Draw(cols));
      return;
    }

    for (int colIndex = 0; colIndex < n; colIndex++) {
      if (!isValid(colIndex, cols)) {
        continue;
      }

      cols.add(colIndex);
      DFSSearch(results, cols, n);
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

  private List<String> Draw(List<Integer> cols) {

    List<String> result = new ArrayList<>();
    for (int i = 0; i < cols.size(); i++) {
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < cols.size(); j++) {
        sb.append(cols.get(i) == j ? "Q" : ".");
      }
      result.add(sb.toString());
    }
    return result;
  }
}
