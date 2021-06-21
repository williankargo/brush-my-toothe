package leetcode.TwoPointer.NSum;

import java.util.Arrays;

// 時O(N^2) ， 空O(1)
public class TriangleCount {

    public int triangleCount(int[] S) {
        if (S == null || S.length < 3) {
            return 0;
        }

        // twoSum雙指針問題需要在有序的數據上進行
        Arrays.sort(S);
        int cnt = 0;

        // 遍歷最大邊
        for (int i = 2; i < S.length; i++) {
            cnt += getTriangleCount(S, i);
        }
        return cnt;
    }

    private int getTriangleCount(int[] arr, int targetIndex) {
        int cnt = 0;

        // 尋找範圍為 [0, targetIndex - 1]
        int left = 0;
        int right = targetIndex - 1;

        int targetSum = arr[targetIndex];
        int sum = 0;
        while (left < right) {
            sum = arr[left] + arr[right];
            // sum > target，可以組成三角形
            if (sum > targetSum) {
                cnt += right - left; // 一次求出多個可行解的個數
                right--;
            }

            else {
                left++;
            }
        }
        return cnt;

    }

}
