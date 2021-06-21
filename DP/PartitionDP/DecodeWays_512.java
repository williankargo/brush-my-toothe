package leetcode.DP.PartitionDP;

public class DecodeWays_512 {


    public int numDecodings(String s) { // 灌數字進去

        if (s == null || s.length() == 0) {
            return 0; // 排除了dp[0] == 1 return 1的錯誤可能性
        }

        int n = s.length();
        // 前i個有多少種解碼方法
        int dp[] = new int[n + 1];
        dp[0] = 1; // 為了方便dp[2]產生正確的數字
        dp[1] = BeWordOK(s.substring(0, 1)); // 第一個字符可否解碼
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * BeWordOK(s.substring(i - 1, i)) + dp[i - 2] * BeWordOK(s.substring(i - 2, i));
        }

        return dp[n];
    }

    private int BeWordOK(String number) {

        int num = Integer.parseInt(number);
        int length = number.length();  // 要設length為了防止 01 掉到length == 2彈出有效值

        if (length == 1 && 1 <= num && num <= 9) {
            return 1;
        } else if (length == 2 && 10 <= num && num <= 26) {
            return 1;
        }
        return 0;
    }
}