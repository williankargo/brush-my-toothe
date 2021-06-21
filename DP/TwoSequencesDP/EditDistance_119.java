package leetcode.DP.TwoSequencesDP;

public class EditDistance_119 {

  // 時空 O(NM)
  public int minDistance(String word1, String word2){

    if(word1 == null || word2 == null){
      return -1;
    }

    int n = word1.length();
    int m = word2.length();

    // state: word1前i個字符 到 word2前j個字符 的轉化最小成本
    int[][] dp = new int[n + 1][m + 1];

    // initialization
    for(int i = 1; i <= n; i++){
      dp[i][0] = i;
    }
    for(int j = 1; j <= m; j++){
      dp[0][j] = j;
    }

    // function
    for(int i = 1; i <= n; i++){
      for(int j = 1; j <= m; j++){
        if(word1.charAt(i - 1) == word2.charAt(j - 1)){ // 最後一個相等
          dp[i][j] = Math.min(dp[i - 1][j - 1], // 無替換成本
              Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)); // 後面這個無腦加入
        }else{  // 最後一個不相等
          dp[i][j] = Math.min((dp[i - 1][j - 1] + 1), // 有一個替換成本
              Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)); // 對word1刪除尾 和 對word1增加尾 的成本
        }
      }
    }


    // answer
    return dp[n][m];
  }


  // 可以用滾動數組，因為只依賴於前面一個
  // 時 O(NM)  空(2M)
  public int minDistance_2(String word1, String word2){

    if(word1 == null || word2 == null){
      return -1;
    }

    int n = word1.length();
    int m = word2.length();

    // state: word1前i個字符 到 word2前j個字符 的轉化最小成本
    int[][] dp = new int[2][m + 1];

    // function
    for(int i = 0; i <= n; i++){
      for(int j = 0; j <= m; j++) {
        if (i == 0) { // init
          dp[i % 2][j] = j;
        } else if (j == 0) { // init
          dp[i % 2][j] = i;
        } else {
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // 最後一個相等
            dp[i % 2][j] = Math.min(dp[(i - 1) % 2][j - 1], // 無替換成本
                Math.min(dp[(i - 1) % 2][j] + 1, dp[i % 2][j - 1] + 1)); // 後面這個無腦加入
          } else {  // 最後一個不相等
            dp[i % 2][j] = Math.min((dp[(i - 1) % 2][j - 1] + 1), // 有一個替換成本
                Math.min(dp[(i - 1) % 2][j] + 1,
                    dp[i % 2][j - 1] + 1)); // 對word1刪除尾 和 對word1增加尾 的成本
          }
        }
      }
    }


    // answer
    return dp[n % 2][m];
  }
}
