package leetcode.Hash;

public class HeapSort_463 {

  private void siftdown(int[] A, int left, int right) {
    int k = left;
    while (k * 2 + 1 <= right) {
      int son = k * 2 + 1;  // A[k] 的左儿子下标
      if (son + 1 <= right && A[son] < A[son + 1]) {
        son = k * 2 + 2;    // 选择两个儿子中较小的
      }
      if (A[son] <= A[k]) {
        break;
      }
      // 父親與比自己小的兒子向下交換
      int tmp = A[son];
      A[son] = A[k];
      A[k] = tmp;
      k = son;
    }
  }

  // 堆化
  public void heapify(int[] A) {
    for (int i = (A.length - 1) / 2; i >= 0; i--) {
      siftdown(A, i, A.length - 1);
    }
  }

  void sortIntegers(int[] A) { // 升序排列
    heapify(A);
    for (int i = A.length - 1; i > 0; i--) {
      int tmp = A[0];
      A[0] = A[i];
      A[i] = tmp;
      siftdown(A, 0, i - 1);
    }
  }
}
