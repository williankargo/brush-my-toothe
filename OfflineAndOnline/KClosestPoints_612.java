package leetcode.OfflineAndOnline;

import java.util.Comparator;
import java.util.PriorityQueue;

// 把所有點(n)都放入最小堆，然後從堆中取出k個 : time O((n+k)log(n)) / space O(n)
public class KClosestPoints_612 {

  public static void main(String[] args) {

    int ans1 = (4 - 2) * (4 - 2) + (5 - 3) * (5 - 3);
    int ans2 = (4 - 2) ^ 2 + (5 - 3) ^ 2; // 這不是平方，這是 XOR!!!
    System.out.println(ans1);
    System.out.println(ans2);
  }

  public Point[] kClosest(Point[] points, Point origin, int k) {

    // initial capacity (the queue will grow as it needs to) & comparator
    PriorityQueue<Point> pq = new PriorityQueue<>(k, new PointComparator(origin)); // min heap!

    // 所有點加入pq
    for (int i = 0; i < points.length; i++) {
      pq.offer(points[i]);
    }

    int size = Math.min(k, pq.size());
    Point[] ans = new Point[size];

    int index = 0;
    while (index < size) {
      ans[index++] = pq.poll();
    }
    return ans;
  }


  private int getDistance(Point a, Point b) {
    return (int) Math.pow((a.x - b.x), 2) + (a.y - b.y) * (a.y - b.y);
  }

  // 創造一個比較器(inner class)
  class PointComparator implements Comparator<Point> {

    private Point origin;

    public PointComparator(Point origin) {
      this.origin = origin;
    }

    public int compare(Point a, Point b) {
      int diff = getDistance(a, origin) - getDistance(b, origin);

      if (diff == 0) {
        diff = a.x - b.x;
      }
      if (diff == 0) {
        diff = a.y - b.y;
      }
      return diff;
    }
  }

}

class Point {

  int x;
  int y;

  Point() {
    x = 0;
    y = 0;
  }

  Point(int a, int b) {
    x = a;
    y = b;
  }
}