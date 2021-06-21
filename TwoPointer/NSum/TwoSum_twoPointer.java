package leetcode.TwoPointer.NSum;

import java.util.ArrayList;
import java.util.List;

public class TwoSum_twoPointer {

    // 空 O(N) 需把所有數據紀錄下來
    public List<Integer> nums; // list裝升序數據
    
    public void TwoSum() {
        nums = new ArrayList<Integer>();
    }

    // add: 時 O(N)
    public void add(int number) {
        nums.add(number);
        int index = nums.size() - 1;
        // 先把插進來的數放在最後一個，並和前面已經排序好的數比較，逐漸交替往前放
        while (index > 0 && nums.get(index - 1) > nums.get(index)) {
            swap(nums, index);
            index--;
        }
    }

    // 在list中交換 (index - 1)和index對應的值
    private void swap(List<Integer> nums, int index) {
        int temp = nums.get(index);
        nums.set(index, nums.get(index - 1));
        nums.set(index - 1, temp);
    }

    // find: 時 O(N)
    public boolean find(int targetValue) {

        // 左右兩顆相向雙指針
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int twoSum = nums.get(left) + nums.get(right);

            if (twoSum < targetValue) {
                left++;
            } else if (twoSum > targetValue) {
                right--;
            } else {
                return true;
            }
        }
        // 找不到，傳false
        return false;
    }

}
