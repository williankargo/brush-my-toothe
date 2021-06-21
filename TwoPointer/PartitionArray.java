package leetcode.TwoPointer;

// return the partitioning index that the first index i -> nums[i] >= k
public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }

        // 兩端的相向指針
        int left = 0, right = nums.length - 1;

        while (left <= right){
            while (left <= right && nums[left] < k){  // 依照題意，左指針找一個不屬於左邊的數字並跳出while
                left++;
            }
            while (left <= right && nums[right] >= k){
                right--;
            }
            
            // 交換左右指針數字，雙方都到了正確的一端
            if (left <= right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }

        // R L  最後左指針的位置即為右邊partition的起點
        return left;
    }
}
