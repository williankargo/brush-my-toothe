package leetcode.BinarySearch;

// 440.
public class FindKClosestElements {
  public int[] KClosestNumbers(int[] A, int target, int k){
    int left = findLowerClosest(A, target);
    int right = left + 1;

    int[] results = new int[k];
    for (int i = 0; i < k; i++){
      // 如果左邊更接近，選左邊
      if (isLeftCloser(A, target, left, right)){
        results[i] = left;
        left--;
      }else{
        results[i] = right;
        right++;
      }
    }
    return results;
  }

  private boolean isLeftCloser(int[] A, int target, int left, int right){

    // 如果左邊已經耗盡
    if (left < 0){
      return false;
    }

    // 如果右邊已經耗盡
    if (right >= A.length){
      return true;
    }

    // 為什麼有等號？如果左右距離相等，選左邊
    return target - A[left] <= target - A[right];
  }


  // 找小於target的最右邊一個數
  private int findLowerClosest(int[] A, int target){
    int start = 0, end = A.length - 1;
    while (start + 1 < end){
      int mid = start + (end - start) / 2;
      if(A[mid] < A[target]){
        start = mid;
      }
      else{
        end = mid;
      }
    }
    if (A[end] < A[target]){
      return end;
    }
    if (A[start] < A[target]){
      return start;
    }
    return -1;
  }
}
