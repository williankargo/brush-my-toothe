package leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestContinuousIncreasingSubsequenceII_398 {

  public int longestContinuousIncreasingSubsequence2(int[][] matrix) {

    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }

    // 矩陣的行數和列數
    int n = matrix.length, m = matrix[0].length;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    // int[][] matrix -> [行 列 值]，存入list
    List<List<Integer>> points = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        points.add(Arrays.asList(i, j, matrix[i][j]));
      }
    }

    // 並照值的大小，由小到大排列
    points.sort(Comparator.comparingInt(p -> p.get(2)));

    // dp[i][j]表示以座標(i,j)結尾的「小到大」長度
    int[][] dp = new int[n][m];

    // 按照值從小到大排序遍歷所有點
    for (int i = 0; i < points.size(); i++) {
      int x = points.get(i).get(0);
      int y = points.get(i).get(1);

      // initialization
      dp[x][y] = 1;

      for (int j = 0; j < 4; j++) {
        int prevX = x - dx[j];
        int prevY = y - dy[j];

        if (prevX < 0 || prevY < 0 || prevX >= n || prevY >= m) {
          continue;
        }

        // 找周圍比自己小的點，並加上該點的 dp[prevX][prevY]
        if (matrix[prevX][prevY] < matrix[x][y]) {
          dp[x][y] = Math.max(dp[x][y], dp[prevX][prevY] + 1);
        }
      }
    }

    int longest = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        longest = Math.max(longest, dp[i][j]);
      }
    }

    return longest;
  }
}