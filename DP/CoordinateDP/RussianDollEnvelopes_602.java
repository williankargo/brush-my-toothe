package leetcode.DP.CoordinateDP;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes_602 {

    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0){
            return 0;
        }

        // 按照w升序排序，w相同再按照h降序排序 -> 變成了 在h中找最長上升子序列 的問題
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {

                // 看w有沒有相等
                if(arr1[0] == arr2[0]){
                    return arr2[1] - arr1[1]; // 如果有，h大到小排序
                }else{
                    return arr1[0] - arr2[0]; // 如果沒有，W小到大排序
                }
            }
        });

        // Greedy的做法
        int n = envelopes.length;
        int[] lis = new int[n + 1];
        lis[0] = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            lis[i] = Integer.MAX_VALUE;
        }

        // (1,2) (2,3) (2,1) (3,2) (3,1) -> 這樣排，你用LIS方法做做看，會發現很巧的就得出答案了
        int longest = 0;
        for(int i = 0; i < n; i++){
            int index = firstGTE(lis, envelopes[i][1]);
            lis[index] = envelopes[i][1];
            longest = Math.max(longest, index);
        }

        return longest;
    }

    // Greater than or Equal to
    private int firstGTE(int[] nums, int target){
        int start = 0, end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(nums[mid] >= target){
                end = mid;  // 找左邊界的概念
            }else{
                start = mid;
            }
        }

        // 左邊界優先的概念
        if(nums[start] >= target){
            return start;
        }
        return end;
    }
}
