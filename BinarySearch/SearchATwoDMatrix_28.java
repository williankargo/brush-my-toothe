package leetcode.BinarySearch;

public class SearchATwoDMatrix_28 {

  public boolean searchMatrix(int[][] matrix, int target) {

    if (matrix == null || matrix.length == 0) {
      return false;
    }
    if (matrix[0] == null || matrix[0].length == 0) {
      return false;
    }

    int n = matrix.length, m = matrix[0].length;
    int start = 0, end = n * m - 1;
    while (start + 1 < end) {

      int mid = start + (end - start) / 2;
      if (get(matrix, mid) < target) {
        start = mid;
      } else {
        end = mid;
      }
    }

    if (get(matrix, start) == target) {
      return true;
    }
    if (get(matrix, end) == target) {
      return true;
    }

    return false;
  }

  private int get(int[][] matrix, int index) {
    int x = index / matrix[0].length;
    int y = index % matrix[0].length;
    return matrix[x][y];
  }
}
