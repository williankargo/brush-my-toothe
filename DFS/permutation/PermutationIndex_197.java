package leetcode.DFS.permutation;

// 備忘錄
// 時 O(n^2) 空O(1)
public class PermutationIndex_197 {

  public long permutationIndex(int[] A) {

    long result = 0;
    long permutation = 1;

    for (int i = A.length - 2; i >= 0; i--) { // 從倒數第二位開始
      int small = 0;
      for (int j = i + 1; j < A.length; j++) {
        if (A[j] < A[i]) { // 比 A[i] 小的就加進去
          small++;
        }
      }
      result += small * permutation;
      permutation *= A.length - i;
    }
    return result + 1;
  }
}

// 4 3 2 1