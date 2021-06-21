package leetcode.DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII_132 {

  public int[] dx = {1, 0, -1, 0};   //搜索方向
  public int[] dy = {0, 1, 0, -1};

  public List<String> wordSearchII(char[][] board, List<String> words) {

    if (board == null || board.length == 0) {
      return new ArrayList<>();
    }
    if (board[0] == null || board[0].length == 0) {
      return new ArrayList<>();
    }

    boolean[][] visited = new boolean[board.length][board[0].length];

    // wordSet中包含所有需要尋找的詞
    Set<String> wordSet = new HashSet<>();

    // prefixSet中包含所有詞的前綴（包括整個詞）
    Set<String> prefixSet = new HashSet<>();

    // 遍歷格子中的每一個點，從這個點開始進行bfs
    for (String word : words) {
      for (int i = 0; i < word.length(); i++) {
        prefixSet.add(word.substring(0, i + 1)); // [0,1], [0,1,2], [0,1,2,3], [0,1,2,3,4] ...
      }
      wordSet.add(word);
    }

    Set<String> resultSet = new HashSet<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        visited[i][j] = true; // 選定一個點開始遍歷
        dfs(board, visited, i, j, String.valueOf(board[i][j]), wordSet, prefixSet, resultSet);
        // String.valueOf(char[]) !
        visited[i][j] = false;
      }
    }
    return new ArrayList<String>(resultSet);
  }

  private void dfs(char[][] board, boolean[][] visited, int x, int y, String word,
      Set<String> wordSet, Set<String> prefixSet, Set<String> resultSet) {

    // 出口
    // 如果不是prefix，沒有必要繼續走下去，回退一步
    if (!prefixSet.contains(word)) {
      return;
    }

    // 這裡已經找到了dog，但還需繼續往下找，有可能找到另一個時dogecoin
    if (wordSet.contains(word)) {
      resultSet.add(word);
    }

    // 分解
    // 上下左右找起來
    for (int i = 0; i < 4; i++) {
      int adjX = x + dx[i];
      int adjY = y + dy[i];

      if (!inside(board, adjX, adjY) || visited[adjX][adjY]) {
        continue;
      }

      visited[adjX][adjY] = true;
      dfs(board, visited, adjX, adjY, word + board[adjX][adjY], wordSet, prefixSet, resultSet);
      visited[adjX][adjY] = false;
    }
  }

  private boolean inside(char[][] board, int x, int y) {
    return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
  }
}



