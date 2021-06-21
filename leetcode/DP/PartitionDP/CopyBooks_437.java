package leetcode.DP.PartitionDP;

public class CopyBooks_437 {

    public int copyBooks(int[] pages, int k) {
        if(pages == null || pages.length == 0 || k == 0){
            return 0;
        }

        int n = pages.length;

        // 計算前綴和
        int[] prefixSum = new int[n + 1]; // 包含前0個
        for(int i = 1; i <= n; i++){
            prefixSum[i] = prefixSum[i - 1] + pages[i - 1]; // 第i個
        }

        





    }
}
