package leetcode.DP.CoordinateDP;

// 時O(n^2)
public class LongestIncreasingSubsequence_76 {

    public int longestIncreasingSubsequence(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int n = nums.length;

        // state: 表示以下標i結尾的龍最長是多長
        // 任一點都可能是頭
        int[] dp = new int[n];

        // initialization
        for(int i = 0; i < n; i++){
            dp[i] = 1;
        }

        // function
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }


        // answer：任一點都可能是尾
        int max = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
