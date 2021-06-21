package leetcode.dataStructure.ExternalSorting;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication_654 {

  public int[][] multiply(int[][] A, int[][] B) {

    int rowA = A.length;
    int ColumnA = A[0].length; // == rowB
    int ColumnB = B[0].length;
    int[][] ans = new int[rowA][ColumnB];

    // 把B的非零column index 一row一row的挑出來(橫的)
    // 時O(ColumnA * ColumnB)
    List<List<Integer>> B_nonZero_ColumnIndexCluster = new ArrayList<>();
    for (int i = 0; i < ColumnA; i++) {
      List<Integer> carrier = new ArrayList<>();
      for (int j = 0; j < ColumnB; j++) {
        if (B[i][j] != 0) {
          carrier.add(j);
        }
      }
      B_nonZero_ColumnIndexCluster.add(carrier);
    }

    // 時O(ColumnA * ColumnB * K) K為B的每column不為零的個數，且排除了A裡零的點再乘下去的時間
    for (int i = 0; i < rowA; i++) {
      for (int j = 0; j < ColumnA; j++) {
        if (A[i][j] != 0) {
          for (int colIndex : B_nonZero_ColumnIndexCluster.get(j)) {
            ans[i][colIndex] += A[i][j] * B[j][colIndex];  // 不能用正常乘方式想 // 備忘錄
          }
        }
      }
    }

    return ans;
  }

}
