package leetcode.DP.CoordinateDP;

import java.util.*;

// 時 O(n(num^1/2))
public class LargestDivisibleSubset_603 {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        int n = nums.length;
        HashMap<Integer, Integer> dp = new HashMap<>(); // dp 為以這個i結尾的龍的目前最長長度
        HashMap<Integer, Integer> prev = new HashMap<>(); // 紀錄前一個數

        for (int i = 0; i < n; i++) {
            dp.put(nums[i], 1);
            prev.put(nums[i], -1); // 沒有前面的數
        }

        int lastNum = nums[0];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (Integer factor : getFactors(num)) {
                if (!dp.containsKey(factor)) { // 如果找到的factor不存在dp中
                    continue;
                }

                if (dp.get(num) < dp.get(factor) + 1) { // 為了保證求出那個factor是當前小於num裡的factor中最大的
                    dp.put(num, dp.get(factor) + 1); // 更新當前i
                    prev.put(num, factor);
                }

            }
            if (dp.get(num) > dp.get(lastNum)) {
                lastNum = num;
            }
        }


        return getPath(prev, lastNum);
    }

    private List<Integer> getPath(HashMap<Integer, Integer> prev, int lastNum) {
        List<Integer> path = new ArrayList<>();
        while (lastNum != -1) {
            path.add(lastNum);
            lastNum = prev.get(lastNum); // 會讓path大到小
        }
        Collections.reverse(path); // 弄回小到大
        return path;
    }

    // 找因數
    private List<Integer> getFactors(int num) {
        List<Integer> factors = new ArrayList<>();
        if (num == 1) { // 求出來的東西不能包括num
            return factors;
        }
        int factor = 1;
        while (factor * factor <= num) { // 因為因子是成對出現的，所以O(Num^1/2)就能找到
            if (num % factor == 0) {
                factors.add(factor);
                if (factor != 1 && num / factor != factor) { // 因子會成對出現
                    factors.add(num / factor);
                }
            }
            factor++;
        }
        return factors;
    }

}
