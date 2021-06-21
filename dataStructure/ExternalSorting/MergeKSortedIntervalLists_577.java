package leetcode.dataStructure.ExternalSorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import leetcode.dataStructure.ExternalSorting.MergeTwoSortedIntervalLists_839.Interval;

// 時 O(nlogk), n:總共有多少個 k:heap的容量
// 空 O(n)
public class MergeKSortedIntervalLists_577 {

  class Element {

    int row;
    int column;

    Element(int row, int column) {
      this.row = row;
      this.column = column;
    }
  }

  public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {

    Queue<Element> queue = new PriorityQueue<>(intervals.size(),
        new Comparator<Element>() {
          @Override
          public int compare(Element o1, Element o2) {
            return intervals.get(o1.row).get(o1.column).start - intervals.get(o2.row)
                .get(o2.column).start;
          }
        });

    // 放老大們
    for (int i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).size() != 0) {
        queue.offer(new Element(i, 0));  // 放Element進去
      }
    }

    // 產生以start排序的list
    List<Interval> preResult = new ArrayList<>();
    while (!queue.isEmpty()) {
      Element element = queue.poll();
      preResult.add(intervals.get(element.row).get(element.column));
      element.column++;
      if (element.column < intervals.get(element.row).size()) {
        // intervals.get(element.row).get(element.column) != null 這樣寫會 index out of bound
        queue.offer(element);
      }
    }

    return merger(preResult);
  }


  // 產生最終答案
  private List<Interval> merger(List<Interval> preResult) {

    List<Interval> result = new ArrayList<>();
    int currStart = preResult.get(0).start;
    int currEnd = preResult.get(0).end;

    for (Interval interval : preResult) {
      if (interval.start <= currEnd) {
        currEnd = Math.max(currEnd, interval.end);
      } else { // 要新增interval了
        result.add(new Interval(currStart, currEnd));
        currStart = interval.start;
        currEnd = interval.end;
      }
    }

    // 不要忘記最後一次
    result.add(new Interval(currStart, currEnd));
    return result;
  }
}