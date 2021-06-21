package leetcode.BinarySearch;

// 75.
public class FindPeakElement {

  public int findPeak(int[] A) {

    // peak不可能在兩端，所以兩端不看
    int start = 1, end = A.length - 2;

    while (start + 1 < end){
      int mid = start + (end - start) / 2;

      // 如果mid向左上方傾斜，選左半邊找peak
      if (A[mid] < A[mid - 1]){
        end = mid;
        // 如果mid向右上方傾斜，選右半邊找peak
      }else if(A[mid] > A[mid + 1]){
        start = mid;
        //mid = peak
      }else{
        return mid;
      }
    }
    // start end中找peak
    return Math.max(A[start], A[end]);
  }
}
