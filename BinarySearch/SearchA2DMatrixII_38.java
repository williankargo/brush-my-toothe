package leetcode.BinarySearch;

public class SearchA2DMatrixII_38 {

  // 時O(m+n) <- 最壞的狀況
  public int searchMatrix(int[][] matrix, int target) {

    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    if (matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }

    // 從左下出發的特殊算法
    int n = matrix.length;
    int m = matrix[0].length;
    int i = n - 1, j = 0, count = 0;
    while (i >= 0 && j < m) {
      if (matrix[i][j] == target) {
        count++;
        // 因為每行列的數字不重複，所以往右上移動
        i--;
        j++;
      } else if (matrix[i][j] < target) {
        j++;
      } else {
        i--;
      }
    }
    return count;
  }

}
