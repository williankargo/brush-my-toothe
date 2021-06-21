package leetcode.DP;


// 時 O(N^2) 狀態總數(N+1)(N+1) * 每個狀態的時間花費(1) <- 因為沒有for
// 空 O(N^2)
// 遞歸深度 N <- 用樹高想
public class WildcardMatching_192 {

  public static void main(String[] args) {
    WildcardMatching_192 demo = new WildcardMatching_192();
    String a = "aa";
    String b = "**";
    demo.isMatch(a, b);
  }

  public boolean isMatch(String s, String p) {

    if (s == null || p == null) {
      return false;
    }

    // !!! 記憶走過的路徑 !!! (java: pass by Value 參考型別在傳遞時永遠指向同一個物件)
    boolean[][] memo = new boolean[s.length()][p.length()];
    boolean[][] visited = new boolean[s.length()][p.length()];

    return isMatchHelper(s, 0, p, 0, memo, visited);
  }

  private boolean isMatchHelper(String source, int sIndex, String pattern, int pIndex,
      boolean[][] memo, boolean[][] visited) {

    // 出口
    if (pIndex == pattern.length()) { // pIndex走完了
      return sIndex == source.length(); // 看sIndex是不是也走完了
    }

    if (sIndex == source.length()) {
      return allStar(pattern, pIndex);
    }

    if (visited[sIndex][pIndex]) {
      return memo[sIndex][pIndex];
    }

    // 拆解
    char sChar = source.charAt(sIndex);
    char pChar = pattern.charAt(pIndex);
    boolean match;

    if (pChar != '*') {
      match =
          charMatch(sChar, pChar) && isMatchHelper(source, sIndex + 1, pattern, pIndex + 1, memo,
              visited);
    } else {
      match =
          isMatchHelper(source, sIndex, pattern, pIndex + 1, memo, visited) || isMatchHelper(source,
              sIndex + 1, pattern, pIndex, memo, visited); // 二分法 : 一邊*沒吃 , 一邊*吃一個
    }

    visited[sIndex][pIndex] = true;   // 空間 O(N^2)
    memo[sIndex][pIndex] = match;

    return match;
  }


  private boolean charMatch(char sChar, char pChar) {
    return (sChar == pChar || pChar == '?');
  }

  private boolean allStar(String p, int pIndex) {
    for (int i = pIndex; i < p.length(); i++) {
      if (p.charAt(i) != '*') {
        return false;
      }
    }
    return true;
  }
}
