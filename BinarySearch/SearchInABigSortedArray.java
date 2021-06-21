package leetcode.BinarySearch;

// 447.
// 倍增、二分的時複: O(logK)
public class SearchInABigSortedArray {

  public int searchBigSortedArray(ArrayReader reader, int target){
    // 倍增法，初始化查找範圍為1，後兩倍兩倍遞增
    int rangeTotal = 1;
    while (reader.get(rangeTotal - 1) < target){
      rangeTotal = rangeTotal * 2;
    }


    // 二分法
    int start = 0, end = rangeTotal - 1;
    while (start + 1 < end){
      int mid = start + (end - start) / 2;

      if (reader.get(mid) < target){
        start = mid;
      }
      // if reader.get(mid) == reader.get(target) 還是不能直接返回，因為要找最早出現的
      // reader.get(mid) >= target
      else{
        end = mid;
      }
    }
    if (reader.get(start) == target){
      return start;
    }
    if (reader.get(end) == target){
      return end;
    }

    return -1;
  }
}
