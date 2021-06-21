package leetcode.BinarySearch;

public class CopyBooks_437 {

  public int copyBooks(int[] pages, int k) {

    if (pages == null || pages.length == 0) {
      return 0;
    }
    if (k == 0) {
      return -1;
    }

    int start = 0, end = Integer.MAX_VALUE; // 偷懶寫法，不算出全部page數

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (getNumberOfCopies(pages, mid) <= k) {
        end = mid;
      } else {
        start = mid;
      }
    }

    if (getNumberOfCopies(pages, start) <= k) {  // 先看時間比較短(k會比較大)的有沒有滿足
      return start;
    }
    return end;
  }

  private int getNumberOfCopies(int pages[], int limit) { // 丟入時間成本上限，算人數

    int copiers = 0;
    int lastCopied = limit; // 避免 [0,0,0,0] 的情況還返回一個人

    for (int page : pages) {
      if (page > limit) { // 大於最大時間成本，一個人不可能完成的單位，不管再多少人也不可能分攤
        return Integer.MAX_VALUE;
      }

      if (page + lastCopied > limit) {
        copiers++;
        lastCopied = 0;
      }
      lastCopied += page;
    }
    return copiers;
  }
}
