package leetcode.DP.PartitionDP;

import java.util.Set;

public class WordBreak_107 {

    // 時 O(N^2) -> 太久了
    public boolean wordBreak(String s, Set<String> wordSet) {

        if(s == null){
            return true;
        }

        int n = s.length();
        // 前i個字符是否可以被劃分成若干個單詞
        boolean[] dp = new boolean[n + 1]; // 前綴型都要+1

        // 空串為true
        dp[0] = true;

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                if(!dp[j]){
                    continue;
                }
                String word = s.substring(j, i);
                if(wordSet.contains(word)){
                    dp[i] = true;
                    break; // 跳出內層for循環
                }
            }
        }
        return dp[n];
    }

    // 時 O(nL + M)
    public  boolean wordBreak2(String s, Set<String> wordSet){

        if(s == null){
            return true;
        }

        int maxLength = 0;
        for(String word: wordSet){ // 時O(M)
            maxLength = Math.max(maxLength, word.length());
        }

        int n = s.length();
        boolean dp[] = new boolean[n + 1];
        dp[0] = true;

        for(int i = 1; i <= n; i++){ // O(n)
            for(int l = 1;l <= maxLength ;l++){ // O(L)
                if(i < l){
                    break;
                }
                if(!dp[i - l]){
                    continue;
                }
                String word = s.substring(i - l, i); // i - l == j
                if(wordSet.contains(word)){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }



}
