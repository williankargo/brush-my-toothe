package leetcode.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class InsertDeleteGetRandomO1_657 {

}

class RandomizedSet {

  // 存放數字
  ArrayList<Integer> nums;
  // value : index
  HashMap<Integer, Integer> numToIndexMap;
  Random rand;

  RandomizedSet() {
    nums = new ArrayList<>();
    numToIndexMap = new HashMap<>();
    rand = new Random();
  }

  // O(1)
  public boolean insert(int val) {

    // 如果該數字已經存在，返回false
    if (numToIndexMap.containsKey(val)) {
      return false;
    }
    // 如果該數字不存在
    numToIndexMap.put(val, nums.size());
    nums.add(val);
    return true;
  }

  // O(1) 隨機生成一個 [0] - [nums.size()-1] 的數字
  public int getRandom() {
    return nums.get(rand.nextInt(nums.size()));  // 不會到nums.size()
  }


  // O(1)
  public boolean remove(int val) {

    if (!numToIndexMap.containsKey(val)) {
      return false;
    }

    int index = numToIndexMap.get(val);

    // 動set 不搬list(會O(n)) !!!!!!!!!!!!!!!!!!!
    if (index < nums.size() - 1) { // 如果index在最後一個就不用這樣搬了
      int last = nums.get(nums.size() - 1);
      nums.set(index, last);
      numToIndexMap.put(last, index);
    }

    // 移除最後一點的對應關係
    numToIndexMap.remove(val);

    // 移除最後一點 nums.remove(nums.size() - 1)
    nums.remove(nums.size() - 1);  // nums.remove(放index)  -> 所以不能寫成 nums.remove(val)

    return true;
  }

}