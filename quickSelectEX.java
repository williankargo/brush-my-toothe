package leetcode;

public class quickSelectEX {

    public int kthLargestElement(int k, int[] nums) {

        if (nums == null) {
            return -1;
        }
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {

        if (start == end) { // 相等說明已經找到了
            return nums[start];
        }

        // quick sort的東西背起來
        int i = start, j = end;
        int pivot = nums[(i + j) / 2];

        while (i <= j) {
            while (i <= j && nums[i] < pivot) { //  > 變成從大排到小
                i++;
            }
            while (i <= j && nums[j] > pivot) { //  < 變成從大排到小
                j--;
            }
            if (i <= j) { // ＊ 當 i == j 時，再往外擴，那麼 i j 中間就會有一個數了
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        // 此時 j在左邊 i在右邊
        // 如果第k大的數在左邊，就只找左邊
        // -------v
        // start_____j___i_______end
        //
        if (start + (k - 1) <= j) { // (k - 1)是offset
            return quickSelect(nums, start, j, k);
        }

        // 如果第k大的數在右邊，就只找右邊
        // ------------------v
        // start_____j___i_______end
        //
        if (start + (k - 1) >= i) {
            return quickSelect(nums, i, end, k - (i - start));
        }

        // 如果第k大的數在 i j 中間 ＊
        // ------------v
        // start_____j___i_______end
        //
        return nums[j + 1];

    }

}