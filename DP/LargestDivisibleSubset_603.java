package leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LargestDivisibleSubset_603 {

  public static void main(String[] args) {
    LargestDivisibleSubset_603 demo = new LargestDivisibleSubset_603();
    int[] data = {1, 2, 3, 6, 8, 12};
    System.out.println(demo.largestDivisibleSubset(data));
    // [1,2,6,12] not [1,2,3,4,6,12] -> 不滿足任意兩數整除
  }

  public List<Integer> largestDivisibleSubset(int[] nums) {

    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }

    // 所有數字從小到大排序
    Arrays.sort(nums);
    int n = nums.length;

    HashMap<Integer, Integer> dp = new HashMap();
    HashMap<Integer, Integer> prev = new HashMap<>();

    // initialization
    for (int i = 0; i < n; i++) {
      dp.put(nums[i], 1);     // 初始長度1 -> 僅包含自己
      prev.put(nums[i], -1);  // -1 表示未知
    }

    // 最大整除子集的最後一個數字
    int lastNum = nums[0];

    // 遍歷從小到大的每個數字
    for (int num : nums) {

      for (Integer factor : getFactors(num)) { // factor 為 num 的因子
        if (!dp.containsKey(factor)) {
          continue;
        }

        // 和可能是未來的自己比較  不同數值同dp就代表一方不能被另一方整除 <-- 很難想到
        if (dp.get(num) < dp.get(factor) + 1) {
          dp.put(num, dp.get(factor) + 1);
          prev.put(num, factor); // 當前點 : 之前點
        }
      }

      // 找答案的最大數字
      if (dp.get(num) > dp.get(lastNum)) {
        lastNum = num;
      }
    }

    return getPath(prev, lastNum);
  }


  private List<Integer> getFactors(int num) {
    List<Integer> factors = new ArrayList<>();

    if (num == 1) { // 不包括自身，所以1沒有因子
      return factors;
    }

    int factor = 1;
    while (factor * factor <= num) { // factor只會取到 num^(1/2) (math problem)
      if (num % factor == 0) {  // 代表是因子
        factors.add(factor);

        if (factor != 1 && factor * factor != num) {  // 在上面就加入了，這裡加入會導致重複
          factors.add(num / factor);
        }
      }
      factor++;
    }
    return factors;
  }

  // 倒推求路徑
  private List<Integer> getPath(HashMap<Integer, Integer> prev, int lastNum) {
    List<Integer> path = new ArrayList<>();

    while (lastNum != -1) {
      path.add(lastNum);
      lastNum = prev.get(lastNum);
    }

    // 翻轉得到正序列
    Collections.reverse(path);
    return path;
  }
}
















