package leetcode.dataStructure.ExternalSorting;

import java.util.ArrayList;
import java.util.List;

// 時空 O(M + N) M, N 分別代表 list1, list2的長度
public class MergeTwoSortedIntervalLists_839 {

  static class Interval {

    int start, end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {

    if (list1 == null) {
      return list2;
    }
    if (list2 == null) {
      return list1;
    }

    int index1 = 0, index2 = 0;
    Interval now = null; // 待加入的東東
    List<Interval> results = new ArrayList<>();

    // 雙指針
    while (index1 < list1.size() && index2 < list2.size()) {
      if (list1.get(index1).start <= list2.get(index2).start) {
        now = list1.get(index1++);
      } else {
        now = list2.get(index2++);
      }

      if (results.isEmpty()) {  // 第一個加入的
        results.add(now);
      } else {
        merge(now, results.get(results.size() - 1), results);
      }
    }

    // list2跑完了
    while (index1 < list1.size()) {
      now = list1.get(index1++);
      merge(now, results.get(results.size() - 1), results); // 打擂台看能不能更改末尾
    }

    while (index2 < list2.size()) {
      now = list2.get(index2++);
      merge(now, results.get(results.size() - 1), results);
    }

    return results;
  }

  // 用來找末尾
  private void merge(Interval now, Interval last, List<Interval> results) {

    // 排到下一個末尾
    if (now.start > last.end) {
      results.add(now);
      return;
    }
    // 更改現在的末尾
    last.end = Math.max(now.end, last.end);
  }
}