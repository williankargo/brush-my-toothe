package leetcode.TwoPointer.SortColor;

public class sortColorIII {
  public void sortColors2(int[] colors, int k){
    if (colors == null || colors.length < 2){
      return;
    }
    rainbowSort(colors, 0, colors.length - 1, 1, k);
  }

  public void rainbowSort(int[] colors, int start, int end, int colorFrom, int colorTo){


    if (colorFrom == colorTo){
      return;
    }

    if (start >= end){
      return;
    }

    int colorMid = (colorFrom + colorTo) / 2;  // 向下取整 和quicksort的pivot = A[(start + end) / 2]不一樣
    // 左邊區域小於等於中間色，右邊大於中間色            所以等于的在左边 和 pivot同侧
    int left = start, right = end;
    while (left <= right){
      // 左 <= k
      while (left <= right && colors[left] <= colorMid){ // ??? why <= ???
        left++;
      }
      // 右 > k
      while (left <= right && colors[right] > colorMid){
        right--;
      }
      if (left <= right){
        int temp = colors[left];
        colors[left] = colors[right];
        colors[right] = temp;
        left++;
        right--;
      }
    }

    rainbowSort(colors, start, right, colorFrom, colorMid);
    rainbowSort(colors, left, end, colorMid + 1, colorTo);  // 如果pivot = colorMid放在右邊，那這行colorFrom就會怪怪的，所以when=colorMid不可能放在右邊
  }
}
