package leetcode.TwoPointer.NSum;

import java.util.HashMap;
import java.util.Map;

// 時O(N^2) ， 空O(N^2) 因為建了一個map
public class FourSumII {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        // key為(a+b)之和, value為出現的頻次
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;

                // 如果沒有keysum, 返回默認值0
                int frqcy = map.getOrDefault(sum, 0);
                map.put(sum, frqcy + 1);
            }
        }

        int cnt = 0;
        for (int c : C) {
            for (int d : D) {
                int sum = c + d;
                // 如果-sum出現在AB之和中，累加頻次
                cnt += map.getOrDefault(-sum, 0);
            }
        }
        return cnt;
    }
}
