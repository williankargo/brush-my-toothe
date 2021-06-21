package leetcode.TwoPointer.NSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 時O(N^2) ， 空O(K), K為解的個數
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return results;
        }

        // O(NlogN)棑序
        Arrays.sort(nums);

        // 遍歷三元組中的最小數
        for (int i = 0; i < nums.length - 2; i++) { // 後兩個數找了沒意義，不夠兩個數配對

            if (i > 0 && nums[i] == nums[i - 1]) { // 為了去重, i>0為了防後面越界
                continue;
            }

            // -7 |-7____ 2____ 5|
            // -----i+1-----length-1
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];

            twoSum(nums, left, right, target, results);

        }

        return results;
    }

    public void twoSum(int[] nums, int left, int right, int target, List<List<Integer>> results) {
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                generateTriplet(nums, results, left, right, target);
                left++;
                right--;

                // 去重left
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }

                // 去重right
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (sum < target) {
                left++;
            }

            else {
                right--;
            }

        }
    }

    private void generateTriplet(int[] nums, List<List<Integer>> results, int left, int right, int target) {
        ArrayList<Integer> triplet = new ArrayList<>();

        // [num1, num2, num3]
        triplet.add(-target);
        triplet.add(nums[left]);
        triplet.add(nums[right]);

        results.add(triplet);

    }

}
