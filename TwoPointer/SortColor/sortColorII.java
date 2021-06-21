package leetcode.TwoPointer.SortColor;

// 時O(N) ， 空O(1) 因為 in-place
public class sortColorII {

    public void sortColors(int[] a) {
        partitionArray(a, 1); // 先排1
        partitionArray(a, 2); // 再排2
    }

    // 前指針往前找，後指針抓交替
    // | 1 0 2 0 2
    // ^-^
    // ^---^
    // --^-^
    // --0 1 2 0 2
    // ...
    // --0 0 2 1 2
    // ----^---^
    public void partitionArray(int[] nums, int k) {

        // 指向<k區間的最後一個元素
        int lastSmallPointer = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < k) {
                lastSmallPointer++;
                swap(nums, lastSmallPointer, i);
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
