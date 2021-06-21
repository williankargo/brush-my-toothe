package leetcode.LowerThanON;

public class MedianOfTwoSortedArrays_65 {

  public double findMedianSortedArrays(int[] input1, int[] input2) {
    // 短的放上面，方便接下來的處理
    if (input1.length > input2.length) {
      return findMedianSortedArrays(input2, input1);
    }

    int x = input1.length;
    int y = input2.length;

    // 短的指標
    int low = 0;
    int high = x;

    while (low <= high) {
      int partitionX = (low + high) / 2;
      int partitionY = (y + x + 1) / 2 - partitionX; // partitionX, Y 加起來會是總長的一半
      // 為什麼要+1? 這樣總數是odd時，左邊可以多一個數，如果是even的話沒有差小數點會被吃掉

      int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
      int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

      int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
      int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

      if (maxLeftX <= minRightY && minRightX >= maxLeftY) {

        if ((x + y) % 2 == 0) { // 如果總長是偶數
          return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;

        } else { // 如果總長是奇數
          return (double) Math.max(maxLeftX, maxLeftY); // 左邊的個數會多一個
        }
      } else if (maxLeftX > minRightY) { // 短的的劃分要左移一點
        high = partitionX - 1;
      } else { // 短的的劃分再試著右移一點
        low = partitionX + 1; // 不+-1會超時
      }
    }
    return 0; // no answer
  }
}

// https://www.youtube.com/watch?v=LPFhl65R7ww
// 這題和二分法模板不一樣

// 有時間再研究這個分治法版本 : https://www.jiuzhang.com/problem/median-of-two-sorted-arrays/