package leetcode.DFS.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringPermutationII_10 {

  public static void main(String[] args) {
    StringPermutationII_10 demo = new StringPermutationII_10();
    List<String> ans = demo.stringPermutation2("abc");
    System.out.println(ans);
  }

  public List<String> stringPermutation2(String str) {
    List<String> permutations = new ArrayList<String>();

    if (str == null) {
      return permutations;
    }

    char[] chars = str.toCharArray();

    Arrays.sort(chars); // (1) 方便去重 (2) 依字母序

    boolean[] visited = new boolean[chars.length];

    // 用stringBuilder較char[]節省空間
    StringBuilder permutation = new StringBuilder();
    dfs(chars, visited, permutation, permutations);
    return permutations;
  }

  // [abc] -> [abc, acb, bac, bca, cab, cba]
  // 和combination相比：不用傳入index!
  private void dfs(char[] chars, boolean[] visited, StringBuilder permutation,
      List<String> permutations) {

    // 出口
    if (permutation.length() == chars.length) {
      permutations.add(permutation.toString()); // toSting() 當下就會直接回傳一個新的String物件
      return;
    }

    // 拆解
    for (int i = 0; i < chars.length; i++) {

      // 去重：如果前面同樣的還沒走到，我就不能先走
      if (i > 0 && chars[i - 1] == chars[i] && !visited[i - 1]) {
        continue;
      }

      if (visited[i]) {
        continue;
      }

      // 已訪問
      visited[i] = true;
      permutation.append(chars[i]);  // StringBuilder 屬mutable可以直接修改原物件

      dfs(chars, visited, permutation, permutations);

      permutation.deleteCharAt(permutation.length() - 1);
      visited[i] = false;
    }
  }
}
