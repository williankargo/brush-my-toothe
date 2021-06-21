package leetcode.DP.PartitionDP;

// 時 O(n^2 * k)
public class CopyBooks_437_2 {

    public int copyBooks(int[] pages, int k) {

        if (pages == null || pages.length == 0 || k == 0) {
            return 0;
        }

        int n = pages.length;

        // 計算前綴和
        int[] prefixSum = new int[n + 1]; // +1 包括前0個
        for(int i = 1; i <= n; i++){
            prefixSum[i] = prefixSum[i - 1] + pages[i - 1]; // 第i個
        }

        // state: dp[i][j]表是前i本書分給j個人複印，需要多久時間
        int[][] dp = new int[n + 1][k + 1];

        // initialization
        for(int i = 1; i <= n; i++){
            dp[i][0] = Integer.MAX_VALUE; // dp[0][i]已經默認為0
        }

        // function
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int prev = 0; prev < i; prev++){
                    int cost = prefixSum[i] - prefixSum[prev];
                    dp[i][j] = Math.min(dp[i][j],Math.max(dp[prev][j - 1],cost));
                }
            }
        }


        // answer
        return dp[n][k];
    }
}
