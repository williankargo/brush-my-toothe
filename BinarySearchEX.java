package leetcode;

public class BinarySearchEX {

    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // [1, 2, 3, 3, 4, 5, 10], target = 3 (first position)
        int start = 0, end = nums.length - 1;
        // 要点1: start + 1 < end, 避免start == end時才退出可能會死循環，現在變成相鄰時會退出
        while (start + 1 < end) {
            // 要点2：start + (end - start) / 2
            int mid = start + (end - start) / 2;
            // 要点3：=, <, > 分开讨论，mid 不+1也不-1
            if (nums[mid] < target) {
                start = mid; // start = mid or end = mid 依last or first調整
            } else if (nums[mid] == target) {
                end = mid;
            } else {
                end = mid;
            }
        }

        // 要点4: 循环结束后，单独处理start和end
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    // recurrsion 版本
    public int findPositionRC(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {

        // target不在nums裡
        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        // coding style: 如果上面有return，下面的if就不要寫else，也盡量不要用到else

        if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, end, target);
        }
        return binarySearch(nums, start, mid - 1, target);

    }
}
