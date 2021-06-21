package leetcode.DP.CoordinateDP;

// 時O(nlogn) 但也只有幾題能用，是做過才會的題目
public class LongestIncreasingSubsequence_76_Greedy {

    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 表示長度為i個LIS的末尾的數最小是多少
        int[] lis = new int[nums.length + 1];
        lis[0] = Integer.MAX_VALUE; // 其他都最大
        for(int i = 1; i <= nums.length; i++){
            lis[i] = Integer.MIN_VALUE; // 頭部最小，不會被動到
        }

        int longest = 0;
        for(int i = 0; i < nums.length; i++){

            int index = firstGTE(lis, nums[i]); // 在lis中找大於等於當前數nums[i]的index
            lis[index] = nums[i]; // lis會被搞得升序越來越長，最後那個非初始值的list index會是答案（看講義的範例就知道）
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
