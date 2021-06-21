package leetcode.Hash;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueNumberInDataStream_685 {

  public int firstUniqueNumber(int[] nums, int number) {

    if (nums == null || nums.length == 0) {
      return -1;
    }

    // key代表num, value代表這個num是否unique
    Map<Integer, Boolean> numUniqueMap = new HashMap<>();

    for (int num : nums) {

      numUniqueMap.put(num, !numUniqueMap.containsKey(num));  // #1 -> !false  #2 #3...-> !true

      if (num == number) {
        break;
      }
    }

    // 如果沒有遇到終止數字
    if (!numUniqueMap.containsKey(number)) {
      return -1;
    }

    for (int num : nums) {
      if (numUniqueMap.get(num)) {
        return num;
      }
    }
    return -1;
  }
}
