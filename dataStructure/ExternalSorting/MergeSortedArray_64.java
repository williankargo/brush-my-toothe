package leetcode.dataStructure.ExternalSorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSortedArray_64 {

  public void mergeSortedArray(int[] A, int m, int[] B, int n) {

    if (A == null || B == null) {
      return;
    }

    List<Integer> array = new ArrayList<Integer>();
    int i = 0, j = 0;
    while (i < m && j < n) {
      if (A[i] < B[j]) {
        array.add(A[i]);
        i++;
      } else {
        array.add(B[j]);
        j++;
      }
    }

    // B[j] 都排完了
    while (i < m) {
      array.add(A[i]);
      i++;
    }

    // A[i] 都排完了
    while (j < n) {
      array.add(B[j]);
      j++;
    }

    int k = 0;
    for (Integer num : array) {
      A[k++] = (int) num;
    }

  }

  // follow-up: in-place
  // 在A[]中從後往前放，先放大的再放小的，
  // 『每次先放大的在A[]放面』相對『每次先放小的放A[]前面』，不用每次都整個往後移增加時間複雜度了
  // 備忘錄
  public void mergeSortedArray2(int[] A, int m, int[] B, int n) {

    if (A == null || B == null) {
      return;
    }

    int i = m - 1, j = n - 1, size = A.length - 1;
    while (i >= 0 && j >= 0) {
      if (A[i] > B[j]) {
        A[size--] = A[i--];
      } else {
        A[size--] = B[j--];
      }
    }

    // B[j] 都排完了
    while (i >= 0) {
      A[size--] = A[i--];
    }

    // A[i] 都排完了
    while (j >= 0) {
      A[size--] = B[j--];
    }
  }
}

