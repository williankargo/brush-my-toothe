package leetcode.dataStructure.ExternalSorting;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class MergeKSortedArrays_486 {

  class Element {
    public int row, col, value;
    Element(int row, int col, int value) {
      this.row = row;
      this.col = col;
      this.value = value;
    }
  }

  private final Comparator<Element> ElementComparator = new Comparator<Element>() {
    public int compare(Element num1, Element num2) {
      return num1.value - num2.value;
    }
  };

  public int[] mergekSortedArrays(int[][] arrays) {

    if (arrays == null || arrays.length == 0) {
      return new int[0];
    }

    Queue<Element> queue = new PriorityQueue<Element>(arrays.length, ElementComparator);
    int totalSize = 0;

    // 把老大們抓起來
    for (int i = 0; i < arrays.length; i++) {
      if(arrays[i].length > 0){
        Element element = new Element(i, 0, arrays[i][0]);
        queue.offer(element);
        totalSize += arrays[i].length;
      }
    }

    int[] ans = new int[totalSize];
    int index = 0;
    while (!queue.isEmpty()) {
      Element element = queue.poll();
      ans[index++] = element.value;

      // 補回去
      if (element.col < arrays[element.row].length - 1) { // -1: 剩最後一個也不用補了
        element.col += 1;
        element.value = arrays[element.row][element.col];
        queue.offer(element);
      }
    }

    return ans;
  }
}
