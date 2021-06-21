package leetcode.DFS.permutation;


// 看備忘錄
// 時O(N) 空O(1)
public class nextPermutation_52 {

  public int[] nextPermutation(int[] nums) {

    int length = nums.length;
    if (length < 2) {
      return nums;
    }

    int i = length - 1;
    while (i > 0 && nums[i] <= nums[i - 1]) {
      i--;
    }

    if (i != 0) { // nums不是遞減的狀況
      int j = length - 1;
      while (nums[j] <= nums[i - 1]) { // 要徹底大於
        j--;
      }
      swapItems(i - 1, j, nums);
    }
    swapList(i, length - 1, nums); // 可以同時處理nums非遞減的狀況或nums遞減的狀況

    return nums;
  }

  private void swapItems(int i, int j, int[] nums) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void swapList(int i, int j, int[] nums) {

    while (i < j) {
      swapItems(i, j, nums);
      i++;
      j--;
    }
  }
}




