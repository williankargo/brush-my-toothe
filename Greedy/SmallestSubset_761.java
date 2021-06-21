package leetcode.Greedy;

import java.util.Arrays;

public class SmallestSubset_761 {

  public static void main(String[] args) {
    SmallestSubset_761 demo = new SmallestSubset_761();
    int[] data = new int[]{3, 1, 7, 1};
    System.out.println(demo.minElements(data));
  }

  public int minElements(int[] arr) {

    int sum = 0;
    int halfSum = 0;
    int length = arr.length;

    for (int i = 0; i < length; i++) {
      sum += arr[i];
    }
    halfSum = sum / 2;
    Arrays.sort(arr);

    int currSum = 0;
    int num = 0;
    for (int i = length - 1; i >= 0; i--) {
      currSum += arr[i];
      num++;
      if (currSum > halfSum) {
        return num;
      }
    }
    return num;
  }

}
